package satta.win.main.ui.sattaforum

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import ecommerce.com.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.simplifiedcoding.data.network.UserApi
import net.simplifiedcoding.data.repository.UserRepository
import satta.win.R
import satta.win.databinding.FragmentHomeBinding
import satta.win.databinding.FragmentSattaforumBinding
import satta.win.main.ui.home.HomeViewModel
import satta.win.visible


class SattaForumFragment  : BaseFragment<HomeViewModel, FragmentSattaforumBinding, UserRepository>() {

    private lateinit var homeViewModel: HomeViewModel

    private var mInterstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MobileAds.initialize(context, "ca-app-pub-1965837604317865/3861198045")
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd!!.setAdUnitId("ca-app-pub-1965837604317865/3861198045")
        PrepareAdd()

        MobileAds.initialize(
                context
        )
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.progressbar.visible(true)
        webLoad()



    }
    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentSattaforumBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val name =  runBlocking { userPreferences.name.first() }
        // Userid =  runBlocking { userPreferences.Userid.first().toString() }
        val loginUserID =  runBlocking { userPreferences.loginUserID.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

    override fun getViewModel() = HomeViewModel::class.java

    fun PrepareAdd() {
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd!!.adUnitId = "ca-app-pub-1965837604317865/3861198045"
        mInterstitialAd!!.loadAd(AdRequest.Builder().build())
    }

    private fun webLoad() {
        val settings: WebSettings = binding.webview.getSettings()
        settings.javaScriptEnabled = true
        binding.webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        val alertDialog = AlertDialog.Builder(context).create()
        binding.webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // Log.i(HomeFragment.TAG, "Processing webview url click...")
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Log.i(HomeFragment.TAG, "Finished loading URL: $url")
                binding.progressbar.visible(false)
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                //  Log.e(HomeFragment.TAG, "Error: $description")
                Toast.makeText(context, "Oh no! $description", Toast.LENGTH_SHORT).show()
                alertDialog.setTitle("Error")
                alertDialog.setMessage(description)
                alertDialog.setButton("OK", DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })
                alertDialog.show()
            }
        })
        binding.webview.loadUrl("https://sattawin.in/app/sattaforum.php")
    }
}