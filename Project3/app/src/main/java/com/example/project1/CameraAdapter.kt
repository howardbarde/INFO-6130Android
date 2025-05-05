package com.example.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CameraAdapter(private val cameras: List<Camera>) : RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {

    inner class CameraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cameraName: TextView = view.findViewById(R.id.tv_camera_name)

        fun bind(camera: Camera) {
            cameraName.text = camera.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_camera, parent, false)
        return CameraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.bind(cameras[position])
    }

    override fun getItemCount(): Int = cameras.size
}
