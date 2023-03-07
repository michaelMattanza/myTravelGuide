package com.example.my_travel_guide.Entity

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class Travel( id_travel: String ) {
    private val db = Firebase.firestore
    lateinit private var step: TravelSteps

    lateinit var admin: String
    lateinit var name: String
    lateinit var steps: ArrayList<TravelSteps>

    init {

        val travelInfo = db.collection("travel").document(id_travel)
        travelInfo.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    admin = document.data?.get("admin") as String
                    name = document.data?.get("name") as String
                    step = document.data?.get("step") as TravelSteps
                    steps.add(step)
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


    class TravelSteps( geoPoint: GeoPoint, place: String ){
        var coord: GeoPoint = geoPoint
        var place: String = place
    }

}