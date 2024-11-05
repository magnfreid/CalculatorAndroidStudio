package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

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
        val sumDisplay = binding.sumDisplayTV
        val operatorDisplay = binding.operatorDisplayTV
        val calculateDisplay = binding.calculateDisplayTV
        val clearButton = binding.clearButton
        val calculateButton = binding.calculateButton
        val buttonHandler = ButtonHandler(sumDisplay, operatorDisplay, calculateDisplay)
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
        val operationsButtons = listOf(
            binding.plusButton,
            binding.minusButton,
            binding.multiplyButton,
            binding.divideButton,
            binding.powerOfButton,
            binding.squareRootButton,
        )

        clearButton.setOnClickListener {
            buttonHandler.clearButtonClick()
        }
        calculateButton.setOnClickListener {
            buttonHandler.calculateButtonClick()
        }
        for (digit in digitsButtons) {
            digit.setOnClickListener {
                buttonHandler.digitButtonClick(digit.text.toString())
            }
        }
        for (operator in operationsButtons) {
            operator.setOnClickListener {
                buttonHandler.operatorButtonClick(operator.text.toString())
            }
        }
    }
}
