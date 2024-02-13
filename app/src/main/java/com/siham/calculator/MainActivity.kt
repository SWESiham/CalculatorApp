package com.siham.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.marginTop
import com.siham.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    var visibility=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.ACbtn.setOnClickListener {
       binding.Expression.text=""
       binding.Result.text=""
            if(visibility==1){
                binding.Result.visibility= View.INVISIBLE
                visibility=0
            }
   }
        binding.btn0.setOnClickListener {
            binding.Expression.append("0")
        }
        binding.btn1.setOnClickListener {
            binding.Expression.append("1")
        }
        binding.btn2.setOnClickListener {
            binding.Expression.append("2")
        }
        binding.btn3.setOnClickListener {
            binding.Expression.append("3")
        }
        binding.btn4.setOnClickListener {
            binding.Expression.append("4")
        }
        binding.btn5.setOnClickListener {
            binding.Expression.append("5")
        }
        binding.btn6.setOnClickListener {
            binding.Expression.append("6")
        }
        binding.btn7.setOnClickListener {
            binding.Expression.append("7")
        }
        binding.btn8.setOnClickListener {
            binding.Expression.append("8")
        }
        binding.btn9.setOnClickListener {
            binding.Expression.append("9")
        }
        binding.btnAdd.setOnClickListener {
            binding.Expression.append("+")
        }
        binding.btnMultipy.setOnClickListener {
            binding.Expression.append("×")
        }
        binding.btnDivide.setOnClickListener {
            binding.Expression.append("÷")
        }
        binding.btnSubtract.setOnClickListener {
            binding.Expression.append("-")
        }
        binding.Cbtn.setOnClickListener {
          binding.Expression.text=binding.Expression.text.substring(0,binding.Expression.text.toString().lastIndex)
        }
        binding.Dotbtn.setOnClickListener {
        binding.Expression.append(".")
        }
        binding.Modulus.setOnClickListener {

            binding.Expression.append("%")
        }


        binding.Equalbtn.setOnClickListener {
           binding.Result.visibility= View.VISIBLE
            calculateResult()
            visibility=1
        }

    }


    private fun calculateResult() {
        val input = binding.Expression.text.toString()
        if(input[binding.Expression.text.lastIndex]=='+'||input[binding.Expression.text.lastIndex]=='-'||input[binding.Expression.text.lastIndex]=='*'||input[binding.Expression.text.lastIndex]=='/'){
            Toast.makeText(this,"Enter Another Number",Toast.LENGTH_SHORT).show()
            return
        }

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val numbers = input.split("+", "-", "×", "÷"," ","%")

        if (numbers.size < 2) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            return
        }

        var result = numbers[0].toDouble()
        var currentIndex = numbers[0].length

        for (i in 1 until numbers.size) {
            val operator = input[currentIndex]
            val operand = numbers[i].toDouble()

            result = when (operator) {
                '+' -> Add().perform(result, operand)
                '-' -> Subtract().perform(result, operand)
                '×' -> Multiply().perform(result, operand)
                '÷' -> Divide().perform(result, operand)
                '%' -> Modulus().perform(result,operand)
                else -> throw IllegalArgumentException("Invalid operator: $operator")
            }

            currentIndex += numbers[i].length + 1
        }

        binding.Result.text = "$result"


    }






}




