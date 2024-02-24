package com.example.loginandregisterscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandregisterscreen.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreenActivity : AppCompatActivity() {
    private val binding: ActivityLoginScreenBinding by lazy {
        ActivityLoginScreenBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Initialise Firebase Auth

        auth = FirebaseAuth.getInstance()

        binding.nextbutton.setOnClickListener {
            val userName = binding.usernamelogin.text.toString()
            val password = binding.passwordlogin.text.toString()

            if(userName.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(userName,password)
                    .addOnCompleteListener(this){ task->
                        if(task.isSuccessful)
                        {
                            Toast.makeText(this, "Sign In Succesful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))

                        }
                        else {
                            // If sign in fails, display a message to the user based on the specific error
                            val errorMessage = task.exception?.message
                            if (errorMessage != null) {
                                // Check if the error message contains specific keywords indicating incorrect credentials
                                if (errorMessage.contains("password")) {
                                    // Incorrect password
                                    Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                                } else if (errorMessage.contains("email")) {
                                    // Incorrect email (username)
                                    Toast.makeText(this, "Wrong email", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Other error, display the error message returned by Firebase
                                    Toast.makeText(this, "Sign In Failed. Please try again.", Toast.LENGTH_SHORT).show()

                                }
                            }
                        }
                    }
            }

        }

        binding.newuserbutton.setOnClickListener {

            startActivity(Intent(applicationContext,SignUpScreen::class.java))
        }

    }
}