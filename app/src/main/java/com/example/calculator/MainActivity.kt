package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.EditText
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {
    lateinit var button1: android.widget.Button
    lateinit var button2: android.widget.Button
    lateinit var button3: android.widget.Button
    lateinit var button4: android.widget.Button
    lateinit var button5: android.widget.Button
    lateinit var button6: android.widget.Button
    lateinit var button7: android.widget.Button
    lateinit var button8: android.widget.Button
    lateinit var button9: android.widget.Button
    lateinit var button0: android.widget.Button
    lateinit var buttonPercent: android.widget.Button
    lateinit var buttonAdd: android.widget.Button
    lateinit var buttonSub: android.widget.Button
    lateinit var buttonDivide: android.widget.Button
    lateinit var buttonMultiply: android.widget.Button
    lateinit var buttonDot: android.widget.Button
    lateinit var buttonClear: android.widget.Button
    lateinit var buttonAllClear: android.widget.Button
    lateinit var buttonEquals: android.widget.Button

    lateinit var inputText: EditText
    lateinit var outputText: EditText

    var check =0
    //check is for operator. here check is 0, means there is no any operator is present

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0 = findViewById(R.id.btn0)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)

        buttonPercent =findViewById(R.id.percent)
        buttonAdd = findViewById(R.id.plus)
        buttonSub = findViewById(R.id.minus)
        buttonMultiply = findViewById(R.id.multiply)
        buttonDivide = findViewById(R.id.division)
        buttonDot = findViewById(R.id.dot)
        buttonClear = findViewById(R.id.backSpace)
        buttonAllClear = findViewById(R.id.clearAll)
        buttonEquals = findViewById(R.id.equal)

        inputText = findViewById(R.id.input)
        outputText = findViewById(R.id.output)

        //to set horizontally edittext
        inputText.movementMethod = ScrollingMovementMethod()
        inputText.isActivated()
        inputText.isPressed()

        var text : String

        button0.setOnClickListener {
            text = inputText.text.toString() +"0"
            inputText.setText(text)
            result(text)
        }

        button1.setOnClickListener {
            text = inputText.text.toString() +"1"
            inputText.setText(text)
            result(text)
        }
        button2.setOnClickListener {
            text = inputText.text.toString() +"2"
            inputText.setText(text)
            result(text)
        }

        button3.setOnClickListener {
            text = inputText.text.toString() +"3"
            inputText.setText(text)
            result(text)
        }

        button4.setOnClickListener {
            text = inputText.text.toString() +"4"
            inputText.setText(text)
            result(text)
        }

        button5.setOnClickListener {
            text = inputText.text.toString() +"5"
            inputText.setText(text)
            result(text)
        }

        button6.setOnClickListener {
            text = inputText.text.toString() +"6"
            inputText.setText(text)
            result(text)
        }

        button7.setOnClickListener {
            text = inputText.text.toString() +"7"
            inputText.setText(text)
            result(text)
        }

        button8.setOnClickListener {
            text = inputText.text.toString() +"8"
            inputText.setText(text)
            result(text)
        }

        button9.setOnClickListener {
            text = inputText.text.toString() +"9"
            inputText.setText(text)
            result(text)
        }

        buttonDot.setOnClickListener {
            text = inputText.text.toString() +"."
            inputText.setText(text)
            result(text)
        }

        //for one operator between two operands

        buttonAdd.setOnClickListener {
            text = inputText.text.toString()+"+"
            inputText.setText(text)
            check = check+1

        }

        buttonSub.setOnClickListener {
            text = inputText.text.toString()+"-"
            inputText.setText(text)
            check = check+1

        }

        buttonDivide.setOnClickListener {
            text = inputText.text.toString()+"÷"
            inputText.setText(text)
            check = check+1

        }

        buttonPercent.setOnClickListener {
            text = inputText.text.toString()+"%"
            inputText.setText(text)
            check = check+1

        }

        buttonMultiply.setOnClickListener {
            text = inputText.text.toString()+"*"
            inputText.setText(text)
            check = check+1

        }

        //for equal button
        buttonEquals.setOnClickListener {
            text = outputText.text.toString()
            inputText.setText(text)
            outputText.setText(null)
        }

        //for all clear
        buttonAllClear.setOnClickListener {
            inputText.setText(null)
            outputText.setText(null)
        }

        //for backspace
        buttonClear.setOnClickListener {
            var backSpaceVar :String
            if(inputText.text.isNotEmpty()){
                //to modify the input
                val stringBuilder : StringBuilder = StringBuilder(inputText.text)

                //to get the character
                val findChar = inputText.text.toString()
                //for last character
                val findLastChar = findChar.last()

                if (findLastChar.equals('+') || findLastChar.equals('-') || findLastChar.equals('X') || findLastChar.equals('%') || findLastChar.equals('÷')){
                    check = check - 1
                }
                stringBuilder.deleteCharAt(inputText.text.length-1)
                backSpaceVar = stringBuilder.toString()
                inputText.setText(backSpaceVar)
                result(backSpaceVar)
            }
        }


    }

    private fun result(text: String) {

        val engine : ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

        try {
            val result : Any = engine.eval(text)
            //it will evaluate all the button press
            var  finalResult = result.toString()

            if (check==0){
                outputText.setText(null)
            }
            else{
                outputText.setText(finalResult)
            }
        }
        catch (e : ScriptException){
            Log.d("TAG", "ERROR")
        }

    }

}