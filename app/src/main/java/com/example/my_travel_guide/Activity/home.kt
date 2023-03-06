package com.example.my_travel_guide.Activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.my_travel_guide.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class home : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val docUser = db.collection("users").document(Firebase.auth.currentUser?.email.toString())
        docUser.get()
            .addOnSuccessListener { document ->
                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    Log.d(TAG, "DocumentSnapshot data: ${document.data?.get("id_travel")}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
//        val userTravel : Array<*> = docUser

    }

}