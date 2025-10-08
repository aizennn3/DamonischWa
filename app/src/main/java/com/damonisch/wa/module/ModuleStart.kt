package com.damonisch.wa.module

import com.damonisch.wa.module.core.WppUtils
import com.damonisch.wa.module.hooks.core.HooksLoader
import com.damonisch.wa.module.hooks.customization.HideArchivedChatsHook
import com.damonisch.wa.module.hooks.customization.SeparateGroupsHook
import com.damonisch.wa.module.hooks.functions.AntiRevokeHook
import com.damonisch.wa.module.hooks.functions.AntiViewOnceHook
import com.damonisch.wa.module.hooks.functions.CallPrivacyHook
import com.damonisch.wa.module.hooks.functions.CustomPrivacyHook
import com.damonisch.wa.module.hooks.media.DisableFlagSecureHook
import com.damonisch.wa.module.hooks.media.DownloadStatusHook
import com.damonisch.wa.module.hooks.media.DownloadViewOnceHook
import com.damonisch.wa.module.hooks.media.MediaQualityHook
import com.damonisch.wa.module.hooks.others.MenuHook
import com.damonisch.wa.module.hooks.others.OthersHook
import com.damonisch.wa.module.hooks.others.PinnedLimit
import com.damonisch.wa.module.hooks.privacy.*
import com.damonisch.wa.module.hooks.utils.DebugHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import de.robv.android.xposed.XposedBridge

class ModuleStart : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != WppUtils.PACKAGE_NAME) return

        try {
            XposedBridge.log("WaRevamp: Module loading for WhatsApp")

            val hooksLoader = HooksLoader(getAllHooks())
            hooksLoader.invoke(lpparam.classLoader)

            XposedBridge.log("WaRevamp: All hooks loaded successfully")

        } catch (error: Throwable) {
            XposedBridge.log("WaRevamp Error: ${error.message}")
            error.printStackTrace()
        }
    }

    private fun getAllHooks(): List<com.damonisch.wa.module.core.WppCallback> {
        return listOf(
            // Debug primeiro para verificar classes
            DebugHook(),

            // Customization
            HideArchivedChatsHook(),
            SeparateGroupsHook(),

            // Functions
            AntiRevokeHook(),
            AntiViewOnceHook(),
            CallPrivacyHook(),
            CustomPrivacyHook(),

            // Media
            DisableFlagSecureHook(),
            DownloadStatusHook(),
            DownloadViewOnceHook(),
            MediaQualityHook(),

            // Others
            MenuHook(),
            OthersHook(),
            PinnedLimit(),

            // Privacy - Agora com hooks baseados no WaEnhancer
            DndModeHook(),
            FreezeLastSeenHook(),  // Baseado no WaEnhancer
            HideReadHook(),        // Baseado no WaEnhancer
            HideReceiptHook(),     // Baseado no WaEnhancer
            HideTypingRecordingHook()
        )
    }
}