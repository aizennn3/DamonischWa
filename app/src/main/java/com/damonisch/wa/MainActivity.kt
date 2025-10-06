package com.damonisch.wa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.damonisch.wa.adapters.SettingsAdapter
import com.damonisch.wa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = getColor(R.color.background)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CÓDIGO DA TOOLBAR REMOVIDO

        val items = listOf(
            SettingsAdapter.Item("Privacidade", "Configurações de privacidade", R.xml.privacy_preferences, R.drawable.ic_privacy),
            SettingsAdapter.Item("Mídia", "Configurações de mídia", R.xml.media_preferences, R.drawable.ic_media),
            SettingsAdapter.Item("Funções", "Funções especiais", R.xml.functions_preferences, R.drawable.ic_functions),
            SettingsAdapter.Item("Limpeza", "Ferramentas de limpeza", R.xml.cleaner_preferences, R.drawable.ic_cleaner),
            SettingsAdapter.Item("Extras", "Configurações extras", R.xml.extras_preferences, R.drawable.ic_extras),
            SettingsAdapter.Item("Sobre", "Sobre o app", R.xml.about_preferences, R.drawable.ic_about),
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = SettingsAdapter(items) { item ->
            val i = Intent(this, InternalSettingsActivity::class.java)
            i.putExtra(InternalSettingsActivity.EXTRA_XML_RES, item.xmlRes)
            i.putExtra(InternalSettingsActivity.EXTRA_TITLE, item.title)
            startActivity(i)
        }
    }
}