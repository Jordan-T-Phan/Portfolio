package com.example.ratemyanimal

import android.app.Activity
import android.content.Intent
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar

class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity2"
    private var animal = ""
    private var ratingAmount = 0.0F
    private val FILE_NAME = "user_data"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        animal = intent.getStringExtra("animal").toString()
        ratingAmount = intent.getFloatExtra("rating", 0.0f)
        val imageOfSelectedAnimal = when (animal) {
            "Dog" -> R.drawable.dog
            "Cat" -> R.drawable.cat
            "Bear" -> R.drawable.bear
            "Rabbit" -> R.drawable.rabbit

            else -> {
                R.drawable.dog
            }
        }
        findViewById<ImageView>(R.id.Animal_Image1).setImageResource(imageOfSelectedAnimal)
        findViewById<RatingBar>(R.id.ratingBar2).rating = ratingAmount

    }

    fun returnDataToFirstActivity(view: View) {
        val myIntent = Intent()
        myIntent.putExtra("animal", animal)
        ratingAmount = findViewById<RatingBar>(R.id.ratingBar2).rating
        myIntent.putExtra("rating", ratingAmount)
        setResult(Activity.RESULT_OK, myIntent)
        Log.d(TAG, ratingAmount.toString())
        saveData()
        finish()

    }

    fun saveData() {
        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(animal, ratingAmount.toString())

        editor.putString("mostRecent", animal)
        editor.apply()
    }
}