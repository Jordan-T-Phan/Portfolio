package com.example.ticketmaster30

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class FavoritesActivity : AppCompatActivity() {
    private lateinit var fireBaseDB: FirebaseFirestore
    private var TAG = "FavoriteActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorites)
        fireBaseDB = FirebaseFirestore.getInstance()

        val ticketmasterList = ArrayList<FirebaseDBData>()
        val adapter = FirebaseAdapter(ticketmasterList)
        val recyclerView = findViewById<RecyclerView>(R.id.RecycleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        fireBaseDB.collection("VenueEvents").orderBy("id").get().addOnSuccessListener { documents ->

            for (document in documents) {
                ticketmasterList.add(
                    FirebaseDBData(
                        document.get("address").toString(),
                        document.get("eventName").toString(),
                        document.get("image").toString(),
                        document.get("price").toString(),
                        document.get("ticketLink").toString(),
                        document.get("time").toString(),
                        document.get("venueName").toString(),
                        document.get("id").toString()
                    )
                )
                Log.d(TAG,ticketmasterList.get(ticketmasterList.size-1).address.toString())
            }

            Log.d(TAG,ticketmasterList.size.toString())
            if (ticketmasterList.isEmpty()) {
                findViewById<TextView>(R.id.noResults).isVisible = true
            } else {
                findViewById<TextView>(R.id.noResults).isVisible = false
            }
            adapter.notifyDataSetChanged()
        }

    }

}
