package com.sujata.virginmoneydemo.ui.peoples

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sujata.virginmoneydemo.R
import com.sujata.virginmoneydemo.databinding.ListItemPeoplesBinding
import com.sujata.virginmoneydemo.domain.PeoplesData

class PeoplesRecyclerAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<PeoplesRecyclerAdapter.ViewHolder>(){

    private var peoplesData: List<PeoplesData> = listOf()
    private var context = (itemClickListener as PeoplesFragment).requireContext()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemPeoplesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.peopleName.text = peoplesData[position].firstName+" "+peoplesData[position].lastName
            binding.titleTV.text=peoplesData[position].jobtitle
            Glide.with(binding.root)
                .load(
                    peoplesData[position].avatar ?: AppCompatResources.getDrawable(
                        context, R.drawable.no_image
                    )
                )
                .apply(RequestOptions.overrideOf(400))
                .into(binding.thumbnailIV)
            binding.root.setOnClickListener { itemClickListener.onClick(position) }
        }
    }
    fun setListData(peoplesData: List<PeoplesData>) {
        this.peoplesData = peoplesData
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return peoplesData.size
    }

    class ViewHolder(val binding: ListItemPeoplesBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun onClick(position: Int)
    }
}