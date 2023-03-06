package com.example.my_travel_guide.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.my_travel_guide.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = Firebase.auth

        findViewById<Button>(R.id.signupButton).setOnClickListener {
            val emailText: String =
                findViewById<TextInputEditText>(R.id.signupEmailField).text.toString()
            val passwordText: String =
                findViewById<TextInputEditText>(R.id.signupPasswordField).text.toString()
            val repasswordText: String =
                findViewById<TextInputEditText>(R.id.repasswordField).text.toString()

            if (isValidEmail( emailText ) && validPassword(passwordText, repasswordText) == true)
                createUser(emailText, passwordText)
        }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())
            true
        else{
            Toast.makeText(
                applicationContext,
                "Email not valid",
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }
    private fun validPassword(passwordText: String, repasswordText: String): Boolean? {

        if (passwordText!!.length < 8) {
            Toast.makeText(applicationContext, "Minimum 8 Character Password", Toast.LENGTH_LONG)
                .show()
            return false
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            Toast.makeText(
                applicationContext,
                "Must Contain 1 Upper-case Character",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            Toast.makeText(
                applicationContext,
                "Must Contain 1 Lower-case Character",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (!passwordText.matches(".*[@#\$%!?-_^&+=].*".toRegex())) {
            Toast.makeText(
                applicationContext,
                "Must Contain 1 Special Character (@#\$!?-_%^&+=)",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (repasswordText != passwordText) {
            Toast.makeText(applicationContext, "Password not match", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    Firebase.auth.currentUser!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(ContentValues.TAG, "Email sent.")
                                startActivity(Intent(this, login::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "User creation not valid",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}