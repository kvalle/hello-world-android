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
        val octoButton : Button = findViewById(R.id.octopusButton)
        val kittenButton : Button = findViewById(R.id.kittenButton)
        val nameField : EditText = findViewById(R.id.whoToGreetTextField);
        val image : ImageView = findViewById(R.id.kittyImage)

        octoButton.setOnClickListener {
            image.setImageResource(R.drawable.octopus_cartoon_clipart_784330)
            image.alpha = 0.5f
        }

        kittenButton.setOnClickListener {
            image.setImageResource(R.drawable.hello)
            image.alpha = 0.2f
        }

        helloButton.setOnClickListener {
            val outputTextView: TextView = findViewById(R.id.helloOutputText);
            outputTextView.text = "Hello, ${nameField.text}!"

            image.pivotX = image.width.toFloat() / 2
            image.pivotY = image.height.toFloat() / 2
            image.animate().rotation(15f).setDuration(300).withEndAction {
                image.animate().rotation(0f).setDuration(300).withEndAction {
                    image.animate().rotation(15f).setDuration(300).withEndAction {
                        image.animate().rotation(0f)
                    }
                }
            }


        }
    }

}