package com.example.ratemyanimal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var mostRecentAnimal = ""
    private val animalList = Array<AnimalAndRating>(4) { AnimalAndRating("", -1.0f) }
    lateinit var animalAdapter: ArrayAdapter<String>
    private val FILE_NAME = "user_data"
    private val animalNames = listOf<String>("Dog", "Cat", "Bear", "Rabbit")
    private val animalStringArr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val secondActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val animal = data?.getStringExtra("animal")
                    val rating = data?.getFloatExtra("rating", 0.0f)
                    mostRecentAnimal = animal.toString()
                    convertAnimalStringArr(mostRecentAnimal, rating.toString().toFloat())
                    animalAdapter.notifyDataSetChanged()
                    animalList[animalNames.indexOf(animal)] =
                        AnimalAndRating(animal.toString(), rating.toString().toFloat())
                    setMostRecentLayout(animal.toString(), rating.toString().toFloat())

                }
            }
        loadData()
        animalAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, animalStringArr)
        val animalListView = findViewById<ListView>(R.id.animal_listView)
        animalListView.adapter = animalAdapter

        animalListView.setOnItemClickListener { parent, view, position, id ->
            val rating = animalList[position].rating
            val myIntent = Intent(this, MainActivity2::class.java)
            myIntent.putExtra("animal", animalList[position].animal)
            myIntent.putExtra("rating", animalList[position].rating.toFloat())
            secondActivityLauncher.launch(myIntent)
            //convertAnimalStringArr(animal, rating)
            //animalAdapter.notifyDataSetChanged()

        }


    }


    fun saveData() {
        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        for (i in 0 until animalList.size) {
            editor.putString(animalList[i].animal, animalList[i].rating.toString())
        }
        editor.putString("mostRecent", mostRecentAnimal)
        editor.apply()
    }

    fun loadData() {

        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)


        animalStringArr.clear()

        for (i in 0 until animalNames.size) {

            if (sharedPreferences.contains(animalNames[i])) {
                animalList[i] = AnimalAndRating(
                    animalNames[i],
                    sharedPreferences.getString(animalNames[i], "").toString().toFloat()
                )
                if (sharedPreferences.getString(animalNames[i], "").toString().toFloat() < -.5f) {
                    animalStringArr.add(animalNames[i])

                } else {
                    animalStringArr.add(
                        i,
                        animalList[i].animal + "- Rating: " + animalList[i].rating + "/5"
                    )

                }
                if (animalNames[i].equals(sharedPreferences.getString("mostRecent", ""))) {
                    setMostRecentLayout(
                        sharedPreferences.getString("mostRecent", "").toString(),
                        sharedPreferences.getString(animalNames[i], "").toString().toFloat()
                    )
                    findViewById<ConstraintLayout>(R.id.mostRecentLayout).isVisible = true
                }

            } else {

                animalStringArr.add(animalNames[i])
                animalList[i] = (AnimalAndRating(animalNames[i], -1.0f))
            }

        }
    }

    fun clearAllData() {
        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        for (i in 0 until animalList.size) {
            editor.putString(animalList[i].animal, animalList[i].rating.toString())
        }
        editor.apply()


    }

    fun convertAnimalStringArr(animal: String, rating: Float) {
        for (i in 0 until animalStringArr.size)
            if (animalNames[i].equals(animal)) {

                animalStringArr.removeAt(i)
                animalStringArr.add(i, "$animal - Rating: $rating/5")
                Log.d(TAG, animalStringArr[i])
                return
            }


    }

    fun clearButton(view: View) {
        findViewById<ConstraintLayout>(R.id.mostRecentLayout).isVisible = false
        animalStringArr.clear()
        for (i in 0 until animalNames.size) {
            animalStringArr.add(animalNames[i])
            animalList[i] = (AnimalAndRating(animalNames[i], -1.0f))
        }
        clearAllData()
        animalAdapter.notifyDataSetChanged()

    }

    fun setMostRecentLayout(animal: String, rating: Float) {
        val imageOfSelectedAnimal = when (animal) {
            "Dog" -> R.drawable.dog
            "Cat" -> R.drawable.cat
            "Bear" -> R.drawable.bear
            "Rabbit" -> R.drawable.rabbit

            else -> {
                R.drawable.dog
            }
        }
        findViewById<ImageView>(R.id.Animal_Image2).setImageResource(
            imageOfSelectedAnimal
        )
        findViewById<RatingBar>(R.id.ratingBar1).rating = rating.toString().toFloat()
        findViewById<TextView>(R.id.mostRecentAnimalText).text = mostRecentAnimal
        findViewById<ConstraintLayout>(R.id.mostRecentLayout).isVisible = true
    }
}