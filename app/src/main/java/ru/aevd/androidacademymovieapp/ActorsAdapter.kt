package ru.aevd.androidacademymovieapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.data.Actor

class ActorsAdapter: RecyclerView.Adapter<ActorViewHolder>() {
    private var actors: List<Actor> = listOf()

    override fun getItemViewType(position: Int): Int {
        Log.d("ActorsAdapter", "getItemViewType: enter method")
        return when (actors.size) {
            0 -> {
                Log.d("ActorsAdapter", "getItemViewType: EmptyActorViewHolder")
                VIEW_TYPE_EMPTY
            }
            else -> {
                Log.d("ActorsAdapter", "getItemViewType: DataActorViewHolder")
                VIEW_TYPE_ACTORS
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return when(viewType) {
            VIEW_TYPE_ACTORS -> {
                Log.d("ActorsAdapter", "onCreateViewHolder: DataActorViewHolder")
                DataActorViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_actor, parent, false))
            }
            else -> {
                Log.d("ActorsAdapter", "onCreateViewHolder: EmptyActorViewHolder")
                EmptyActorViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_actor_empty, parent, false))
            }


        }
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        when (holder) {
            is DataActorViewHolder -> {
                holder.onBind(actors[position])
                Log.d("ActorsAdapter", "onBindViewHolder: DataActorViewHolder")
            }
            is EmptyActorViewHolder -> {/* nothing to bind */
                Log.d("ActorsAdapter", "onBindViewHolder: EmptyActorViewHolder")
            }
        }
    }

    override fun getItemCount(): Int = when(actors.size) {
        0 -> 1
        else -> actors.size
    }

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
        Log.d("ActorsAdapter", "bindActors" + actors.size)
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
                .transform(
                        CenterInside(),
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