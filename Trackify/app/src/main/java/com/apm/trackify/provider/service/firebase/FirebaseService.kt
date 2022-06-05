package com.apm.trackify.provider.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.apm.trackify.provider.model.domain.RouteItem
import com.apm.trackify.provider.model.domain.UserItem
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch

class FirebaseService {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun createNewUser(userId: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val user: MutableMap<String, Any> = HashMap()
        user["following"] = listOf<DocumentReference>()
        user["routes"] = listOf<DocumentReference>()
        user["followers"] = 0

        db.collection("users").document(userId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documentSnap = task.result
                if (documentSnap.exists()) {
                    Log.d(TAG, "Document already exists")
                    onSuccess()
                } else {
                    db.collection("users").document(userId)
                        .set(user).addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure() }
                }
            }
        }
    }

    fun createNewRoute(
        userId: String, name: String, coordinates: List<LatLng>, playlistUrl: String,
        onSuccess: () -> Unit, onFailure: () -> Unit
    ) {
        val route: MutableMap<String, Any> = HashMap()
        val firstCoordinate = coordinates.first()
        route["name"] = name
        route["playlistId"] = playlistUrl
        route["firstLat"] = firstCoordinate.latitude
        route["firstLong"] = firstCoordinate.longitude
        route["coordinates"] = coordinates
        route["creator"] = userId

        db.collection("routes").add(route).addOnSuccessListener { document ->
            db.collection("users").document(userId).update(
                "routes",
                FieldValue.arrayUnion(db.document("routes/${document.id}"))
            ).addOnSuccessListener { onSuccess() }.addOnFailureListener { onFailure() }
        }
    }

    fun deleteRoute(routeId: String, onSuccess: () -> Unit, onFailure: () -> Unit) {

        db.collection("routes").document(routeId).get().addOnSuccessListener {
            val batch: WriteBatch = db.batch()
            val route = db.collection("routes").document(routeId)
            val user = db.document("users/${it.data?.get("creator")}")

            batch.update(
                user, "routes",
                FieldValue.arrayRemove(route)
            )
            batch.delete(route)

            batch.commit().addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure()
            }
        }.addOnFailureListener {
            onFailure()
        }
    }

    fun checkFollowed(
        userId: String,
        followedUserId: String,
        onResult: (Boolean) -> Unit
    ) {
        var following: MutableList<DocumentReference> = ArrayList()

        db.collection("users").document(userId).get().addOnSuccessListener { document ->
            var check = false
            following = document.data?.getValue("following") as MutableList<DocumentReference>
            for (document: DocumentReference in following) {
                if (document.path == "users/${followedUserId}") {
                    check = true
                    break
                }
            }
            onResult(check)
        }
    }

    fun follow(
        myUserId: String,
        followedUserId: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        checkFollowed(myUserId, followedUserId) {
            if (!it) {
                val batch: WriteBatch = db.batch()
                val myProfile = db.document("users/${myUserId}")
                val followedProfile = db.document("users/${followedUserId}")

                batch.update(
                    myProfile, "following",
                    FieldValue.arrayUnion(followedProfile)
                )

                batch.update(
                    followedProfile,
                    "followers",
                    FieldValue.increment(1)
                )
                batch.commit().addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure() }
            } else {
                onFailure()
            }
        }
    }

    fun unfollow(
        myUserId: String,
        followedUserId: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        checkFollowed(myUserId, followedUserId) {
            if (it) {
                val batch: WriteBatch = db.batch()
                val myProfile = db.document("users/${myUserId}")
                val followedProfile = db.document("users/${followedUserId}")

                batch.update(
                    myProfile,
                    "following",
                    FieldValue.arrayRemove(followedProfile)
                )
                batch.update(
                    followedProfile,
                    "followers",
                    FieldValue.increment(-1)
                )
                batch.commit().addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure() }
            } else {
                onFailure()
            }
        }

    }

    fun findRoutesByUserId(
        userId: String,
        forEachRoute: (RouteItem) -> Unit,
        onFailure: () -> Unit
    ) {
        if (userId != "") {
            db.collection("routes").whereEqualTo("creator", userId).get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        for (document in documents) {
                            val coords =
                                document.data["coordinates"] as List<HashMap<String, Double>>
                            val newCoords = ArrayList<LatLng>()
                            for (coord in coords) {
                                val lat = coord["latitude"]
                                val long = coord["longitude"]
                                if (lat != null && long != null) {
                                    newCoords.add(LatLng(lat, long))
                                }
                            }
                            forEachRoute(
                                RouteItem(
                                    document.id,
                                    document.data["name"] as String,
                                    document.data["playlistId"] as String,
                                    document.data["firstLat"] as Double,
                                    document.data["firstLong"] as Double,
                                    newCoords,
                                    document.data["creator"] as String
                                )
                            )
                        }
                    } else {
                        onFailure()
                    }
                }.addOnFailureListener {
                    onFailure()
                }
        }
    }

    fun getUser(userId: String, onSuccess: (UserItem) -> Unit, onFailure: () -> Unit) {
        if (userId != "") {
            db.collection("users").document(userId).get().addOnSuccessListener { document ->
                if (document.data == null) {
                    onFailure()
                } else {
                    val following = document.data?.get("following") as List<DocumentReference>
                    val routes = document.data?.get("routes") as List<DocumentReference>
                    onSuccess(
                        UserItem(
                            document.id,
                            following.map { it.toString() },
                            routes.map { it.toString() },
                            document.data?.get("followers") as Long
                        )
                    )
                }
            }.addOnFailureListener {
                onFailure()
            }
        }
    }

    fun findFollowingUsers(
        userId: String,
        forEachUser: (UserItem) -> Unit,
        onFailure: () -> Unit
    ) {
        if (userId != "") {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { myDocument ->
                    val data = myDocument.data?.get("following")
                    if (data != null) {
                        val docs = data as MutableList<DocumentReference>
                        if (docs.isNotEmpty()) {
                            for (doc in docs) {
                                getUser(doc.id, forEachUser, onFailure)
                            }
                        } else {
                            onFailure()
                        }
                    }
                }.addOnFailureListener {
                    onFailure()
                }
        }
    }

    fun findRoutesByUserCoord(
        creator: String,
        latitude: Double,
        longitude: Double,
        forEachRoute: (RouteItem) -> Unit
    ) {

        val latThreshold = 0.050
        val longThreshold = 0.025

        Log.d("CREADOR", creator)

        db.collection("routes")
            .whereGreaterThanOrEqualTo("firstLat", latitude - latThreshold)
            .whereLessThanOrEqualTo("firstLat", latitude + latThreshold)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val firstLong = document.data["firstLong"] as Double
                    val docCreator = document.data["creator"] as String
                    if (firstLong >= longitude - latThreshold
                        && firstLong <= longitude + longThreshold
                        && creator != docCreator) {
                        val coords = document.data["coordinates"] as List<HashMap<String, Double>>
                        val newCoords = ArrayList<LatLng>()
                        for (coord in coords) {
                            val lat = coord["latitude"]
                            val long = coord["longitude"]
                            if (lat != null && long != null) {
                                newCoords.add(LatLng(lat, long))
                            }
                        }
                        forEachRoute(
                            RouteItem(
                                document.id,
                                document.data["name"] as String,
                                document.data["playlistId"] as String,
                                document.data["firstLat"] as Double,
                                document.data["firstLong"] as Double,
                                newCoords,
                                document.data["creator"] as String
                            )
                        )
                    }
                }
            }
    }

}