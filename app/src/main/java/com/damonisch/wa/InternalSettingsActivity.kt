package com.damonisch.wa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.damonisch.wa.databinding.ActivityInternalSettingsBinding
import com.google.android.material.appbar.MaterialToolbar

class InternalSettingsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_XML_RES = "extra_xml_res"
        const val EXTRA_TITLE = "extra_title"
    }

    private lateinit var binding: ActivityInternalSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInternalSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val xmlRes = intent.getIntExtra(EXTRA_XML_RES, -1)
        val title = intent.getStringExtra(EXTRA_TITLE)

        // Configura a Toolbar
        val toolbar = binding.toolbar
        toolbar.title = title ?: "Configurações"

        // Configura o botão voltar
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Carrega o fragment com as preferences
        if (savedInstanceState == null && xmlRes != -1) {
            val frag = SettingsFragment.newInstance(xmlRes)
            supportFragmentManager.beginTransaction()
                .replace(R.id.settings_container, frag)
                .commit()
        }
    }
}