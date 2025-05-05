package com.example.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RoverExpandableAdapter(
    private var rovers: MutableList<RoverParent>
) : RecyclerView.Adapter<RoverExpandableAdapter.RoverViewHolder>() {

    inner class RoverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val roverName: TextView = view.findViewById(R.id.tv_rover_name)
        private val landingDate: TextView = view.findViewById(R.id.tv_landing_date)
        private val launchDate: TextView = view.findViewById(R.id.tv_launch_date)
        private val detailsLayout: View = view.findViewById(R.id.detailsLayout)  // ✅ FIXED!
        private val recyclerViewCameras: RecyclerView = view.findViewById(R.id.recyclerViewCameras)

        fun bind(roverParent: RoverParent, position: Int) {
            val rover = roverParent.rover
            roverName.text = rover.name
            landingDate.text = "Landing Date: ${rover.landingDate}"
            launchDate.text = "Launch Date: ${rover.launchDate}"

            detailsLayout.visibility = if (roverParent.isExpanded) View.VISIBLE else View.GONE

            recyclerViewCameras.layoutManager = LinearLayoutManager(itemView.context)
            recyclerViewCameras.adapter = CameraAdapter(rover.cameras)

            itemView.setOnClickListener {
                rovers[position] = rovers[position].copy(isExpanded = !rovers[position].isExpanded)
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rover, parent, false)
        return RoverViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        holder.bind(rovers[position], position)
    }

    override fun getItemCount() = rovers.size

    fun updateData(newRovers: List<RoverParent>) {
        rovers.clear()
        rovers.addAll(newRovers)
        notifyDataSetChanged()
    }
}
