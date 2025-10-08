package com.damonisch.wa.broadcast.senders

import android.content.Context
import android.content.Intent
import com.damonisch.wa.broadcast.Receivers
import com.damonisch.wa.broadcast.Senders

object WhatsAppSender {

    fun sendWhatsAppEvent(context: Context, eventType: String) {
        val intent = Intent(Receivers.ACTION_WHATSAPP_EVENT).apply {
            `package` = Senders.PACKAGE_WHATSAPP
            putExtra("event_type", eventType)
        }

        context.sendBroadcast(intent)
    }

    fun notifyWhatsAppStarted(context: Context) {
        sendWhatsAppEvent(context, "started")
        EventEmitter.sendWhatsAppStarted(context)
    }

    fun notifyWhatsAppStopped(context: Context) {
        sendWhatsAppEvent(context, "stopped")
        EventEmitter.sendWhatsAppStopped(context)
    }

    fun notifyMessageReceived(context: Context) {
        sendWhatsAppEvent(context, "message_received")
        EventEmitter.sendMessageReceived(context)
    }

    fun notifyMessageSent(context: Context) {
        sendWhatsAppEvent(context, "message_sent")
        EventEmitter.sendMessageSent(context)
    }
}