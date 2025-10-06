package com.damonisch.wa

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceViewHolder
import androidx.preference.SwitchPreferenceCompat
import com.google.android.material.switchmaterial.SwitchMaterial

class CustomSwitchPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwitchPreferenceCompat(context, attrs) {

    init {
        layoutResource = R.layout.custom_switch_preference
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        val switch = holder.findViewById(R.id.switch_widget) as? SwitchMaterial

        switch?.let {
            // REMOVE completamente qualquer tint do Material Design
            it.thumbTintList = null
            it.trackTintList = null
            it.backgroundTintList = null

            // FORÇA os drawables personalizados
            it.thumbDrawable = ContextCompat.getDrawable(context, R.drawable.switch_thumb)
            it.trackDrawable = ContextCompat.getDrawable(context, R.drawable.switch_track)

            // Remove qualquer transformação do Material
            it.setTrackResource(R.drawable.switch_track)
            it.setThumbResource(R.drawable.switch_thumb)


            it.setOnCheckedChangeListener(null)
            it.isChecked = isChecked
            it.setOnCheckedChangeListener { _, newCheckedState ->
                if (callChangeListener(newCheckedState)) {
                    isChecked = newCheckedState
                }
            }
        }
    }
}