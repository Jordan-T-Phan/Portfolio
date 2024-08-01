package com.example.ticketmaster30

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAdapter(private val Events: ArrayList<FirebaseDBData>) :
    RecyclerView.Adapter<FirebaseAdapter.MyViewHolder>() {
    private val TAG = "FirebaseAdapter"
    private lateinit var fireBaseDb: FirebaseFirestore

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val image = itemView.findViewById<ImageView>(R.id.recycleImage)

        val eventName = itemView.findViewById<TextView>(R.id.EventNameText)
        val venueName = itemView.findViewById<TextView>(R.id.VenueNameText)
        val address = itemView.findViewById<TextView>(R.id.AddressText)
        val time = itemView.findViewById<TextView>(R.id.TimeText)
        val price = itemView.findViewById<TextView>(R.id.PriceText)
        val ticketLink = itemView.findViewById<Button>(R.id.seeTicketButton)
        val trash = itemView.findViewById<Button>(R.id.trash)



        init {


            itemView.setOnClickListener{

                /*  val memIntent = Intent(holder.itemView.context,Memory::class.java)
              memIntent.putExtra("ID",id)
              holder.itemView.context.startActivity(memIntent)
  */            Toast.makeText(itemView.context,"You clicked on me",Toast.LENGTH_SHORT).show()

                val memIntent = Intent(itemView.context,Memory::class.java)
                memIntent.putExtra("ID", Events[adapterPosition].id)
                itemView.context.startActivity(memIntent)
                @Override
                fun onClick(view: View) {
                    Log.d(TAG, "Hello WOrld")
                    val memIntent = Intent(view.context, Memory::class.java)
                    memIntent.putExtra("ID", Events[adapterPosition].id)
                    view.context.startActivity(memIntent)

                }


            }
        }
        /*init {
            itemView.findViewById<Button>(R.id.memories).setOnClickListener (View.OnClickListener {
              @Override
              fun onClick(view: View){
                      Log.d(TAG,"Hello WOrld")
                  val memIntent = Intent(itemView.context,Memory::class.java)
                  startActivity(itemView.context,memIntent,null)

              }


            })
        }*/


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_row_ticket_event, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fireBaseDb = FirebaseFirestore.getInstance()
        val currentItem = Events[position]
        val id = currentItem.id
        holder.eventName.text = currentItem.eventName
        holder.venueName.text = currentItem.venueName
        holder.address.text = currentItem.address
        holder.time.text = currentItem.time
        Log.d(TAG, "Hello WOrld")
        if (currentItem.price == null || currentItem.price == "" || currentItem.price == "null") {
            holder.price.isVisible = false

        } else {
            holder.price.text = currentItem.price
        }
        holder.trash.setOnClickListener(View.OnClickListener {
            fireBaseDb.collection("VenueEvents").whereEqualTo("id", id).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if (document != null) {
                            document.reference.delete()
                            fireBaseDb.collection("Comments").whereEqualTo("id", id).get()
                                .addOnSuccessListener { bocuments ->
                                    for (bocument in bocuments) {

                                        if (bocument != null) {
                                            bocument.reference.delete()

                                        }

                                    }
                                }

                            break

                        } else {
                            Log.d(TAG, "no such document")
                        }


                    }
                    Events.removeAt(position)
                    notifyDataSetChanged()


                }


        })


        /*      @Override
              fun memButton(view: View){

                  val memIntent = Intent(view.context,Memory::class.java)
                  startActivity(view.context,memIntent,null)
              }
              holder.memory.setOnClickListener ( View.OnClickListener {
                  @Override
                  public void onClick(View v) {


                  }
                  val memIntent = Intent(holder.itemView.context,Memory::class.java)
                  if (holder.itemView.context != null) {
                      startActivity(holder.itemView.context, memIntent, null)

                  }

              })*/


        holder.ticketLink.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(currentItem.ticketLink)
            if (holder.itemView.context != null) {
                startActivity(holder.itemView.context, browserIntent, null)

            }

        })

        val highestQualityImage =
            currentItem.image
        val context = holder.itemView.context

        if (highestQualityImage != null) {
            Glide.with(context)
                .load(highestQualityImage.toString()).placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.image)
        }
    }


    override fun getItemCount(): Int {
        return Events.size
    }

}
