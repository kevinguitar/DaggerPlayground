package com.example.daggerexp.di

import javax.inject.Inject

class TestRepo @Inject constructor() {

    fun print() {
        println("Something")
    }
}