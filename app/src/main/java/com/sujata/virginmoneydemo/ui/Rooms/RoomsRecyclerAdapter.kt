package com.sujata.virginmoneydemo.ui.Rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sujata.virginmoneydemo.databinding.ListItemRoomBinding
import com.sujata.virginmoneydemo.domain.RoomsData

class RoomsRecyclerAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<RoomsRecyclerAdapter.ViewHolder>(){

    private var roomsData: List<RoomsData> = listOf()
    private var context = (itemClickListener as RoomsFragment).requireContext()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.textView1.text = "Room No: "+roomsData[position].id
            if ((roomsData[position].isOccupied)===false){
                binding.textView2.text = "Available: YES"
            }else{
                binding.textView2.text = "Available: NO"
            }
            binding.textView3.text="Room Capacity: "+roomsData[position].maxOccupancy.toString()
            binding.root.setOnClickListener { itemClickListener.onClick(position) }
        }
    }
    fun setListData(roomsData: List<RoomsData>) {
        this.roomsData = roomsData
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return roomsData.size
    }

    class ViewHolder(val binding: ListItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun onClick(position: Int)
    }
}