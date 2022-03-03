package com.example.helloworldapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloButton: Button = findViewById(R.id.helloButton)
        val octoButton: Button = findViewById(R.id.octopusButton)
        val elephantButton: Button = findViewById(R.id.elephantButton)
        val kittenButton: Button = findViewById(R.id.kittenButton)
        val nameField: EditText = findViewById(R.id.whoToGreetTextField);
        val image: ImageView = findViewById(R.id.kittyImage)

        octoButton.setOnClickListener {
            image.setImageResource(R.drawable.octopus)
            image.alpha = 0.6f
            Log.v(TAG, "Octopus was chosen.")
        }

        kittenButton.setOnClickListener {
            image.setImageResource(R.drawable.kitty)
            image.alpha = 0.4f
            Log.v(TAG, "Kitty was chosen.")
        }

        elephantButton.setOnClickListener {
            image.setImageResource(R.drawable.elephant)
            image.alpha = 0.8f
            Log.v(TAG, "Elephant was chosen.")
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