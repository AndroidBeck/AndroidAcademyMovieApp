package ru.aevd.androidacademymovieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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