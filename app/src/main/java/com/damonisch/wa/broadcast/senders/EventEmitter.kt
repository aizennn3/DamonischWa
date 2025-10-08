package com.damonisch.wa.broadcast.senders

import android.content.Context
import android.content.Intent
import android.os.Build
import com.damonisch.wa.broadcast.Events
import com.damonisch.wa.broadcast.Receivers

object EventEmitter {

    fun sendModuleStarted(context: Context) {
        val intent = Intent(Events.MODULE_STARTED).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_MODULE)
        }
        context.sendBroadcast(intent)
    }

    fun sendModuleStopped(context: Context) {
        val intent = Intent(Events.MODULE_STOPPED).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_MODULE)
        }
        context.sendBroadcast(intent)
    }

    fun sendModuleError(context: Context, error: String) {
        val intent = Intent(Events.MODULE_ERROR).apply {
            putExtra(Events.EXTRA_ERROR_MESSAGE, error)
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_MODULE)
        }
        context.sendBroadcast(intent)
    }

    fun sendHookExecuted(context: Context, hookName: String) {
        val intent = Intent(Events.HOOK_EXECUTED).apply {
            putExtra(Events.EXTRA_HOOK_NAME, hookName)
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_HOOKS)
        }
        context.sendBroadcast(intent)
    }

    fun sendHookError(context: Context, hookName: String, error: String) {
        val intent = Intent(Events.HOOK_ERROR).apply {
            putExtra(Events.EXTRA_HOOK_NAME, hookName)
            putExtra(Events.EXTRA_ERROR_MESSAGE, error)
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_HOOKS)
        }
        context.sendBroadcast(intent)
    }

    fun sendWhatsAppStarted(context: Context) {
        val intent = Intent(Events.WHATSAPP_STARTED).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_WHATSAPP)
        }
        context.sendBroadcast(intent)
    }

    fun sendWhatsAppStopped(context: Context) {
        val intent = Intent(Events.WHATSAPP_STOPPED).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_WHATSAPP)
        }
        context.sendBroadcast(intent)
    }

    fun sendMessageReceived(context: Context) {
        val intent = Intent(Events.WHATSAPP_MESSAGE_RECEIVED).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_WHATSAPP)
        }
        context.sendBroadcast(intent)
    }

    fun sendMessageSent(context: Context) {
        val intent = Intent(Events.WHATSAPP_MESSAGE_SENT).apply {
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
            addCategory(Receivers.CATEGORY_WHATSAPP)
        }
        context.sendBroadcast(intent)
    }

    fun sendSettingsChanged(context: Context, key: String, value: String) {
        val intent = Intent(Events.SETTINGS_CHANGED).apply {
            putExtra(Events.EXTRA_SETTING_KEY, key)
            putExtra(Events.EXTRA_SETTING_VALUE, value)
            putExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
        }
        context.sendBroadcast(intent)
    }
}