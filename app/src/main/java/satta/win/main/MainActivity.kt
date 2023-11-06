package satta.win.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import satta.win.R
import satta.win.main.ui.MyConstent.controll


class MainActivity : AppCompatActivity() {
    var view: View? = null


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_sattaforum, R.id.nav_currentmixchart,
                    R.id.nav_guesserforum, R.id.nav_freejodi, R.id.nav_SattaChart,
                    R.id.nav_VIPForum, R.id.nav_Privacy, R.id.nav_LoginReg, R.id.nav_Logout
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

          if(controll.equals("1")){
              val nav_Menu: Menu = navView.getMenu()
              nav_Menu.findItem(R.id.nav_Logout).isVisible = true
              nav_Menu.findItem(R.id.nav_LoginReg).isVisible = false
          }else{
              val nav_Menu: Menu = navView.getMenu()
              nav_Menu.findItem(R.id.nav_Logout).isVisible = false
              nav_Menu.findItem(R.id.nav_LoginReg).isVisible = true
          }


        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-1965837604317865/7769499952")
          view = window.decorView.rootView
          Admob.createLoadInterstitial(applicationContext, null)





    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.aRef -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }
            R.id.aExit -> {
                finish()
                return true
            }

            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}