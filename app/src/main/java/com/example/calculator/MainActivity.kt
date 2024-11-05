package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val sumDisplay = binding.sumDisplayTV
        var calculateDisplay = binding.calculateDisplayTV

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
            binding.nineButton
        )

        val operationsButtons = listOf(
            binding.plusButton,
            binding.minusButton,
            binding.multiplyButton,
            binding.divideButton,
            binding.powerOfButton,
            binding.squareRootButton,
            binding.clearButton,
            binding.dotButton
        )

        for (digit in digitsButtons) {
            digit.setOnClickListener {
                val calculateDisplayText = calculateDisplay.text.toString() + digit.text
                calculateDisplay.text = calculateDisplayText
            }
        }

        for(operator in operationsButtons) {
            operator.setOnClickListener{
                val sumText = calculateDisplay.text.toString()+operator.text
                sumDisplay.text = sumText
                calculateDisplay.text =""

            }
        }

    }


}
