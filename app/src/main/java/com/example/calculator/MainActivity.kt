package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var inputTxtView:TextView?=null
    private var lastNumeric:Boolean=false
    private var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTxtView=findViewById(R.id.inputTxtView)

    }
    fun onDigit(view:View){
        inputTxtView?.append((view as Button).text)
        lastNumeric=true
//        lastDot=false
//        Toast.makeText(this, "Digit Clicked", Toast.LENGTH_SHORT).show()
    }

    fun onClr(view:View){
        inputTxtView?.text=""
        Toast.makeText(this, "Input Cleared", Toast.LENGTH_SHORT).show()
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric&&!lastDot){
            inputTxtView?.append(".")
            lastNumeric=false
            lastDot=true

        }
    }

    fun onOp(view: View){
        inputTxtView?.text?.let {
            if(lastNumeric&& !isOpAdd(it.toString())){
                inputTxtView?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }
    private fun isOpAdd(value: String):Boolean{
        return if(value.startsWith("-")){
            var valueStr = value.substring(1)
            valueStr.contains("+") || valueStr.contains("-") || valueStr.contains("/") || valueStr.contains("*")
        }else{
            value.contains("+") || value.contains("-") || value.contains("/") || value.contains("*")
        }
    }

    private fun removeZero(result: String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)

        return value
    }
    fun onEql(view: View){
        if(lastNumeric){
            var valueTxtView=inputTxtView?.text.toString()
            var prefix=""
            try {
                if(valueTxtView.startsWith("-")){
                    prefix="-"
                    valueTxtView=valueTxtView.substring(1)
                }
                if(valueTxtView.contains("-")){
                    val splitValue=valueTxtView.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    inputTxtView?.text=removeZero((one.toDouble()-two.toDouble()).toString())
                }else if(valueTxtView.contains("+")){
                    val splitValue=valueTxtView.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    inputTxtView?.text=removeZero((one.toDouble()+two.toDouble()).toString())
                }else if(valueTxtView.contains("/")){
                    val splitValue=valueTxtView.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    inputTxtView?.text=removeZero((one.toDouble()/two.toDouble()).toString())
                }else if(valueTxtView.contains("*")){
                    val splitValue=valueTxtView.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    inputTxtView?.text=removeZero((one.toDouble()*two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}