package com.example.fabrikatepla.ui.theme

object otpCode {

    private var _code:String? = null
    val code get() = _code

    fun generateNewCode(){
        val currentMills = System.currentTimeMillis().toString()
        val newOtpCode = currentMills.subSequence(currentMills.length-5,currentMills.length-1)
        _code = newOtpCode.toString()
    }

}