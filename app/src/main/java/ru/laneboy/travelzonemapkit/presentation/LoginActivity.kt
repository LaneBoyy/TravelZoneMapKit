package ru.laneboy.travelzonemapkit.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import ru.laneboy.travelzonemapkit.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        onButtonSingInClick()

    }

    private fun onButtonSingInClick() {
        findViewById<AppCompatButton>(R.id.btnSingIn).setOnClickListener {
//            val password = findViewById<TextInputEditText>(R.id.et_password).text.toString()
//            if (password.isEmpty()) {
//                Toast.makeText(this, "Заполните поле пароля", Toast.LENGTH_SHORT).show()
//            } else {
//                val intent = Intent(this, MapActivity::class.java)
//                startActivity(intent)
//                finish()
//            }

//                <ПОТОМ ИСПРАВИТЬ>
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
            finish()
//                </ПОТОМ ИСПРАВИТЬ>
        }
    }
}