package com.damonisch.wa

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        private const val ARG_XML = "arg_xml"
        fun newInstance(xmlRes: Int): SettingsFragment {
            val f = SettingsFragment()
            val b = Bundle()
            b.putInt(ARG_XML, xmlRes)
            f.arguments = b
            return f
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val xml = arguments?.getInt(ARG_XML) ?: return
        setPreferencesFromResource(xml, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Remove TODOS os fundos e espaçamentos
        listView?.setPadding(0, 0, 0, 0)
        listView?.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        listView?.clipToPadding = false

        // Habilita scroll interno se necessário
        listView?.isScrollContainer = true
    }
}