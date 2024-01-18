package com.aditya.projectz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.projectz.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var auth=Firebase.auth
        database=Firebase.database.reference

        binding.logoutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,loginAct::class.java))
            finish()

        }

        val myUid=FirebaseAuth.getInstance().uid.toString()
        database.child("User").child(myUid).get().addOnSuccessListener{

            val name=it.child("name").value.toString()
            val email=it.child("email").value.toString()
            val pass=it.child("password").value.toString()

            binding.myEmail.text=email
            binding.myName.text=name



        }



    }
}