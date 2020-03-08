package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.ImageMessage
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance(){
        val user = User.Factory.makeUser("Ser Bel")
        val user2 = User.Factory.makeUser("Jown Crow")
        val user3 = User.Factory.makeUser("Dan Valen")

    }

    @Test
    fun test_parse(){
        println(Utils.parseFullName(null)) //null null
        println(Utils.parseFullName("")) //null null
        println(Utils.parseFullName(" ")) //null null
        println(Utils.parseFullName("John")) //John null

    }

    @Test
    fun test_Initial(){
        println(Utils.toInitials("john" ,"doe")) //JD
        println(Utils.toInitials(null, "doe")) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null

    }

    @Test
    fun test_date(){
        val user = User.makeUser("Ser Bel")
        val user2 = user.copy(lastVisit = Date().add(-30, TimeUnits.SECOND))
        val user3 = user.copy(lastVisit = Date().add(-3, TimeUnits.MINUTE))
        val user4 = user.copy(lastVisit = Date().add(-1, TimeUnits.SECOND))
        val user5 = user.copy(lastVisit = Date().add(-15, TimeUnits.HOUR))

        println((user2.lastVisit?.format()))
        println((user3.lastVisit?.format()))
        println((user4.lastVisit?.format()))
        println((user5.lastVisit?.format()))

    }


    @Test
    fun test_transliteration(){
        val user = User.makeUser("Ser Bel")
        val user2 = user.copy(lastVisit = Date().add(-30, TimeUnits.SECOND))
        val res = Utils.transliteration("Сергей Белянцев")

        println(res)
    }


    @Test
    fun test_base_message(){
        val user = User.makeUser("Ser Bel")
        val textmsg = BaseMessage.makeMessage(user, Chat(0), payload = "any text", type = "text")
        val imgmess =BaseMessage.makeMessage(user, Chat(0), payload = "any url image", type = "image")
        println(textmsg.formatMessage())
        println(imgmess.formatMessage())

    }
}
