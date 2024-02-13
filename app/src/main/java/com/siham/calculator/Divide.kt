package com.siham.calculator

class Divide {
    fun perform(x:Double,y:Double):Double{
        try {
            return x/y
        }
        catch (exc:java.lang.ArithmeticException){
             exc.message
            return 0.0
        }
    }
}