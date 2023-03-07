package com.example.my_travel_guide.Entity

import android.R.id
import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class LoggedUser() {

    private val db = Firebase.firestore
//    @JvmField
    var isPaid: Boolean ?= null

    var data_payment: Int ?= 0
    var infoTravel: ArrayList<Travel>? = null
    private var idTravel: ArrayList<String>? = null

    init{
        val docUser = db.collection("users").document(Firebase.auth.currentUser?.email.toString())
        docUser.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    isPaid = document.data?.get("isPaid") as Boolean?
                    data_payment = document.data?.get("data_payed") as Int?
                    idTravel = document.data?.get("id_travel") as ArrayList<String>?

                    idTravel?.forEach {
                        infoTravel?.add(Travel( it ))
                    }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }
}