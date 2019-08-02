package com.miguel.android.trackyourexpenses.ui

import android.view.View

interface OnMovementSelected {
    fun onIncomeIdSelected(movementId: String?, view: View?)
    fun onExpenseIdSelected(movementId: String?, view: View?)
}