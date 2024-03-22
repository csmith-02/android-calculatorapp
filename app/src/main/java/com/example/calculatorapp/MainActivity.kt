package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private val buffer = mutableListOf<Double>()
    private var currentOperation: Operation? = null

    private var decimal = false
    private var listenersDeactivated = false
    private var isNegative: Boolean = false

    private val maxValue: Long = 9999999

    private var numberButtons: List<Button>? = null

    enum class Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonListeners(binding);

    }

    private fun setButtonListeners(binding: ActivityMainBinding) {

        // Initialize buffer and text field
        buffer.clear()
        binding.textViewNumber.text = "0.0"


        numberButtons = listOf<Button>(binding.buttonZero, binding.buttonOne, binding.buttonTwo,
            binding.buttonThree, binding.buttonFour, binding.buttonFive, binding.buttonSix, binding.buttonSeven,
            binding.buttonEight, binding.buttonNine);


        binding.buttonClear.setOnClickListener {
            binding.textViewNumber.text = "0.0"
            currentOperation = null
            buffer.clear()
            binding.textViewOperation.text = ""
            isNegative = false
            listenersDeactivated = false
            getAllNumberListeners(binding)
            decimal = false
        }

        binding.buttonEquals.setOnClickListener {
            if (buffer.isEmpty() || buffer.size == 1) {
                return@setOnClickListener
            } else {
                when (currentOperation) {
                    Operation.ADD -> buffer[0] = buffer[0] + buffer[1]
                    Operation.MULTIPLY -> buffer[0] = buffer[0] * buffer[1]
                    Operation.SUBTRACT -> buffer[0] = buffer[0] - buffer[1]
                    Operation.DIVIDE -> buffer[0] = buffer[0] / buffer[1]
                    null -> return@setOnClickListener
                }
                buffer.removeAt(1)
                currentOperation = null
                binding.textViewOperation.text = "="
                if (buffer[0] > maxValue) {
                    binding.textViewNumber.text = scientificNotation(buffer[0].toString())
                } else {
                    binding.textViewNumber.text = formatNumber(buffer[0].toString())
                }
                isNegative = false
                decimal = false
            }
        }

        binding.buttonAdd.setOnClickListener {
            if (buffer.isEmpty()) {
                return@setOnClickListener
            } else {
                if (buffer.size == 2) {
                    // Add contents of index 0 & 1 and store in index 0
                    when (currentOperation) {
                        Operation.ADD -> buffer[0] = buffer[0] + buffer[1]
                        Operation.MULTIPLY -> buffer[0] = buffer[0] * buffer[1]
                        Operation.SUBTRACT -> buffer[0] = buffer[0] - buffer[1]
                        Operation.DIVIDE -> buffer[0] = buffer[0] / buffer[1]
                        null -> return@setOnClickListener
                    }
                    buffer.removeAt(1)
                    if (buffer[0] > maxValue) {
                        binding.textViewNumber.text = "%e".format(buffer[0].toString())
                    } else {
                        binding.textViewNumber.text = formatNumber(buffer[0].toString())
                    }
                }
                if (listenersDeactivated) {
                    getAllNumberListeners(binding)
                    listenersDeactivated = false
                }
                binding.textViewOperation.text = "+"
                currentOperation = Operation.ADD
                isNegative = false
                decimal = false
            }
        }

        binding.buttonMinus.setOnClickListener {
            if (buffer.isEmpty()) {
                return@setOnClickListener
            } else {
                if (buffer.size == 2) {
                    // Add contents of index 0 & 1 and store in index 0
                    when (currentOperation) {
                        Operation.ADD -> buffer[0] = buffer[0] + buffer[1]
                        Operation.MULTIPLY -> buffer[0] = buffer[0] * buffer[1]
                        Operation.SUBTRACT -> buffer[0] = buffer[0] - buffer[1]
                        Operation.DIVIDE -> buffer[0] = buffer[0] / buffer[1]
                        null -> return@setOnClickListener
                    }
                    buffer.removeAt(1)
                    if (buffer[0] > maxValue) {
                        binding.textViewNumber.text = scientificNotation(buffer[0].toString())
                    } else {
                        binding.textViewNumber.text = formatNumber(buffer[0].toString())
                    }
                }
                if (listenersDeactivated) {
                    getAllNumberListeners(binding)
                    listenersDeactivated = false
                }
                binding.textViewOperation.text = "-"
                currentOperation = Operation.SUBTRACT
                isNegative = false
                decimal = false
            }
        }

        binding.buttonMultiply.setOnClickListener {
            if (buffer.isEmpty()) {
                return@setOnClickListener
            } else {
                if (buffer.size == 2) {
                    // Add contents of index 0 & 1 and store in index 0
                    when (currentOperation) {
                        Operation.ADD -> buffer[0] = buffer[0] + buffer[1]
                        Operation.MULTIPLY -> buffer[0] = buffer[0] * buffer[1]
                        Operation.SUBTRACT -> buffer[0] = buffer[0] - buffer[1]
                        Operation.DIVIDE -> buffer[0] = buffer[0] / buffer[1]
                        null -> return@setOnClickListener
                    }

                    buffer.removeAt(1)
                    if (buffer[0] > maxValue) {
                        binding.textViewNumber.text = scientificNotation(buffer[0].toString())
                    } else {
                        binding.textViewNumber.text = formatNumber(buffer[0].toString())
                    }
                }
                if (listenersDeactivated) {
                    getAllNumberListeners(binding)
                    listenersDeactivated = false
                }
                binding.textViewOperation.text = "x"
                currentOperation = Operation.MULTIPLY
                isNegative = false
                decimal = false
            }
        }

        binding.buttonDivide.setOnClickListener {
            if (buffer.isEmpty()) {
                return@setOnClickListener
            } else {
                if (buffer.size == 2) {
                    // Add contents of index 0 & 1 and store in index 0
                    when (currentOperation) {
                        Operation.ADD -> buffer[0] = buffer[0] + buffer[1]
                        Operation.MULTIPLY -> buffer[0] = buffer[0] * buffer[1]
                        Operation.SUBTRACT -> buffer[0] = buffer[0] - buffer[1]
                        Operation.DIVIDE -> buffer[0] = buffer[0] / buffer[1]
                        null -> return@setOnClickListener
                    }
                    buffer.removeAt(1)
                    if (buffer[0] > maxValue) {
                        binding.textViewNumber.text = scientificNotation(buffer[0].toString())
                    } else {
                        binding.textViewNumber.text = formatNumber(buffer[0].toString())
                    }
                }
                if (listenersDeactivated) {
                    getAllNumberListeners(binding)
                    listenersDeactivated = false
                }
                binding.textViewOperation.text = "/"
                currentOperation = Operation.DIVIDE
                isNegative = false
                decimal = false
            }
        }

        getAllNumberListeners(binding)

        binding.buttonNegate.setOnClickListener {
            negate(binding)
        }

        binding.buttonPercent.setOnClickListener {
            if (buffer.isEmpty()) {
                return@setOnClickListener
            } else if (buffer.size == 1) {
                if (currentOperation == null) {
                    buffer[0] = buffer[0] / 100
                    binding.textViewNumber.text = formatNumber(buffer[0].toString())
                } else {
                    return@setOnClickListener
                }
            } else {
                buffer[1] = buffer[1] / 100
                binding.textViewNumber.text = formatNumber(buffer[1].toString())
            }
        }

        binding.buttonDecimal.setOnClickListener {
            decimal = true
            if (currentOperation != null) {
                if (buffer.size == 1) {
                    buffer.add(1, 0.0)
                    binding.textViewNumber.text = "0.0"
                }
            }
        }
    }

    private fun getAllNumberListeners(binding: ActivityMainBinding) {
        for ((i, button) in numberButtons?.withIndex()!!) {
            getNumberListener(button, i, binding)
        }
    }

    private fun getNumberListener(button: Button, i: Int, binding: ActivityMainBinding) {
       button.setOnClickListener(setNumberListener(button, i, binding))
    }

    private fun negate(binding: ActivityMainBinding) {

        // Toggle negative
        isNegative = !isNegative

        if (buffer.size == 1) {
            if (currentOperation == null){
                buffer[0] = buffer[0] * -1
                if (isNegative) {
                    binding.textViewNumber.text = buffer[0].toString()
                } else {
                    binding.textViewNumber.text = buffer[0].toString()
                }
            } else {
                if (isNegative) {
                    binding.textViewNumber.text = "-0.0"
                } else {
                    binding.textViewNumber.text = "0.0"
                }
            }
        } else if (buffer.size == 2) {
            buffer[1] = buffer[1] * -1
            binding.textViewNumber.text = buffer[1].toString()
        } else {
            if (isNegative) {
                binding.textViewNumber.text = "-0.0"
            } else {
                binding.textViewNumber.text = "0.0"
            }
        }
    }

    private fun scientificNotation(input: String) : String {
        return "%.2e".format(input.toFloat())
    }

    private fun formatNumber(input: String) : String {
        val inputArr = input.split(".")
        var numOfDecimals = inputArr[1]
        if (inputArr[0] == "0" && numOfDecimals.length >= 6) {
            return scientificNotation(input)
        }
        if (numOfDecimals.length >= 4 && inputArr[0].length >= 4) {
            numOfDecimals = numOfDecimals.substring(0, 2)
        } else if (numOfDecimals.length > 4) {
            numOfDecimals = numOfDecimals.substring(0, 4)
        }
        return inputArr[0].plus(".").plus(numOfDecimals)
    }

    private fun setNumberListener(button: Button, i: Int, binding: ActivityMainBinding) : View.OnClickListener {
        return View.OnClickListener {
            if (buffer.isEmpty()) {
                if (isNegative) {
                    if (decimal) {
                        buffer.add(0, -1 * i.toDouble() / 10)
                    } else {
                        buffer.add(0, -1 * i.toDouble())
                    }
                } else {
                    if (decimal) {
                        buffer.add(0, i.toDouble() / 10)
                    } else {
                        buffer.add(0, i.toDouble())
                    }
                }
                binding.textViewNumber.text = buffer[0].toString()
            } else if (buffer.size == 1) {
                if (currentOperation == null) {
                    if (binding.textViewNumber.text.length == 9 && !listenersDeactivated) {
                        removeNumberListener()
                        return@OnClickListener
                    }
                    if (isNegative) {
                        if (decimal) {
                            buffer.add(0, buffer[0] - (i.toDouble() / (10.toDouble().pow(getNumOfPlaces(buffer[0].toString())).toInt())))
                        } else {
                            buffer.add(0, (buffer[0] * 10) - i)
                        }
                    } else {
                        if (decimal) {
                            buffer.add(0, buffer[0] + (i.toDouble() / (10.toDouble().pow(getNumOfPlaces(buffer[0].toString())).toInt())))
                        } else {
                            buffer.add(0, (buffer[0] * 10) + i)
                        }
                    }
                    buffer.removeAt(1)
                    binding.textViewNumber.text = buffer[0].toString()
                } else {
                    if (isNegative) {
                        if (decimal) {
                            buffer.add(1, -1 * i.toDouble() / 10)
                        } else {
                            buffer.add(1, -1 * i.toDouble())
                        }
                    } else {
                        if (decimal) {
                            buffer.add(1, i.toDouble() / 10)
                        } else {
                            buffer.add(1, i.toDouble())
                        }
                        buffer.add(1, i.toDouble())
                    }
                    binding.textViewNumber.text = buffer[1].toString()
                }
            } else {
                if (binding.textViewNumber.text.length == 9 && !listenersDeactivated) {
                    removeNumberListener()
                    return@OnClickListener
                }
                if (isNegative) {
                    if (decimal) {
                        buffer.add(1, buffer[1] - (i.toDouble() / (10.toDouble().pow(getNumOfPlaces(buffer[1].toString())).toInt())))
                    } else {
                        buffer.add(1, (buffer[1] * 10) - i)
                    }
                } else {
                    if (decimal) {
                        buffer.add(1, buffer[1] + (i.toDouble() / (10.toDouble().pow(getNumOfPlaces(buffer[1].toString())).toInt())))
                    } else {
                        buffer.add(1, (buffer[1] * 10) + i)
                    }
                }
                buffer.removeAt(2)
                binding.textViewNumber.text = buffer[1].toString()

            }
        }
    }

    private fun getNumOfPlaces(input: String) : Int {
        val decimals = input.split(".")[1]
        if (decimals == "0") {
            return 1
        }
        return decimals.length + 1
    }

    private fun removeNumberListener() {
        for (button in numberButtons!!) {
            button.setOnClickListener(null)
        }
        listenersDeactivated = true
    }

}