package com.apm.trackify.service

import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseService @Inject constructor() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun createNewUser(userName: String, onFailureListener: OnFailureListener) {
        val user: MutableMap<String, Any> = HashMap()
        user["following"] = listOf<DocumentReference>()
        user["routes"] = listOf<DocumentReference>()
        user["followers"] = 0

        db.collection("users").document(userName).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documentSnap = task.result
                if (documentSnap.exists()) {
                    Log.d(TAG, "Document already exists");
                } else {
                    db.collection("users").document(userName)
                        .set(user)
                        .addOnFailureListener(onFailureListener)
                }
            }
        }

    }

    fun createNewRoute(userName: String, name: String, coordinates: String, playlistUrl: String) {
        val route: MutableMap<String, Any> = HashMap()
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

    suspend fun getRoutesByUsername(userName: String): MutableList<MutableMap<String, Any>> {
        var routeList: MutableList<MutableMap<String, Any>> = ArrayList()

        db.collection("routes").whereEqualTo("creator", userName).get().addOnSuccessListener { documents ->
            for (document in documents) {
                routeList.add(document.data)
            }
        }.await()

        return routeList
    }
}