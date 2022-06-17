package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtpassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnsignup : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtpassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener{
            val intent = Intent (this, signup::class.java)
            startActivity(intent)
    }

        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtpassword.text.toString()

            login(email,password);
        }
    }
    private fun login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "User dosent esxist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}