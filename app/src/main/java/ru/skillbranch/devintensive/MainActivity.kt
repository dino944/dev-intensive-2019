package ru.skillbranch.devintensive

//import android.support.v7.app.AppCompatActivity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    /**
     * Вызывается при первом создании или перезапуске Activity.
     *
     * здесь задается внешний вид активности (UI) через метод setContentView().
     * инициализируются представления
     * представления связываются с необходимыми данными и ресурсами
     * связываются данные со списками
     *
     * Этот метод также предоставляет Bundle, содержащий ранее сохраненное состояние Activity, если оно было.
     *
     * Всегда сопровождается вызовом onStart().
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("M_MainActivity","onCreate $status $question")

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }

    /**
     * Если Activity возвращается в приоритетный режим после вызова onStop(),
     * то в этом случае вызывается метод onRestart().
     * Т.е. вызывается после того, как Activity была остановлена и снова была запущена пользователем.
     * Всегда сопровождается вызовом метода onStart().
     *
     * Используется для специальных действий, которые должны выполняться только при повторном запуске Activity
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    /**
     * При вызове onStart() окно еще не видно пользователю, но вскоре будет видно.
     * Вызывается непосредственно перед тем, как активность становится видимой пользователю.
     *
     * Чтение из базы данных
     * Запуск анимации
     * Запуск потоков, отслеживания показаний датчиков, запрос к GPS, таймеров, сервисов или др. прцессов,
     * которые нужны исключительно для обновления пользовательского интерфейса.
     *
     *Затем следует onResume(), если Activity выходит на передний план.
     */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")

    }

    /**
     * Вызывается, когда Activity начинает взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио, видео
     * регистрации любых BroadcastReceiver или других процессов, которые вы освободили/приостановили в onPause()
     * выполнение любых других инициализаций, которые должны происходить, когда Activity вновь активна (камера).
     *
     * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume")
    }

    /**
     *Метод onPause() вызывается после сворачивания текущей активности или перехода к новому.
     * От onPause() можно перейти к вызову либо onResume(), либо onStop().
     *
     * остановка анимцииб аудио и видео
     * сохранение состояния пользовательского ввода (легкие процессы)
     * сохранение в Базе Данных если данные должны быть доступны в новой Activity
     * остановка сервисов, подписок, BroadcastReceiver
     *
     * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    /**
     *Метод onStop() вызывается, когда Activity становится невидимым для пользователя.
     * Это может произойти при ее уничтожениии, или если была запущена другая Activity (существующая или новая)
     * перекрывающая окно текущей Activity.
     *
     * запись в Базу Данных
     * приостановка сложной анимации
     * приостановка потоков, отслеживания показаний датчиков, запрос к GPS,таймеровБ сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при вызове метода finish() у Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop")
    }

    /**
     * Метод вызывается по окончании работы Activity, при вызове метода finish() или в случае,
     * когда система уничтожает этот экземпляр активности для освобождения ресурсов
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy")
    }

    /**
     * Этот метод сохраняет состояние представления в Dundle
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity","onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
            var (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) =color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }

    }
}
