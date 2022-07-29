package com.hexademical.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hexademical.tiptime.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if(cost == null){
            binding.tipAmount.text = ""
            return
        }
        val selectId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectId){
            R.id.tip_15_percent -> 0.15
            R.id.tip_18_percent -> 0.18
            else -> 0.20
        }

        val isRoundUp = binding.roundupService.isChecked
        val tipAmount = when(isRoundUp){
            true -> ceil(cost * tipPercentage)
            else -> cost * tipPercentage
        }

        val formatTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.tipAmount.text = getString(R.string.tip_amount, formatTip)


    }
}