package com.example.my_travel_guide.Entity

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoggedUser(email: String, date_payment: Int, id_travels: Array<*>) {
    private val db = Firebase.firestore
}