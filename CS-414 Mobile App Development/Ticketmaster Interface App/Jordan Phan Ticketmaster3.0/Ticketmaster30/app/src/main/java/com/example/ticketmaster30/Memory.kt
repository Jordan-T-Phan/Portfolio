package com.example.ticketmaster30

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class Memory : AppCompatActivity() {
    private val TAG = "Memory"
    private lateinit var fireBaseDB: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_memory)
        fireBaseDB = FirebaseFirestore.getInstance()
        val id = intent.getStringExtra("ID")
        val image = findViewById<ImageView>(R.id.imageName)
        val eventName = findViewById<TextView>(R.id.EventName)
        val venueName = findViewById<TextView>(R.id.VenueName)
        val address = findViewById<TextView>(R.id.AddressName)
        val date = findViewById<TextView>(R.id.DateName)
        val price = findViewById<TextView>(R.id.PriceName)
        if (id != null) {
            Log.d(TAG, id)
        }
        //val seeTicket = findViewById<Button>(R.id.seeTicketButton)

        //val fireBaseList = ArrayList<FirebaseDBData>()
        fireBaseDB.collection("VenueEvents").whereEqualTo("id", id).get()
            .addOnSuccessListener { documents ->
                Log.d("Bobby", "fireBaseList.get(fireBaseList.size - 1).address.toString()")
                for (document in documents) {
                    eventName.text = document.get("eventName").toString()
                    venueName.text = document.get("venueName").toString()
                    address.text = document.get("address").toString()
                    val highestQualityImage =
                        document.get("image").toString()
                    val context = this
                    date.text = document.get("time").toString()
                    if (document.get("price") == null || document.get("price") == "" || document.get(
                            "price"
                        ) == "null"
                    ) {
                        price.isVisible = false

                    } else {
                        price.text = document.get("price").toString()
                    }
                    if (highestQualityImage != null) {
                        Glide.with(context)
                            .load(highestQualityImage.toString())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(image)

                    }

                    // Log.d("Bobby", fireBaseList.get(fireBaseList.size - 1).address.toString())
                }

                // Log.d(TAG, fireBaseList.size.toString())
            }
        //   Log.d(TAG, fireBaseList.size.toString())


        // eventName.text = fireBaseList.get(fireBaseList.size - 1).address
        //  venueName.text = fireBaseList[0].venueName
        //address.text = fireBaseList[0].address
        //date.text = fireBaseList.get(0).time/*
        /*    if (fireBaseList[0].price == null||fireBaseList[0].price == ""||fireBaseList[0].price =="null") {
                price.isVisible = false

            } else {
                price.text = fireBaseList[0].price
            }

            val highestQualityImage =
                fireBaseList[0].image
            val context = this

            if (highestQualityImage != null) {
                Glide.with(context)
                    .load(highestQualityImage.toString()).placeholder(R.drawable.ic_launcher_foreground)
                    .into(image)

            }*/


        val commentsList = ArrayList<ComentsDBData>()
        val adapter = commentsAdapter(commentsList)
        val recyclerView = findViewById<RecyclerView>(R.id.memRecycle)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        fireBaseDB.collection("Comments").whereEqualTo("ID", id).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    commentsList.add(
                        ComentsDBData(
                            document.get("ID").toString(),
                            document.get("Name").toString(),
                            document.get("Comment").toString()
                        )
                    )
                    adapter.notifyDataSetChanged()
                     Log.d(TAG, document.get("Name").toString())
                }

                adapter.notifyDataSetChanged()
                if (commentsList.size <= 0) {
                    findViewById<TextView>(R.id.noResult2).isVisible = true
                } else {
                    findViewById<TextView>(R.id.noResult2).isVisible = false
                }
            }


    }

    fun buildAlertDialog(error: Boolean) {
        var alertTitle = ""
        var alertMessage = ""
        if (error) {
            alertTitle = "Name term missing."
            alertMessage = "Name term cannot be empty. Please enter a name term."

        } else {
            alertTitle = "Comments Missing."
            alertMessage = "Comments cannot be empty. Please enter a comment."
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage(alertMessage)
        builder.setIcon(android.R.drawable.ic_delete)
        builder.setPositiveButton("Okay") { dialog, which -> }
        val dialog = builder.create()
        dialog.show()


    }

    fun addButton(view: View) {

        val name = findViewById<EditText>(R.id.Name)
        val commenter = findViewById<EditText>(R.id.Comments)
        name.hideKeyboard()
        commenter.hideKeyboard()
        if (name.text.isEmpty() || commenter.text.isEmpty()) {
            if (name.text.isEmpty()) {

                buildAlertDialog(true)
            } else {
                buildAlertDialog(false)

            }
        } else {
            Log.d(TAG,"I MADE IT HERE")
            val comments = fireBaseDB.collection("Comments")
            val comment = hashMapOf(
                "ID" to intent.getStringExtra("ID"),
                "Name" to findViewById<EditText>(R.id.Name).text.toString(),
                "Comment" to findViewById<EditText>(R.id.Comments).text.toString()


            )
            val documentId = comments.document().id
            comments.document(documentId).set(comment)
            findViewById<RecyclerView>(R.id.memRecycle).adapter?.notifyDataSetChanged()

        }
    }

    fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}