package com.apm.trackify.service

import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseService @Inject constructor() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )
    }

    fun createNewUser(userName: String, token: String, onFailureListener: OnFailureListener) {
        val user: MutableMap<String, Any> = HashMap()
        user["token"] = token
        user["following"] = listOf<String>()
        user["routes"] = listOf<String>()

        db.collection("users").document(userName)
            .set(user)
            .addOnFailureListener(onFailureListener)
    }

    fun createNewRoute(userName: String, name: String, coordinates: String, playlistUrl: String ) {
        val route: MutableMap<String, Any> = HashMap()
        route["playlistUrl"] = playlistUrl
        route["coordinates"] = coordinates
        route["creator"] = userName

        db.collection("routes").add(route)
            .addOnSuccessListener { document ->
                val userRef = db.collection("users").document(userName)
                userRef.update("routes", FieldValue.arrayUnion(document))
            }
    }
}