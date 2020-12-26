package ru.aevd.androidacademymovieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.aevd.androidacademymovieapp.data.Actor

class ActorsAdapter: RecyclerView.Adapter<ActorViewHolder>() {
    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }

}

class ActorViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
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