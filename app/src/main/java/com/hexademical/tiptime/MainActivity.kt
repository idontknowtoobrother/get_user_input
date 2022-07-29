package com.hexademical.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hexademical.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener {
            calculateTip()
        }
    }

    private fun displayTip(tip: Double){
        val formatTip = NumberFormat.getCurrencyInstance().format(Double)
        binding.tipAmount.text = getString(R.string.tip_amount, formatTip)
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.tip_15_percent -> 0.15
            R.id.tip_18_percent -> 0.18
            else -> 0.20
        }

        val tipAmount = when(binding.roundupService.isChecked){
            true -> kotlin.math.ceil(cost * tipPercentage)
            else -> cost * tipPercentage
        }
        displayTip(tipAmount)
    }
}