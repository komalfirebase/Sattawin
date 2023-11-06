package ecommerce.com.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import ecommerce.com.common.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.UserPreferences
import net.simplifiedcoding.data.network.RemoteDataSource
import net.simplifiedcoding.data.repository.BaseRepository
import net.simplifiedcoding.ui.base.BaseViewModel
import net.simplifiedcoding.ui.base.ViewModelFactory
import satta.win.auth.LoginActivity

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreferences.authToken.first()
            userPreferences.loginUserID.first()
            userPreferences.Userid.first()
            userPreferences.name.first() }
        return binding.root
    }

    fun logout() = lifecycleScope.launch{
     /*   val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
        viewModel.logout(api)*/
        FirebaseAuth.getInstance().signOut();
        userPreferences.clear()
        requireActivity().startNewActivity(LoginActivity::class.java)
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R


}