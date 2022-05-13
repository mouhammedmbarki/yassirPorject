package com.example.mbarki_mohamed_yassir_project.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mbarki_mohamed_yassir_project.R
import com.example.mbarki_mohamed_yassir_project.presentation.main.MainActivity


class SpalshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        findViewById<Button>(R.id.button).setOnClickListener {
            val i = Intent(this@SpalshActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}