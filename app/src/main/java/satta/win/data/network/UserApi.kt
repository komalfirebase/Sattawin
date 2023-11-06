package net.simplifiedcoding.data.network


import ecommerce.com.data.responses.*
import okhttp3.ResponseBody
import retrofit2.http.*

interface UserApi {

    @GET("login.php")
    suspend fun getUser(@Query("phone") phone: String): List<LoginResponse>

    @POST("logout")
    suspend fun logout(): ResponseBody
}