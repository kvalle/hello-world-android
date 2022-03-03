package com.example.helloworldapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.helloworldapp.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animalsRadioButtonGroup.setOnCheckedChangeListener { _, option ->
            when (option) {
                R.id.animals_radio_button_option_kitten -> {
                    binding.kittyImage.setImageResource(R.drawable.kitty)
                    binding.kittyImage.alpha = 0.4f
                    Log.v(TAG, "Kitty was chosen.")
                }
                R.id.animals_radio_button_option_octopus -> {
                    binding.kittyImage.setImageResource(R.drawable.octopus)
                    binding.kittyImage.alpha = 0.6f
                    Log.v(TAG, "Octopus was chosen.")
                }
                R.id.animals_radio_button_option_elephant -> {
                    binding.kittyImage.setImageResource(R.drawable.elephant)
                    binding.kittyImage.alpha = 0.8f
                    Log.v(TAG, "Elephant was chosen.")
                }
            }
        }

        binding.whoToGreetTextField.addTextChangedListener {
            when (it?.isEmpty()) {
                true -> binding.helloButton.isEnabled = false
                false -> binding.helloButton.isEnabled = true
            }
        }

        binding.helloButton.setOnClickListener {
            binding.helloOutputText.text = "Hello, ${binding.whoToGreetTextField.text}!"

            if (binding.waveSwitch.isChecked) {
                val image = binding.kittyImage
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

}