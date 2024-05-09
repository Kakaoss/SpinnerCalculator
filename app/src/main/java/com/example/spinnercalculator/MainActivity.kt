package com.example.spinnercalculator

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.jetbrains.annotations.NotNull

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1 = findViewById<EditText>(R.id.number1)
        val num2 = findViewById<EditText>(R.id.number2)
        val result = findViewById<TextView>(R.id.textResult)
        val oblicz = findViewById<Button>(R.id.btnOblicz)

        val spinner: Spinner = findViewById(R.id.spinnerSelection)

        spinner.prompt = "Wybierz działanie"

        ArrayAdapter.createFromResource(
            this,
            R.array.operations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }



        oblicz.setOnClickListener {
            val value1 = num1.text.toString().toDouble()
            val value2 = num2.text.toString().toDouble()
            val operator = spinner.selectedItem.toString()

            if (value1 != null || value2 != null ) {
                val wynik = when (operator) {
                    "+" -> value1 + value2
                    "-" -> value1 - value2
                    "*" -> value1 * value2
                    "/" -> {
                        if (value2 == 0.0) {
                            Toast.makeText(this, "Nie można dzielić przez zero", Toast.LENGTH_SHORT)
                                .show()
                            return@setOnClickListener
                        }
                        value1 / value2
                    }
                    else -> 0.0
                }

                result.text = wynik.toString()
            }
        }
    }
}