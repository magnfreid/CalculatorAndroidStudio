package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var calculationsHandler: CalculationsHandler
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        calculationsHandler = CalculationsHandler(binding)
        setClickListeners()
    }

    private fun setClickListeners() {
        val digitsButtons = listOf(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9,
            binding.btnPeriod
        )
        val operatorButtons = listOf(
            binding.btnPlus,
            binding.btnMinus,
            binding.btnMultiply,
            binding.btnDivide,
            binding.btnPowOf,
            binding.btnSquareRoot,
        )

        binding.btnClear.setOnClickListener {
            calculationsHandler.clearButton()
        }
        binding.btnCalculate.setOnClickListener {
            calculationsHandler.calculateButton()

        }
        for (button in digitsButtons) {
            button.setOnClickListener {
                if (button != binding.btnPeriod) {
                    calculationsHandler.digitButton(button)
                } else if (!binding.tvLower.text.contains('.') && binding.tvLower.text.isNotEmpty()) {
                    calculationsHandler.digitButton(button)
                }
            }
        }
        for (button in operatorButtons) {
            button.setOnClickListener {
                calculationsHandler.operatorButton(button)
            }
        }
    }
}

//TODO fix landscape layout
