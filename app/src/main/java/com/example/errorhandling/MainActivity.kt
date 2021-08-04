package com.example.errorhandling

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var context: Context? = null
    private var textView: ArrayList<TextView>? = null
    private var categoryInput: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        categoryInput = arrayListOf(
            "Select",
            "Celsius",
            "Fahrenheit",
            "Reaumur"
        )
        textView = arrayListOf(
            findViewById(R.id.tv_celcius),
            findViewById(R.id.tv_fahrenheit),
            findViewById(R.id.tv_reamur)
        )
        val category = categoryInput
        context = applicationContext
        val spinner = findViewById<Spinner>(R.id.spin_category)
        val btn: Button = findViewById(R.id.btn)
        val input = findViewById<EditText>(R.id.et_input)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryInput!!)

        spinner.adapter = adapter

        btn.setOnClickListener {
            onClick(input, spinner.selectedItem.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onClick(input: EditText, category: String) {
        val inputData = input.text
        if (!checker(input = inputData.toString(), category)) {
            Toast.makeText(context, "Perhatikan Input", Toast.LENGTH_SHORT).show()
            return
        }
        val hasil = ArrayList<String>()
        when (category) {
            "Celsius" -> {
                hasil.add((inputData.toString().toInt()).toString())
                hasil.add((inputData.toString().toDouble() * (9.toDouble() / 5) + 32).toString())
                hasil.add((inputData.toString().toDouble() * (4.toDouble() / 5)).toString())
            }
            "Fahrenheit" -> {
                hasil.add(((inputData.toString().toInt() - 32).toDouble() * (5.toDouble() / 9)).toString())
                hasil.add((inputData.toString().toInt()).toString())
                hasil.add(((inputData.toString().toInt() - 32).toDouble() * (4.toDouble() / 9)).toString())

            }
            "Reaumur" -> {
                hasil.add((inputData.toString().toDouble() * (4.toDouble() / 5)).toString())
                hasil.add(((inputData.toString().toInt() - 32).toDouble() * (4.toDouble() / 9)).toString())
                hasil.add((inputData.toString().toDouble()).toString())
            }
        }
        textView?.forEachIndexed { index, textView ->
            textView.text = (categoryInput?.get(index + 1) ?: "Not Found") + " : " + hasil[index]
        }
    }


    @SuppressLint("ShowToast")
    fun checker(input: String, category: String): Boolean {
        try {
            input.toInt()
            if (category == "Select"){
                throw NullPointerException()
            }
            return true
        } catch (e: Exception) {
            when (e) {
                is NullPointerException -> {
                    Log.d("Error : ", e.toString())
                }
                is NumberFormatException -> {
                    Log.d("Error : ", e.toString())
                }
            }
        }
        return false
    }

}