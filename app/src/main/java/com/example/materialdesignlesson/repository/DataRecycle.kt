package com.example.materialdesignlesson.repository
const val TYPE_EARTH = 1
const val TYPE_MARS = 2
data class DataRecycle(val someText: String = "title", val description: String = "description", val type:Int = TYPE_EARTH)