package net.simplifiedcoding.data.network


import ecommerce.com.data.responses.LoginResponse
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String
    ) : List<LoginResponse>


/*    @POST("login.php")
    suspend fun login(
        @Query("username") email: String,
        @Query("password") password: String
    ) : LoginResponse*/
}