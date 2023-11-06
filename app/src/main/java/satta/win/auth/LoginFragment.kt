package ecommerce.com.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import ecommerce.com.common.handleApiError
import ecommerce.com.common.snackbar
import ecommerce.com.common.startNewActivity
import ecommerce.com.ui.base.BaseFragment
import ecommerce.com.common.visible
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.AuthApi
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.AuthRepository
import satta.win.auth.VerifyActivity.phonenumber
import satta.win.databinding.FragmentLoginBinding
import satta.win.main.MainActivity
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {
    var verificationId: String? = null

    private var mAuth: FirebaseAuth? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        sendVerificationCode(phonenumber!!)

        binding.progressbar.visible(false)
       // binding.buttonSignIn.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(
                            it.value.get(0).id.toString()!!,
                            it.value.get(0).phone.toString()!!,
                            it.value.get(0).name.toString()!!,
                            it.value.get(0).id.toString()!!,
                            it.value.get(0).email.toString()!!,
                        )
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })


        binding.buttonSignIn.setOnClickListener {
            val code: String = binding.editTextCode.otp.toString().trim { it <= ' ' }

            if (code.isEmpty() || code.length < 6) {

                requireView().snackbar("Enter OTP")
               // binding.editTextCode.setError("Enter code...")
                binding.editTextCode.requestFocus()
                return@setOnClickListener
            }
            verifyCode(code)
            //login()
        }
    }
    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                 /*   val intent = Intent(this@VerifyActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)*/
                    login()
                } else {

                    requireView().snackbar(task.exception!!.message!!)
                  //  task.exception!!.message?.let { requireView().snackbar(it) }
                /*    Toast.makeText(this@VerifyActivity, , Toast.LENGTH_LONG)
                        .show()*/
                }
            }
    }
    private fun login() {
      /*  val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()*/
       // viewModel.login(Name, phonenumber,email)
        lifecycleScope.launch {
            viewModel.saveAuthToken(
                phonenumber.toString()!!,
                phonenumber.toString()!!,
                phonenumber.toString()!!,
                phonenumber.toString()!!,
                phonenumber.toString()!!,
            )
            requireActivity().startNewActivity(MainActivity::class.java)
        }
        requireActivity().startNewActivity(MainActivity::class.java)

    }


    private fun sendVerificationCode(number: String) {
        // progressBar.setVisibility(View.VISIBLE);


       /* PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallBack
        )*/
    }

    private val mCallBack: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                binding.editTextCode.setOTP(code!!)
                code?.let { verifyCode(it) }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                requireView().snackbar(e.message!!)
                //Toast.makeText(this@VerifyActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}