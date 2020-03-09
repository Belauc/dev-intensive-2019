package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderOdj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?: Bender.Question.NAME.name

        benderOdj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderOdj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = benderOdj.asdQuestion()

        sendBtn.setOnClickListener(this)

        messageEt.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
            EditorInfo.IME_ACTION_DONE -> { sendBtn.callOnClick(); true }
            else -> false
            }
        }
    }

    override fun onClick(v:View?){
        if (v?.id == R.id.iv_send) {
            val (phrase, color) = benderOdj.listenAnswer(messageEt.text.toString())
            messageEt.text = null

            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
        hideKeyboard()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderOdj.status.name)
        outState.putString("QUESTION", benderOdj.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${benderOdj.status.name}")
    }
}
