package com.example.my_travel_guide.Activity

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_travel_guide.Adapter.TravelListAdapter
import com.example.my_travel_guide.Entity.LoggedUser
import com.example.my_travel_guide.Entity.Travel
import com.example.my_travel_guide.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class home : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var loggedUser = LoggedUser()
        loggedUser.infoTravel?.forEach { Log.d(TAG, it.name ) }

        var travelListAdapter = TravelListAdapter(loggedUser.infoTravel as MutableList<Travel>)
        var recyclerView = findViewById<RecyclerView>(R.id.card_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = travelListAdapter



    }

}