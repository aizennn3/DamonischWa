package com.damonisch.wa

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder

class CustomPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Preference(context, attrs, defStyleAttr) {

    init {
        layoutResource = R.layout.custom_preference_layout
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        // Aplica os mesmos tamanhos dos switches/botões
        val titleView = holder.findViewById(android.R.id.title) as? android.widget.TextView
        val summaryView = holder.findViewById(android.R.id.summary) as? android.widget.TextView
        val arrowView = holder.findViewById(R.id.arrow_icon) as? ImageView

        // MESMOS TAMANHOS DOS SWITCHES (16sp título, 14sp summary)
        titleView?.let {
            it.textSize = 16f // Igual aos switches
            it.setTextColor(ContextCompat.getColor(context, R.color.text_primary))
        }

        summaryView?.let {
            it.textSize = 14f // Igual aos switches
            it.setTextColor(ContextCompat.getColor(context, R.color.text_secondary))
        }

        // Seta igual aos cards
        arrowView?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_forward))
        arrowView?.setColorFilter(ContextCompat.getColor(context, R.color.text_secondary))
    }
}