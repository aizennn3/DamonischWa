package com.damonisch.wa.module.hooks.others

import android.view.Menu
import android.view.MenuItem
import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class MenuHook : HooksBase() {

    override fun executeHook() {
        val mainActivityClass = findWppClass("Main")

        XposedHelpers.findAndHookMethod(
            mainActivityClass,
            "onCreateOptionsMenu",
            Menu::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val menu = param.args[0] as? Menu ?: return

                    // Adicionar itens customizados ao menu
                    addCustomMenuItems(menu)
                }
            }
        )

        XposedHelpers.findAndHookMethod(
            mainActivityClass,
            "onOptionsItemSelected",
            MenuItem::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val item = param.args[0] as? MenuItem ?: return

                    if (handleCustomMenuItem(item)) {
                        param.result = true
                    }
                }
            }
        )
    }

    private fun addCustomMenuItems(menu: Menu) {
        // Adicionar itens de menu customizados
        // menu.add(0, 1001, 0, "Custom Item")
    }

    private fun handleCustomMenuItem(item: MenuItem): Boolean {
        return when (item.itemId) {
            1001 -> {
                // Ação para o item customizado
                true
            }
            else -> false
        }
    }
}