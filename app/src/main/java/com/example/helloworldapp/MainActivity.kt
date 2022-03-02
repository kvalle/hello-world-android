package com.example.helloworldapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloButton: Button = findViewById(R.id.helloButton)
        val nameField : EditText = findViewById(R.id.whoToGreetTextField);
        val kittyImage : ImageView = findViewById(R.id.kittyImage)

        helloButton.setOnClickListener {
            val outputTextView: TextView = findViewById(R.id.helloOutputText);
            outputTextView.text = "Hello, ${nameField.text}!"

            kittyImage.pivotX = kittyImage.width.toFloat() / 2
            kittyImage.pivotY = kittyImage.height.toFloat() / 2
            kittyImage.animate().rotation(15f).setDuration(300).withEndAction {
                kittyImage.animate().rotation(0f).setDuration(300).withEndAction {
                    kittyImage.animate().rotation(15f).setDuration(300).withEndAction {
                        kittyImage.animate().rotation(0f)
                    }
                }
            }


        }
    }

}