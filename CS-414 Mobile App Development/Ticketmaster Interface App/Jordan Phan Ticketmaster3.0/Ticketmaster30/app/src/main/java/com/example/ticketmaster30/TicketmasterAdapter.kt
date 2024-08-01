package com.example.ticketmaster30

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class TicketmasterAdapter(private val Events: ArrayList<Eventss>) :
    RecyclerView.Adapter<TicketmasterAdapter.MyViewHolder>() {
    private val TAG = "TicketmasterAdapter"
    private var serviceIsRunning = false
    private lateinit var fireBaseDb: FirebaseFirestore
private lateinit var notificationManager: NotificationManager
private lateinit var notificationChannel: NotificationChannel

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.recycleImage)

        val eventName = itemView.findViewById<TextView>(R.id.EventNameText)
        val venueName = itemView.findViewById<TextView>(R.id.VenueNameText)
        val address = itemView.findViewById<TextView>(R.id.AddressText)
        val time = itemView.findViewById<TextView>(R.id.TimeText)
        val price = itemView.findViewById<TextView>(R.id.PriceText)
        val ticketLink = itemView.findViewById<Button>(R.id.seeTicketButton)
        val star = itemView.findViewById<Button>(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_ticket_event, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fireBaseDb = FirebaseFirestore.getInstance()
        val currentItem = Events[position]
        val eventNameText = "${currentItem.name}"
        holder.eventName.text = eventNameText
        val venueNameText = "${currentItem._embedded.venues[0].name}"
        holder.venueName.text = venueNameText
        val addressText =
            "Address: ${currentItem._embedded.venues[0].address.line1}, ${currentItem._embedded.venues[0].city.name}, ${currentItem._embedded.venues[0].state.name}"
        holder.address.text = addressText

        val id = currentItem.id
        val dateString = currentItem.dates.start.localDate.toString()
        val timeString = currentItem.dates.start.localTime.toString()
        val simpleDateFormat = java.text.SimpleDateFormat("yyyy-MM-dd")
        val date = simpleDateFormat.parse(dateString)
        val simpleTimeFormat = java.text.SimpleDateFormat("HH:mm:ss")
        val time = simpleTimeFormat.parse(timeString)
        val displayDate = java.text.SimpleDateFormat("yyyy/MM/dd").format(date)
        val displayTime = java.text.SimpleDateFormat("h:mm aa").format(time)

        val timeText = "Date: ${displayDate} @ ${displayTime}"
        holder.time.text = timeText

        if (currentItem.priceRanges == null) {
            holder.price.isVisible = false

        } else {
            holder.price.text =
                "Price Range: \$${currentItem.priceRanges[0].min} - \$${currentItem.priceRanges[0].max}"
        }

        holder.ticketLink.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(currentItem.url)
            if (holder.itemView.context != null) {
                startActivity(holder.itemView.context, browserIntent, null)

            }

        })

        val highestQualityImage =
            currentItem.images.maxByOrNull { it.width.toInt() * it.height.toInt() }
        val context = holder.itemView.context

        if (highestQualityImage != null) {
            Glide.with(context)
                .load(highestQualityImage.url).placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.image)
        }
        holder.star.setOnClickListener(View.OnClickListener {
            val favorites = fireBaseDb.collection("VenueEvents")
            Log.d(TAG, "B%ksdlfj;asf")
            favorites.whereEqualTo("id", id.toString()).get().addOnSuccessListener { documents ->
                if (documents.size() <= 0) {
                    Log.d(TAG, "THERE")
                    var priceText: String? = null
                    if (currentItem.priceRanges != null) {
                        priceText =
                            "Price Range: \$${currentItem.priceRanges[0].min} - \$${currentItem.priceRanges[0].max}"
                    }
                    val favorite = hashMapOf(
                        "address" to addressText.toString(),
                        "eventName" to eventNameText.toString(),
                        "id" to id.toString(),
                        "image" to highestQualityImage?.url.toString(),
                        "price" to priceText.toString(),
                        "ticketLink" to currentItem.url.toString(),
                        "time" to timeText.toString(),
                        "venueName" to venueNameText.toString()
                    )

                    //  val documentId = favorites.document().id
                    //favorites.document(documentId).set(favorite)
                    Log.d(TAG, "Favorite has been made")

                    val documentId = favorites.document().id
                    favorites.document(documentId).set(favorite)

                   /*
                    val pIntent = Intent(holder.itemView.context,Memory::class.java)
                    pIntent.putExtra("ID",id)
                    val pendingIntent = PendingIntent.getActivity(this,0,pIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                    val contentView = RemoteViews(packageName,R.layout.activity_memory)*/


                }
            }

        })



    }


    override fun getItemCount(): Int {
        return Events.size
    }

}
