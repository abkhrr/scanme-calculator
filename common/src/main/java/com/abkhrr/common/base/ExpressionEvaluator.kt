package com.abkhrr.common.base

import android.util.Log
import java.util.regex.Pattern

class ExpressionEvaluator {

    // Evaluate an arithmetic expression
    // Use a simple regex-based evaluation for demonstration purposes (inside of try, catch)
    fun evaluateExpression(expression: String): Pair<String, String>  {
        val cleanedExpression = expression.replace("[^0-9+\\-*/xX.:÷]".toRegex(), "")
            .replace(Regex("[xX]"), "*")
            .replace(Regex("[:÷]"), "/")

        Log.e("EXPRESSION", cleanedExpression)

        if (!isValidExpression(cleanedExpression)) {
            throw IllegalArgumentException("Invalid expression")
        }

        try {
            val result = object : Any() {
                var pos = -1
                var ch: Char = ' '

                fun nextChar() {
                    ch = if (++pos < cleanedExpression.length) cleanedExpression[pos] else '\u0000'
                }

                fun eval(): Double {
                    nextChar()
                    val x = parseExpression()
                    if (pos < cleanedExpression.length) throw IllegalArgumentException("Unexpected: $ch")
                    return x
                }

                fun parseExpression(): Double {
                    var x = parseTerm()
                    while (true) {
                        when (ch) {
                            '+' -> {
                                nextChar()
                                x += parseTerm()
                            }
                            '-' -> {
                                nextChar()
                                x -= parseTerm()
                            }
                            else -> return x
                        }
                    }
                }

                fun parseTerm(): Double {
                    var x = parseFactor()
                    while (true) {
                        when (ch) {
                            '*' -> {
                                nextChar()
                                x *= parseFactor()
                            }
                            '/' -> {
                                nextChar()
                                val denominator = parseFactor()
                                if (denominator == 0.0) throw IllegalArgumentException("Division by zero")
                                x /= denominator
                            }
                            else -> return x
                        }
                    }
                }

                fun parseFactor(): Double {
                    if (ch == '+') {
                        nextChar()
                        return parseFactor()
                    }
                    if (ch == '-') {
                        nextChar()
                        return -parseFactor()
                    }
                    val startPos = pos
                    if (ch == '(') {
                        nextChar()
                        val x = parseExpression()
                        if (ch != ')') throw IllegalArgumentException("Unmatched parentheses")
                        nextChar()
                        return x
                    }
                    if (ch in '0'..'9' || ch == '.') {
                        while (ch in '0'..'9' || ch == '.') nextChar()
                        val str = cleanedExpression.substring(startPos, pos)

                        return str.toDouble()
                    }
                    throw IllegalArgumentException("Unexpected: $ch")
                }
            }.eval()

            val resultString = result.toInt().toString()
            return Pair(expression, resultString)
        } catch (e: Exception) {
            return Pair(expression, e.message.toString())
        }
    }

    // Check if the expression is valid
    // A simple check for valid characters and balanced parentheses
    private fun isValidExpression(expression: String): Boolean {
        val pattern = Pattern.compile("[0-9+\\-*/.()]*")
        val matcher = pattern.matcher(expression)
        return matcher.matches() && isBalancedParentheses(expression)
    }

    // Check if parentheses are balanced
    private fun isBalancedParentheses(expression: String): Boolean {
        var balance = 0
        for (char in expression) {
            if (char == '(') {
                balance++
            } else if (char == ')') {
                balance--
            }
            if (balance < 0) {
                return false
            }
        }
        return balance == 0
    }
}