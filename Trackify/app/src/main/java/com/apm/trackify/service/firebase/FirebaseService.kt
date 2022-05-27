package com.apm.trackify.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.apm.trackify.model.domain.RouteItem
import com.apm.trackify.model.domain.UserItem
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch

class FirebaseService {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun createNewUser(userName: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val user: MutableMap<String, Any> = HashMap()
        user["following"] = listOf<DocumentReference>()
        user["routes"] = listOf<DocumentReference>()
        user["followers"] = 0

        db.collection("users").document(userName).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documentSnap = task.result
                if (documentSnap.exists()) {
                    Log.d(TAG, "Document already exists")
                } else {
                    db.collection("users").document(userName)
                        .set(user).addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure() }
                }
            }
        }
    }

    fun createNewRoute(
        userName: String, name: String, coordinates: List<LatLng>, playlistUrl: String,
        onSuccess: () -> Unit, onFailure: () -> Unit
    ) {
        val route: MutableMap<String, Any> = HashMap()
        val firstCoordinate = coordinates.first()
        route["name"] = name
        route["playlistId"] = playlistUrl
        route["firstLat"] = firstCoordinate.latitude
        route["firstLong"] = firstCoordinate.longitude
        route["coordinates"] = coordinates
        route["creator"] = userName

        db.collection("routes").add(route).addOnSuccessListener { document ->
            db.collection("users").document(userName).update(
                "routes",
                FieldValue.arrayUnion(db.document("routes/${document.id}"))
            ).addOnSuccessListener { onSuccess() }.addOnFailureListener { onFailure() }
        }
    }

    fun deleteRoute(routeId: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        db.collection("routes").document(routeId).delete().addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    private fun checkFollowed(
        userName: String,
        followedUserName: String,
        onResult: (Boolean) -> Unit
    ) {
        var following: MutableList<String> = ArrayList()

        db.collection("users").document(userName).get().addOnSuccessListener { document ->
            following = document.data?.getValue("following") as MutableList<String>
            onResult(following.contains((followedUserName)))
        }
    }

    fun follow(
        myUserName: String,
        followedUserName: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        checkFollowed(myUserName, followedUserName) {
            if (!it) {
                val batch: WriteBatch = db.batch()
                val myProfile = db.document("users/${myUserName}")
                val followedProfile = db.document("users/${followedUserName}")

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
            } else {
                onFailure()
            }
        }
    }

    fun unfollow(
        myUserName: String,
        followedUserName: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        checkFollowed(myUserName, followedUserName) {
            if (it) {
                val batch: WriteBatch = db.batch()
                val myProfile = db.document("users/${myUserName}")
                val followedProfile = db.document("users/${followedUserName}")

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

    fun findRoutesByUsername(userName: String, forEachRoute: (RouteItem) -> Unit) {

        db.collection("routes").whereEqualTo("creator", userName).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
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

    fun getUser(userName: String, onSuccess: (UserItem) -> Unit) {

        db.collection("users").document(userName).get().addOnSuccessListener { document ->
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
    }

    fun findFollowingUsers(userName: String, forEachUser: (UserItem) -> Unit) {
        var docList: MutableList<DocumentReference> = ArrayList()
        var userList: MutableList<UserItem> = ArrayList()

        db.collection("users").document(userName).get()
            .addOnSuccessListener { myDocument ->
                val data = myDocument.data
                for (doc in (data?.get("following") as MutableList<DocumentReference>)) {
                    getUser(doc.id, forEachUser)
                }
            }
    }
}