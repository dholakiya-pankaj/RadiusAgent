package com.radiusagentassignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiusagentassignment.databinding.ItemExclusionBinding
import com.radiusagentassignment.db.tables.Exclusion

class ExclusionAdapter : RecyclerView.Adapter<ExclusionAdapter.MyViewHolder>() {

    private var exclusionList: List<List<Exclusion>>? = null

    inner class MyViewHolder(private val binding: ItemExclusionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exclusions: List<Exclusion>?) {
            exclusions?.let { list ->
                for (index in list.indices) {
                    val facilityId = "Facility ID: " + list[index].facilityId
                    val optionId = "Option ID:  " + list[index].optionId
                    binding.tvFacilityId.text = facilityId
                    binding.tvOptionId.text = optionId
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemExclusionBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return exclusionList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(exclusionList?.get(position))
    }

    fun setData(exclusionList: List<List<Exclusion>>?) {
        this.exclusionList = exclusionList
    }
}