package ru.aevd.androidacademymovieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ActorViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
    private val imgActor = itemView.findViewById<ImageView>(R.id.img_actor)
    private val actorName = itemView.findViewById<TextView>(R.id.actor_name)

    fun onBind(actor: Actor) {
        actorName.text = actor.name

        Glide.with(context)
                .load(actor.img_path)
                .into(imgActor)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context