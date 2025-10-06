package com.damonisch.wa.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damonisch.wa.databinding.CardContainerBinding

class InternalSettingsAdapter(
    private val items: List<SettingItem>,
    private val onClick: (SettingItem) -> Unit
) : RecyclerView.Adapter<InternalSettingsAdapter.Holder>() {

    data class SettingItem(
        val title: String,
        val subtitle: String,
        val switchState: Boolean = false
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CardContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class Holder(private val binding: CardContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingItem) {
            binding.cardTitle.text = item.title
            binding.cardSubtitle.text = item.subtitle
            binding.root.setOnClickListener { onClick(item) }
        }
    }
}