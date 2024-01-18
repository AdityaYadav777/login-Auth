package com.aditya.projectz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditya.projectz.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class signupAct : AppCompatActivity() {
    lateinit var binding:ActivitySignupBinding
    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotoLogin.setOnClickListener {
            startActivity(Intent(this,loginAct::class.java))

        }

        val auth=Firebase.auth
        database=Firebase.database.reference

        binding.signUpBtn.setOnClickListener {
            val getName=binding.getMyName.text.toString()
            val getMyEmail=binding.getMyEmial.text.toString()
            val getMyPass=binding.getMyPassword.text.toString()
            val confirmPass=binding.getMyConfirmPassword.text.toString()
            if(checkData()==1){

                if(auth.equals(getMyEmail)){
                    Toast.makeText(this,"Email Already Exist",Toast.LENGTH_SHORT).show()
                }

                if(getMyPass!=confirmPass){
                    binding.getMyPassword.setError("Password did not matched")
                    binding.getMyConfirmPassword.setError("Password did not matched")
                }
                else{
                    auth.createUserWithEmailAndPassword(getMyEmail,getMyPass)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                saveData(getName,getMyEmail,getMyPass)
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            } else {

                                Toast.makeText(this,"Signup Error",Toast.LENGTH_SHORT).show()

                            }
                        }

                }

            }


        }





    }

    fun saveData(getName: String, getMyEmail: String, getMyPass: String) {

        val user=myData(getName,getMyEmail,getMyPass)
        val myUserId=FirebaseAuth.getInstance().uid.toString()
        database.child("User").child(myUserId).setValue(user).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Data Not Saved",Toast.LENGTH_SHORT).show()
            }

        }


    }


    fun checkData():Int{

        if(binding.getMyName.text.isEmpty()){
            binding.getMyName.setError("Enter Name")
            return 0
        }
        if(binding.getMyEmial.text.isEmpty()){
            binding.getMyEmial.setError("Enter Email")
            return 0
        }
        if(binding.getMyPassword.text.isEmpty()){
            binding.getMyPassword.setError("Enter Password")
            return 0
         }
        if(binding.confirmPasswordPlaceholder.text.isEmpty()){
            binding.getMyConfirmPassword.setError("Enter Confirm Password")
            return 0
        }


        return 1
    }
}