package com.miguel.android.trackyourexpenses.data

class Movs(val name: String, val description: String, val total: Double, val items: List<ItemMov>)

class ItemMov(val name: String, val itemPP: String, val total: Double)