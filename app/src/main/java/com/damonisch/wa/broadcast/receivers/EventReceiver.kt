package com.damonisch.wa.broadcast.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.damonisch.wa.broadcast.Events

class EventReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "EventReceiver"
    }

    private var listener: EventListener? = null

    fun setEventListener(listener: EventListener) {
        this.listener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return

        Log.d(TAG, "Event received: $action")

        when (action) {
            Events.MODULE_STARTED -> {
                listener?.onModuleStarted(
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }

            Events.MODULE_STOPPED -> {
                listener?.onModuleStopped(
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }

            Events.MODULE_ERROR -> {
                listener?.onModuleError(
                    intent.getStringExtra(Events.EXTRA_ERROR_MESSAGE) ?: "Unknown error",
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }

            Events.HOOK_EXECUTED -> {
                listener?.onHookExecuted(
                    intent.getStringExtra(Events.EXTRA_HOOK_NAME) ?: "Unknown hook",
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }

            Events.HOOK_ERROR -> {
                listener?.onHookError(
                    intent.getStringExtra(Events.EXTRA_HOOK_NAME) ?: "Unknown hook",
                    intent.getStringExtra(Events.EXTRA_ERROR_MESSAGE) ?: "Unknown error",
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }

            Events.SETTINGS_CHANGED -> {
                listener?.onSettingsChanged(
                    intent.getStringExtra(Events.EXTRA_SETTING_KEY) ?: "",
                    intent.getStringExtra(Events.EXTRA_SETTING_VALUE) ?: "",
                    intent.getLongExtra(Events.EXTRA_TIMESTAMP, System.currentTimeMillis())
                )
            }
        }
    }

    interface EventListener {
        fun onModuleStarted(timestamp: Long)
        fun onModuleStopped(timestamp: Long)
        fun onModuleError(error: String, timestamp: Long)
        fun onHookExecuted(hookName: String, timestamp: Long)
        fun onHookError(hookName: String, error: String, timestamp: Long)
        fun onSettingsChanged(key: String, value: String, timestamp: Long)
    }
}