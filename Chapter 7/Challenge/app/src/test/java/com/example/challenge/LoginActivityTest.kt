package com.example.challenge

import android.util.Log
import com.example.challenge.Helper.Constant
import org.junit.Assert.*

import org.junit.Test

class LoginActivityTest {

    private val testSample: LoginActivity = LoginActivity()

    @Test
    fun getSharedpref() {
        val email = "ardan@mail.com"
        val password = "ardan"
        assertEquals(false, testSample.sharedpref.getString(email))
        assertEquals(false, testSample.sharedpref.getString(password))
    }
}