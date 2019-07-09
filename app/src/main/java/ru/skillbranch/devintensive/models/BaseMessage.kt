
package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
        var id: String,
        var from: User?,
        var chat: Chat,
        var isIncoming: Boolean = false,
        var date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object abstractFactory {
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text", payload: Any?,
                        isIncoming: Boolean = false): BaseMessage {
            lastId++
                return when (from) {
                    null -> throw IllegalArgumentException()
                    else ->
                return when (type) {
                    "image" -> ImageMessage("${lastId}", from, chat, date = date,
                            image = payload as String, isIncoming = isIncoming)
                    "text" -> TextMessage("${lastId}", from, chat, date = date,
                            text = payload as String, isIncoming = isIncoming)

                    else -> if ("image" == payload || "text" == payload)
                        makeMessage(from, chat, date, payload, type, isIncoming) else throw IllegalArgumentException()
                }
/*
                    "image"-> ImageMessage("${lastId}", from, chat, date = date, image = payload as String, isIncoming = isIncoming)
                    "text" -> TextMessage("${lastId}", from, chat, date = date, text = payload as String, isIncoming = isIncoming)

                    else-> if ("image" == payload || "text" == payload) makeMessage(from, chat, date, payload, type, isIncoming) else throw IllegalArgumentException()
*/
/*
                    "image" -> ImageMessage("$lastId", from, chat, date = date,
                            image = payload as String, isIncoming = isIncoming)
                    else -> TextMessage("$lastId", from, chat, date = date,
                            text = payload as String, isIncoming = isIncoming)
*/


            }
        }
    }
}

