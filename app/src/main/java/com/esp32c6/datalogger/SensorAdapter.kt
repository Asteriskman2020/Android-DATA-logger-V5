package com.esp32c6.datalogger

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter : RecyclerView.Adapter<SensorAdapter.ViewHolder>() {

    private val records = mutableListOf<SensorRecord>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIndex: TextView = view.findViewById(R.id.tvIndex)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val tvTempAht: TextView = view.findViewById(R.id.tvTempAht)
        val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
        val tvPressure: TextView = view.findViewById(R.id.tvPressure)
        val tvTempBmp: TextView = view.findViewById(R.id.tvTempBmp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.tvIndex.text = record.index.toString()
        holder.tvTime.text = record.toDisplayTime()
        holder.tvTempAht.text = "%.1f".format(record.tempAht)
        holder.tvHumidity.text = "%.1f".format(record.humidity)
        holder.tvPressure.text = "%.1f".format(record.pressure)
        holder.tvTempBmp.text = "%.1f".format(record.tempBmp)

        holder.itemView.setBackgroundColor(
            if (position % 2 == 0) Color.WHITE else Color.parseColor("#EEEEEE")
        )
    }

    override fun getItemCount(): Int = records.size

    fun updateData(newList: List<SensorRecord>) {
        records.clear()
        records.addAll(newList)
        notifyDataSetChanged()
    }

    fun clear() {
        records.clear()
        notifyDataSetChanged()
    }
}
