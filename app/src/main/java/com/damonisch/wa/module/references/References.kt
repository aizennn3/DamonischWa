package com.damonisch.wa.module.references

class References {
    companion object {
        // Message classes
        const val MESSAGE_CLASS = "com.whatsapp.Message"
        const val CONVERSATION_CLASS = "com.whatsapp.Conversation"
        const val MESSAGESTORAGE_CLASS = "com.whatsapp.Messagestorage"

        // Activity classes
        const val MAIN_ACTIVITY = "com.whatsapp.Main"
        const val HOME_ACTIVITY = "com.whatsapp.HomeActivity"

        // Adapter classes
        const val CONVERSATION_ADAPTER = "com.whatsapp.ConversationRow"
        const val MESSAGE_ADAPTER = "com.whatsapp.MessageAdapter"

        // Database classes
        const val MESSAGE_STORAGE = "com.whatsapp.Messagestorage"
        const val CHAT_STORAGE = "com.whatsapp.Chatstorage"

        // Method names
        const val METHOD_GET_MESSAGE = "getMessage"
        const val METHOD_DELETE_MESSAGE = "deleteMessage"
        const val METHOD_SEND_MESSAGE = "sendMessage"
    }
}