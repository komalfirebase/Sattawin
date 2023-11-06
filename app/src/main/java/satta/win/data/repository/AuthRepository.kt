package net.simplifiedcoding.data.repository

import net.simplifiedcoding.data.UserPreferences
import net.simplifiedcoding.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun login(
        name: String,
        password: String,
        email: String
    ) = safeApiCall {
        api.login(name, password,email)
    }

    suspend fun saveAuthToken(token: String,loginUserId: String,name: String,Userid: String,email: String){
        preferences.saveAuthToken(token,loginUserId,name,Userid,email)
    }

}