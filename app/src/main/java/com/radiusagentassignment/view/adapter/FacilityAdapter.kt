package com.radiusagentassignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.radiusagentassignment.databinding.ItemFacilitiesBinding
import com.radiusagentassignment.db.tables.Exclusion
import com.radiusagentassignment.db.tables.Facilities
import io.realm.RealmList

class FacilityAdapter : RecyclerView.Adapter<FacilityAdapter.MyViewHolder>() {

    private var facilitiesRealmList: RealmList<Facilities>? = null
    private var exclusionList: List<List<Exclusion>>? = null
    private val exclusionAdapter: ExclusionAdapter by lazy {
        ExclusionAdapter()
    }

    fun setData(
        facilitiesRealmList: RealmList<Facilities>,
        exclusionList: List<List<Exclusion>>
    ) {
        this.facilitiesRealmList = facilitiesRealmList
        this.exclusionList = exclusionList
    }

    inner class MyViewHolder(private val binding: ItemFacilitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(facilities: Facilities?, position: Int) {
            facilities?.let {
                binding.tvFacilityName.text = it.name
                it.options?.let { options ->
                    val radioGroup = RadioGroup(itemView.context)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(40, 0, 0, 0)
                    for (index in options.indices) {
                        val radioButton = RadioButton(itemView.context)
                        radioButton.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        radioButton.text = options[index]?.name
                        radioButton.id = index

                        exclusionList?.let { myList ->
                            for (exclusions in myList) {
                                for (exclusionIndex in exclusions.indices) {
                                    if (options[index]?.id == exclusions[exclusionIndex].optionId) {
                                        radioButton.isEnabled = false
                                        break
                                    }
                                }
                            }
                        }
                        radioGroup.addView(radioButton)
                    }
                    binding.optionContainer.addView(radioGroup)
                }
            }
            if (position == facilitiesRealmList?.size?.minus(1)) {
                binding.run {
                    tvExclusionHeader.isVisible = true
                    exclusionsRecyclerView.isVisible = true
                    exclusionsRecyclerView.adapter = exclusionAdapter
                    exclusionAdapter.setData(exclusionList)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemFacilitiesBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return facilitiesRealmList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(facilitiesRealmList?.get(position), position)
    }
}