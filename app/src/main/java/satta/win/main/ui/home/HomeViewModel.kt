package satta.win.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ecommerce.com.data.responses.LoginResponse
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.UserRepository
import net.simplifiedcoding.ui.base.BaseViewModel

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<List<LoginResponse>>> = MutableLiveData()
    val user: LiveData<Resource<List<LoginResponse>>> get() = _user


    fun getUser(id: String) = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser(id)
    }
}