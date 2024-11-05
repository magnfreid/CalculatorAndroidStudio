package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

lateinit var binding: ActivityMainBinding
lateinit var buttonHandler: ButtonHandler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonHandler = ButtonHandler(
            binding.sumDisplayTV,
            binding.operatorDisplayTV,
            binding.calculateDisplayTV
        )
        val digitsButtons = listOf(
            binding.zeroButton,
            binding.oneButton,
            binding.twoButton,
            binding.threeButton,
            binding.fourButton,
            binding.fiveButton,
            binding.sixButton,
            binding.sevenButton,
            binding.eightButton,
            binding.nineButton,
            binding.dotButton
        )
        val operatorButtons = listOf(
            binding.plusButton,
            binding.minusButton,
            binding.multiplyButton,
            binding.divideButton,
            binding.powerOfButton,
            binding.squareRootButton,
        )
        setClickListeners(digitsButtons, operatorButtons)
    }

    private fun setClickListeners(
        digitsButtons: List<MaterialButton>,
        operatorButtons: List<MaterialButton>
    ) {
        binding.clearButton.setOnClickListener {
            buttonHandler.clearButtonClick()
        }
        binding.calculateButton.setOnClickListener {
            if (binding.calculateDisplayTV.text.toString() != "" && binding.operatorDisplayTV.text.toString() != "") {
                buttonHandler.calculateButtonClick()
            }
        }
        for (button in digitsButtons) {
            button.setOnClickListener {
                buttonHandler.digitButtonClick(button)
            }
        }
        for (button in operatorButtons) {
            button.setOnClickListener {
                if (binding.calculateDisplayTV.text.toString() != "" && binding.operatorDisplayTV.text.toString() == "") {
                    buttonHandler.operatorButtonClick(button)
                }
            }
        }
    }
}

//TODO make sure only one dot can be used
//TODO fix landscape layout
