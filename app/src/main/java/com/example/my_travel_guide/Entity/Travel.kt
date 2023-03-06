package com.example.my_travel_guide.Entity

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Travel( id_travel: String ) {
    private val db = Firebase.firestore
    lateinit var admin: String
    lateinit var geoPoint: GeoPoint

    init {

        val travelInfo = db.collection("travel").document(id_travel)
        travelInfo.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    admin = document.data?.get("admin") as String
                    geoPoint = document.getGeoPoint("coord")!!
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    fun createTravel() {

    }

}