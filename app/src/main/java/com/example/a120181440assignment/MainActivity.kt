package com.example.a120181440assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Firebase.firestore

        buttonAdd.setOnClickListener {
            if (theName.text.isNotEmpty()&&theEmail.text.isNotEmpty()) {
                val name = theName.text.toString()
                val email =theEmail.text.toString()


                addData(name, email)
            }else{
                Toast.makeText(this,"Please Enter All Of Data", Toast.LENGTH_SHORT).show()
            }

        }

        val    data = mutableListOf<users>()
        db.collection("users").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot){
                var name = document.get("name") as String
                var email = document.get("email") as String
                data.add(users(1,name,email))
            }

            val adapter = listViewAdapter(this,data)
            LV.adapter = adapter

        }.addOnFailureListener {exception ->
            Toast.makeText(this,"faild",Toast.LENGTH_SHORT).show()
        }





    }fun addData(name:String ,email:String){
        val document = hashMapOf("name" to name,"email" to email)
        db.collection("users")
            .add(document)
            .addOnSuccessListener {documentReference ->
                Toast.makeText(this," Success", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
                Toast.makeText(this,"faild", Toast.LENGTH_SHORT).show()
            }
    }






}