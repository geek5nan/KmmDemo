package com.devwu.demo.kmmdemo.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devwu.demo.kmmdemo.Greeting
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
//        tv.text = greet()
        tv.text = "Loading..."
        mainScope.launch {
            kotlin.runCatching {
//                Greeting().getHtml()
                Greeting().getTodayWeather()
            }.onSuccess {
                tv.text = it.toString()
            }.onFailure {
                tv.text = "Error: ${it.localizedMessage}"
            }
        }
    }
}
