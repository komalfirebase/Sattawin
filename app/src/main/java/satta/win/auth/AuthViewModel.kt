package ecommerce.com.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ecommerce.com.data.responses.LoginResponse
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.AuthRepository
import net.simplifiedcoding.ui.base.BaseViewModel

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<List<LoginResponse>>> = MutableLiveData()
    val loginResponse: LiveData<Resource<List<LoginResponse>>>
        get() = _loginResponse

    fun login(
        name: String,
        password: String,
        email: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(name, password,email)
    }

    suspend fun saveAuthToken(token: String,loginUserId: String,name: String,Userid: String,email: String) {
        repository.saveAuthToken(token,loginUserId,name,Userid,email)
    }
}