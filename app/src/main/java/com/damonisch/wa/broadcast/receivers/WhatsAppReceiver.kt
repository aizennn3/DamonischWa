package com.damonisch.wa.broadcast.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.damonisch.wa.broadcast.Events
import com.damonisch.wa.broadcast.Receivers
import com.damonisch.wa.broadcast.senders.EventEmitter

class WhatsAppReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "WhatsAppReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "WhatsApp broadcast received: ${intent.action}")

        // Processar eventos do WhatsApp
        when (intent.action) {
            Receivers.ACTION_WHATSAPP_EVENT -> {
                handleWhatsAppEvent(context, intent)
            }

            Intent.ACTION_PACKAGE_ADDED,
            Intent.ACTION_PACKAGE_REPLACED -> {
                if (intent.data?.schemeSpecificPart == "com.whatsapp") {
                    EventEmitter.sendWhatsAppStarted(context)
                }
            }

            Intent.ACTION_PACKAGE_REMOVED -> {
                if (intent.data?.schemeSpecificPart == "com.whatsapp") {
                    EventEmitter.sendWhatsAppStopped(context)
                }
            }
        }
    }

    private fun handleWhatsAppEvent(context: Context, intent: Intent) {
        val eventType = intent.getStringExtra("event_type") ?: return

        when (eventType) {
            "started" -> {
                EventEmitter.sendWhatsAppStarted(context)
            }
            "stopped" -> {
                EventEmitter.sendWhatsAppStopped(context)
            }
            "message_received" -> {
                EventEmitter.sendMessageReceived(context)
            }
            "message_sent" -> {
                EventEmitter.sendMessageSent(context)
            }
        }
    }
}