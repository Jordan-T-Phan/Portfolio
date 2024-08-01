package com.example.ticketmaster30

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.Manifest
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.PackageManagerCompat

class MainActivity : AppCompatActivity() {
    private val Base_URL = "https://app.ticketmaster.com/discovery/v2/"
    private val API_KEY = "v9I9u0Cd8x7LBlYmhCoNH8SodUerO9vn"
    private val TAG = "MainActivity"
    private var serviceIsRunning = false
    private var notificationReceiver: NotificationReceiver? = null
    private lateinit var notifIntent: Intent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    startService()

    }


    fun buildAlertDialog(error: Boolean) {
        var alertTitle = ""
        var alertMessage = ""
        if (error) {
            alertTitle = "Search term missing."
            alertMessage = "Search term cannot be empty. Please enter a search term."

        } else {
            alertTitle = "Location Missing."
            alertMessage = "City cannot be empty. Please enter a city."
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage(alertMessage)
        builder.setIcon(android.R.drawable.ic_delete)
        builder.setPositiveButton("Okay") { dialog, which -> }
        val dialog = builder.create()
        dialog.show()


    }

    fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun searchButton(view: View) {
        val event = findViewById<EditText>(R.id.EventTypeText)
        val city = findViewById<EditText>(R.id.CityTypeText)
        event.hideKeyboard()
        city.hideKeyboard()
        if (event.text.isEmpty() || city.text.isEmpty()) {
            if (event.text.isEmpty()) {

                buildAlertDialog(true)
            } else {
                buildAlertDialog(false)

            }

        } else {
            val ticketmasterList = ArrayList<Eventss>()
            val adapter = TicketmasterAdapter(ticketmasterList)
            val recyclerView = findViewById<RecyclerView>(R.id.RecyclerList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)


            val retrofit = Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val ticketmasterAPI = retrofit.create(TicketmasterService::class.java)

            ticketmasterAPI.getEventInfo(
                API_KEY,
                event.text.toString(),
                city.text.toString(),
                "date,asc"
            ).enqueue(object :
                Callback<TicketmasterData> {
                override fun onResponse(
                    call: Call<TicketmasterData>,
                    response: Response<TicketmasterData>
                ) {
                    Log.d(TAG, "onresponse: $response")
                    val body = response.body()
                    if (body == null) {
                        // Log.w(TAG,tr)
                        Log.w(TAG, "Valid response was not received")
                        return
                    }
                    if (body.page.totalElements == 0) {
                        findViewById<TextView>(R.id.noResultText).isVisible = true
                        return

                    }

                    findViewById<TextView>(R.id.noResultText).isVisible = false

                    ticketmasterList.addAll(body._embedded.events)
                    adapter.notifyDataSetChanged()

                }

                override fun onFailure(call: Call<TicketmasterData>, t: Throwable) {
                    Log.d(TAG, "onFailure:$t")
                }


            })
        }


    }

    private fun checkPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "NO PERMISSIONS")


        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val requestCode = 200
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                requestCode
            )


        }

    }

    fun startService() {
        Log.d(TAG, "here")
        if (serviceIsRunning) {
            return

        }
        Log.d(TAG, "beer")
        registerMyReceiver()
        Log.d(TAG, "deer")
        notifIntent = Intent(this, NotificationService::class.java)
        startService(notifIntent)
        Log.d(TAG, "ear")
        serviceIsRunning = true


    }

    private inner class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "RECEIVED BROADCAST")
            val favoriteID = intent.getStringExtra("ID")

        }


    }

    private fun registerMyReceiver() {
        Log.d(TAG,"zere")
        val filter = IntentFilter()
        filter.addAction("favoriteMsg")


        notificationReceiver = NotificationReceiver()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            registerReceiver(notificationReceiver, filter, RECEIVER_EXPORTED)
            Log.d(TAG,"zere")

        } else {
            registerReceiver(notificationReceiver, filter)


        }


    }
  /*  override fun onPause(){
        super.onPause()
        if(notificationReceiver != null){
            Log.d(TAG,"UNRregistering the broadcast receiver")
            unregisterReceiver(NotificationReceiver())
            notificationReceiver= null
            stopService(notifIntent)

        }
        serviceIsRunning = false

    }*/

    fun goToFavorites(view: View) {

        val myIntent = Intent(this, FavoritesActivity::class.java)
        startActivity(myIntent)

    }
}

