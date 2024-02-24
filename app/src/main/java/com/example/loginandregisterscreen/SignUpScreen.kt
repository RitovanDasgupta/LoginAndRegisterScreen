package com.example.loginandregisterscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandregisterscreen.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialise firebase auth
        auth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener{

            val email = binding.emailinput.text.toString()
            val username = binding.usernameinput.text.toString()
            val password = binding.passwordinput.text.toString()
            val repeatPassword = binding.repasswordinput.text.toString()

            if(email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty())
            {
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }
            else if(password != repeatPassword){
                Toast.makeText(this, "Repeat Password Must Be Same", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Registration Succesful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,LoginScreenActivity::class.java))
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.login.setOnClickListener {
            val email = binding.emailinput.text.toString()
            val username = binding.usernameinput.text.toString()
            val password = binding.passwordinput.text.toString()
            val repeatPassword = binding.repasswordinput.text.toString()

            if(email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty())
            {
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }
            else if(password != repeatPassword){
                Toast.makeText(this, "Repeat Password Must Be Same", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Registration Succesful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

    }
}