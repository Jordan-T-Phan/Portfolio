package com.example.ticketmaster30

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class commentsAdapter(private val Comments: ArrayList<ComentsDBData>) :
    RecyclerView.Adapter<commentsAdapter.MyViewHolder>() {
    private val TAG = "CommentsAdapter"
    private lateinit var fireBaseDb: FirebaseFirestore

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val comment = itemView.findViewById<TextView>(R.id.comment)
        val delete = itemView.findViewById<Button>(R.id.DeleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): commentsAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.memory_events, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: commentsAdapter.MyViewHolder, position: Int) {
        fireBaseDb = FirebaseFirestore.getInstance()
        val currentItem = Comments[position]
        val id = currentItem.id
        holder.name.text = currentItem.name
        holder.comment.text = currentItem.comment
        holder.delete.setOnClickListener(View.OnClickListener {
            fireBaseDb.collection("Comments").whereEqualTo("Name", currentItem.name)
                .whereEqualTo("Comment", currentItem.comment).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if (document != null) {
                            document.reference.delete()
                            break;
                        }
                    }
                }
            Comments.removeAt(position)
            notifyDataSetChanged()
        })


    }
    override fun getItemCount(): Int {
        return Comments.size
    }
}