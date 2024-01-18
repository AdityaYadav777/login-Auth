package com.aditya.projectz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditya.projectz.databinding.ActivityForgotPassBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class forgotPassAct : AppCompatActivity() {
    lateinit var binding:ActivityForgotPassBinding
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth



        binding.resetBtn.setOnClickListener {

            val email=binding.resetEmail.text.toString()
            if(email.isEmpty()){
                binding.resetEmail.setError("Enter Email")
            }else {

                auth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(this, "Reset Link Send In Your Email", Toast.LENGTH_LONG).show()
                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed To Send Link", Toast.LENGTH_LONG).show()

                    }
            }

        }




    }

}