package com.damonisch.wa.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damonisch.wa.databinding.ItemCardBinding

class SettingsAdapter(
    private val items: List<Item>,
    private val onClick: (Item) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.Holder>() {

    data class Item(
        val title: String,
        val subtitle: String,
        val xmlRes: Int,  // ← DEVE TER ESSE PARÂMETRO
        val iconRes: Int
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class Holder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.cardTitle.text = item.title
            binding.cardSubtitle.text = item.subtitle
            binding.cardIcon.setImageResource(item.iconRes)
            binding.root.setOnClickListener { onClick(item) }
        }
    }
}