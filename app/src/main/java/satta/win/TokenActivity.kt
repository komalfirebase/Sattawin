package satta.win

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import satta.win.main.MainActivity
import satta.win.main.ui.MyConstent
import satta.win.splash.SplashActivity


class TokenActivity : AppCompatActivity() {
    var token = ""
    var phone = ""
    var progressDoalog: ProgressDialog? = null
    var disposable: Disposable? = null
    var shareprefConfig: SharedPrefrenceConfig? = null
    val wikiApiServe by lazy {
        WikiApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token)
       shareprefConfig = SharedPrefrenceConfig(this.applicationContext)
        /*   if (shareprefConfig!!.readLoginStatus()) {
              startActivity(Intent(this@TokenActivity, SplashActivity::class.java))
              finish()
          }*/
        phone=  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
      //  MyConstent.setMsg(phone, this)
        progressDoalog = ProgressDialog(this)
        progressDoalog!!.setMessage("loading....")
        getToken()
    /*    findViewById<Button>(R.id.btnContinue).setOnClickListener({

        })*/

    }

    private fun getToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            token = instanceIdResult.token
            submitToken(token);

        }
    }

    private fun submitToken(token: String) {
        progressDoalog!!.show()
        disposable =
            wikiApiServe.hitCountCheck(token.toString(),phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        progressDoalog!!.dismiss()
                        shareprefConfig!!.writeLoginStats(true)
                        startActivity(Intent(this@TokenActivity, SplashActivity::class.java))
                        finish()
                      //  MyConstent.setMsg(result.responseDesc.toString(), this)
                    },
                    { error ->
                        progressDoalog!!.dismiss()
                    }

                )
    }
}