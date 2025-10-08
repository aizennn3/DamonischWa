package com.damonisch.wa.broadcast.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.damonisch.wa.broadcast.Events
import com.damonisch.wa.broadcast.Receivers
import com.damonisch.wa.broadcast.senders.EventEmitter

class ModuleReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "ModuleReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Module broadcast received: ${intent.action}")

        // Processar eventos específicos do módulo
        when (intent.action) {
            Receivers.ACTION_MODULE_EVENT -> {
                handleModuleEvent(context, intent)
            }

            Receivers.ACTION_HOOK_EVENT -> {
                handleHookEvent(context, intent)
            }
        }
    }

    private fun handleModuleEvent(context: Context, intent: Intent) {
        val eventType = intent.getStringExtra("event_type") ?: return

        when (eventType) {
            "start" -> {
                // Módulo iniciado
                EventEmitter.sendModuleStarted(context)
            }
            "stop" -> {
                // Módulo parado
                EventEmitter.sendModuleStopped(context)
            }
            "error" -> {
                // Erro no módulo
                val error = intent.getStringExtra("error") ?: "Unknown error"
                EventEmitter.sendModuleError(context, error)
            }
        }
    }

    private fun handleHookEvent(context: Context, intent: Intent) {
        val hookName = intent.getStringExtra("hook_name") ?: return
        val eventType = intent.getStringExtra("event_type") ?: return

        when (eventType) {
            "executed" -> {
                EventEmitter.sendHookExecuted(context, hookName)
            }
            "error" -> {
                val error = intent.getStringExtra("error") ?: "Unknown error"
                EventEmitter.sendHookError(context, hookName, error)
            }
        }
    }
}