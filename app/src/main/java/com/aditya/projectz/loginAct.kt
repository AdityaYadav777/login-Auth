package com.aditya.projectz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditya.projectz.databinding.ActivityLoginBinding
import com.aditya.projectz.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class loginAct : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.btnForget.setOnClickListener {
    startActivity(Intent(this,forgotPassAct::class.java))
}

       binding.gotoSignup.setOnClickListener {
            startActivity(Intent(this,signupAct::class.java))
        }
val auth=Firebase.auth
        binding.loginBtn.setOnClickListener {
            val logEmail=binding.logEmail.text.toString()
            val logPass=binding.logPass.text.toString()
            if (checkData() == 1) {

                auth.signInWithEmailAndPassword(logEmail, logPass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {

                            Toast.makeText(this, "Email or Password is Worng", Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

                    }

     fun checkData():Int{

         if(binding.logEmail.text.isEmpty()){
             binding.logEmail.setError("Enter Email")
             return 0
         }
         if(binding.logPass.text.isEmpty()){
             binding.logPass.setError("Enter Password!")
             return 0
         }

         return 1
    }

    override fun onStart() {
        super.onStart()
        val auth=Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser!=null){
            startActivity(Intent(this,MainActivity::class.java))
        }


    }

}
