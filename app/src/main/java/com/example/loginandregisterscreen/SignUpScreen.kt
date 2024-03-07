package com.example.loginandregisterscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandregisterscreen.LoginScreenActivity
import com.example.loginandregisterscreen.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpScreen : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.register.setOnClickListener {
            val email = binding.emailinput.text.toString()
            val username = binding.usernameinput.text.toString()
            val password = binding.passwordinput.text.toString()
            val repeatPassword = binding.repasswordinput.text.toString()
            val city = binding.cityinput.text.toString()
            val phone = binding.numberinput.text.toString()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || phone
                    .isEmpty() || city.isEmpty()) {
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            } else if (password != repeatPassword) {
                Toast.makeText(this, "Repeat Password Must Be Same", Toast.LENGTH_SHORT).show()
            }
            else if(phone.length!=10){
                Toast.makeText(this,"Phone Number Must be 10 Digits",Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userId = user?.uid

                            // Create a reference to the "User" node in the database
                            val userRef = database.reference.child("User")

                            // Create a child node with the UID of the user and set user data
                            val userData = HashMap<String, Any>()
                            userData["email"] = email
                            userData["username"] = username
                            userData["city"] = city
                            userData["phone"] = phone
                            userData["password"] = password
                            userId?.let {
                                userRef.child(it).setValue(userData)
                            }

                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginScreenActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
