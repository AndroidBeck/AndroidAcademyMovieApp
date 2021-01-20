package ru.aevd.androidacademymovieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.R
import ru.aevd.androidacademymovieapp.domain.entities.Actor

class ActorsAdapter: RecyclerView.Adapter<ActorViewHolder>() {
    private var actors: List<Actor> = listOf()

    override fun getItemViewType(position: Int): Int {
        return when (actors.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return when(viewType) {
            VIEW_TYPE_ACTORS ->
                DataActorViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_actor, parent, false))
            else ->
                EmptyActorViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_actor_empty, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        when (holder) {
            is DataActorViewHolder -> {
                holder.onBind(actors[position])
            }
            is EmptyActorViewHolder -> {/* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = when(actors.size) {
        0 -> 1
        else -> actors.size
    }

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }

}

abstract class ActorViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)

private class EmptyActorViewHolder(itemView: View): ActorViewHolder(itemView)

private class DataActorViewHolder(itemView: View): ActorViewHolder(itemView) {
    private val imgActor = itemView.findViewById<ImageView>(R.id.img_actor)
    private val actorName = itemView.findViewById<TextView>(R.id.actor_name)

    fun onBind(actor: Actor) {
        actorName.text = actor.name

        val cornerRadius = 30
        val imageOption = RequestOptions()
                .placeholder(R.drawable.actor_placeholder)
                .transform(
                        CenterCrop(),
                        RoundedCorners(cornerRadius)
                )
        Glide.with(context)
                .load(actor.picture)
                .apply(imageOption)
                .into(imgActor)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_ACTORS = 1