package com.miguel.android.trackyourexpenses.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestMov(accountId: String) {
    @SerializedName("accountId")
    @Expose
    private val accountId: String = accountId


}