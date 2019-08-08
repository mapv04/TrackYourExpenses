package com.miguel.android.trackyourexpenses.data.api.retrofit

import com.miguel.android.trackyourexpenses.data.api.request.RequestAccount
import com.miguel.android.trackyourexpenses.data.api.request.RequestMov
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.api.response.AccountDeleted
import com.miguel.android.trackyourexpenses.data.api.response.Movements
import retrofit2.Call
import retrofit2.http.*

interface AuthExpensesService {
    @GET("api/user/accounts/allAccounts")
    fun getAllAccounts(): Call<List<Account>>

    @POST("api/user/accounts/newAccount")
    fun createNewAccount(@Body requestAccounts: RequestAccount): Call<Account>

    @DELETE("api/user/accounts/deleteAccount/{id}")
    fun deleteAccount(@Path("id") accountId: String): Call<AccountDeleted>

    @POST("api/user/accounts/incomes/createNewIncome")
    fun createNewIncome(@Body requestIncome: RequestMov): Call<Movements>

    @GET("api/user/accounts/incomes/allIncomes/{id}")
    fun getAllIncomes(@Path("id") accountId: String): Call<List<Movements>>

    @POST("api/user/accounts/expenses/createNewExpense")
    fun createNewExpense(@Body requestExpense: RequestMov): Call<Movements>

    @GET("api/user/accounts/expenses/allExpenses/{id}")
    fun getAllExpenses(@Path("id") accountId: String): Call<List<Movements>>

    @GET("api/user/accounts/incomes/income/{id}")
    fun getIncome(@Path("id") incomeId: String): Call<Movements>

    @GET("api/user/accounts/expenses/expense/{id}")
    fun getExpense(@Path("id") expenseId: String): Call<Movements>
}