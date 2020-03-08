package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(id: Int,
                   from: User?,
                   chat: Chat,
                   isIncoming: Boolean = false,
                   date: Date = Date(),
                   var image: String?
): BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return ("""${from?.lastName} ${if(isIncoming) "получил" else "отправил" } изображение $image ----  ${date.humanizeDiff()}""".trimIndent())
    }
}