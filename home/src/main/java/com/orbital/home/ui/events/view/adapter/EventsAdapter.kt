package com.orbital.home.ui.events.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.databinding.ItemEventsBinding
import com.orbital.home.ui.events.view.adapter.viewHolder.EventsViewHolder

class EventsAdapter(private var list:List<EventsResponse.Body>,
                    private var context: Context,
                    private var fragment: Fragment):RecyclerView.Adapter<EventsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(ItemEventsBinding.inflate(LayoutInflater.from(context),parent,false),
            context,
            fragment)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}