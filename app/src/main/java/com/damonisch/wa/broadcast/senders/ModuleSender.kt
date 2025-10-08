package com.damonisch.wa.broadcast.senders

import android.content.Context
import android.content.Intent
import com.damonisch.wa.broadcast.Receivers
import com.damonisch.wa.broadcast.Senders

object ModuleSender {

    fun sendModuleEvent(context: Context, eventType: String, error: String? = null) {
        val intent = Intent(Receivers.ACTION_MODULE_EVENT).apply {
            `package` = Senders.PACKAGE_MODULE
            putExtra("event_type", eventType)
            error?.let { putExtra("error", it) }
        }

        context.sendBroadcast(intent)
    }

    fun sendHookEvent(context: Context, hookName: String, eventType: String, error: String? = null) {
        val intent = Intent(Receivers.ACTION_HOOK_EVENT).apply {
            `package` = Senders.PACKAGE_MODULE
            putExtra("hook_name", hookName)
            putExtra("event_type", eventType)
            error?.let { putExtra("error", it) }
        }

        context.sendBroadcast(intent)
    }

    fun notifyModuleStarted(context: Context) {
        sendModuleEvent(context, "start")
        EventEmitter.sendModuleStarted(context)
    }

    fun notifyModuleStopped(context: Context) {
        sendModuleEvent(context, "stop")
        EventEmitter.sendModuleStopped(context)
    }

    fun notifyModuleError(context: Context, error: String) {
        sendModuleEvent(context, "error", error)
        EventEmitter.sendModuleError(context, error)
    }

    fun notifyHookExecuted(context: Context, hookName: String) {
        sendHookEvent(context, hookName, "executed")
        EventEmitter.sendHookExecuted(context, hookName)
    }

    fun notifyHookError(context: Context, hookName: String, error: String) {
        sendHookEvent(context, hookName, "error", error)
        EventEmitter.sendHookError(context, hookName, error)
    }
}