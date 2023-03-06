package com.example.my_travel_guide.Activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.my_travel_guide.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        if (auth.currentUser != null) {
            Firebase.auth.signOut()
//            startActivity(Intent(this, home::class.java))
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            loginFirebase()
        }
        findViewById<Button>(R.id.linkSignupButton).setOnClickListener {
            startActivity(Intent(this, signup::class.java))
        }
    }

    private fun loginFirebase() {
        val email: String = findViewById<TextInputEditText>(R.id.emailField).text.toString()
        val password: String = findViewById<TextInputEditText>(R.id.passwordField).text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")

                    if(auth.currentUser?.isEmailVerified == true){
                        startActivity(Intent(this, home::class.java))
                        Log.d(TAG, "signInWithEmail:success2")
                    }
                    else
                        Toast.makeText(
                            baseContext, "Email is not verified.",
                            Toast.LENGTH_SHORT
                        ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "User not found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}