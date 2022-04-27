package com.apm.trackify.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.apm.trackify.model.domain.Route
import com.apm.trackify.model.domain.User
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import kotlinx.coroutines.tasks.await

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
                        .set(user).addOnSuccessListener { _ -> onSuccess() }
                        .addOnFailureListener { _ -> onFailure() }
                }
            }
        }

    }

    fun createNewRoute(userName: String, name: String, coordinates: String, playlistUrl: String) {
        val route: MutableMap<String, Any> = HashMap()
        route["name"] = name
        route["playlistUrl"] = playlistUrl
        route["coordinates"] = coordinates
        route["creator"] = userName

        db.collection("routes").add(route).addOnSuccessListener { document ->
            db.collection("users").document(userName).update(
                "routes",
                FieldValue.arrayUnion(db.document("routes/${document.id}"))
            )
        }
    }

    suspend fun checkFollowed(userName: String, followedUserName: String): Boolean {
        var following: MutableList<String> = ArrayList()

        db.collection("users").document(userName).get().addOnSuccessListener { document ->
            following = document.data?.getValue("following") as MutableList<String>
        }.await()

        return following.contains(followedUserName)
    }

    fun follow(myUserName: String, followedUserName: String) {
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
        batch.commit()
    }

    fun unfollow(myUserName: String, followedUserName: String) {
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
        batch.commit()
    }

    fun findRoutesByUsername(userName: String, forEachRoute: (Route) -> Unit) {

        db.collection("routes").whereEqualTo("creator", userName).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                   forEachRoute(
                        Route(
                            document.id,
                            document.data["name"] as String,
                            document.data["playlistUrl"] as String,
                            document.data["coordinates"] as String,
                            document.data["creator"] as String
                        )
                    )
                }
            }
    }

    fun getUser(userName: String, onSuccess: (User) -> Unit) {

        db.collection("users").document(userName).get().addOnSuccessListener { document ->
            val following = document.data?.get("following") as List<DocumentReference>
            val routes = document.data?.get("routes") as List<DocumentReference>
            onSuccess(
                User(
                    document.id,
                    following.map { it.toString() },
                    routes.map { it.toString() },
                    document.data?.get("followers") as Long
                )
            )
        }
    }

    fun findFollowingUsers(userName: String, forEachUser: (User) -> Unit) {
        var docList: MutableList<DocumentReference> = ArrayList()
        var userList: MutableList<User> = ArrayList()

        db.collection("users").document(userName).get()
            .addOnSuccessListener { myDocument ->
                val data = myDocument.data
                for (doc in (data?.get("following") as MutableList<DocumentReference>)) {
                    getUser(doc.id, forEachUser)
                }
            }
    }
}