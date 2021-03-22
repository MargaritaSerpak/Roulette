package com.example.roulette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var roul: ImageView
    private lateinit var random: Random

    //переменные для хранения градуса
    private var oldDeegre = 0
    private var degree = 0

    private val numbers: List<String> = listOf(
        "32 RED", "15 BLACK", "19 RED", "4 BLACK",
        "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED", "6 BLACK", "27 RED", "13 BLACK",
        "36 RED", "11 BLACK", "30 RED", "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK",
        "16 RED", "33 BLACK", "1 RED", "20 BLACK", "14 RED", "31  BLACK", "9 RED", "22 BLACK",
        "18 RED", "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()


    }

    fun onCLickStart(view: View) {
        oldDeegre = degree % 360
        degree = random.nextInt(3600) + 720
        val rotateAnimation = RotateAnimation(
            oldDeegre.toFloat(),
            degree.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 3600
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                tvResult.text = ""
            }

            override fun onAnimationEnd(animation: Animation?) {
                tvResult.text = getResult(360 - (degree % 360))
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        roul.startAnimation(rotateAnimation)
    }

    private fun init() {
        tvResult = findViewById(R.id.tvResult)
        roul = findViewById(R.id.rul)
        random = Random()
    }

    private fun getResult(degree: Int): String {
        var text = ""

        var factor_x = 1
        var factor_y = 3
        var i = 0
        for (i in 0..37) {
            if (degree >= (FACTOR * factor_x) && degree < (FACTOR * factor_y)) {
                text = numbers[i]
            }
            factor_x += 2
            factor_y += 2
            if (degree >= (FACTOR * 73) && degree < 360 || degree >= 0 && degree < (FACTOR * 1)) {
                text = numbers[numbers.size - 1]
            }
        }


        return text
    }


    companion object {
        private const val FACTOR: Float = 4.86f
    }
}