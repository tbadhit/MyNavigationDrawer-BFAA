package com.tbadhit.mynavigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tbadhit.mynavigationdrawer.databinding.ActivityMainBinding
import com.tbadhit.mynavigationdrawer.databinding.NavHeaderMainBinding
import de.hdodenhof.circleimageview.CircleImageView

// add library "build.gradle module" (1)
// Summary Function xml ->
// update "side_nav_bar.xml" (drawable/side_nav_bar.xml) (1)
// add library "build.gradle module" (2)
// update "nav_header_main.xml" (Change ImageView to CircleImageView)
// update "MainActivity" (1)
// Add INTERNET permission on "AndroidManifest"

// CALL FRAGMENT FROM MENU:
// Update code in "activity_main_drawer.xml" (1)
// Create new fragment "CartFragment"
// update "fragment_card.xml"
// add "fragment_cart" to navigation
// update "MainActivity" (2)
// Run APP

// CALL ACTIVITY FROM MENU:
// Create new activity "SubwayAcitivity"
// update "activity_subway.xml" (1)
// add "activity_subway" to navigation
// Run APP
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    // (1)
    private lateinit var profileCircleImageView: CircleImageView
    private var profileImgUrl = "https://lh3.googleusercontent.com/-4qy2DfcXBoE/AAAAAAAAAAI/AAAAAAAABi4/rY-jrtntAi4/s640-il/photo.jpg"
    //-----

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        // (1)
        profileCircleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
        Glide.with(this)
            .load(profileImgUrl)
            .into(profileCircleImageView)
        // nanti
//        val headerBinding = NavHeaderMainBinding.inflate(LayoutInflater.from(navView.context))
//        navView.addHeaderView(headerBinding.root)
//        val profileCircleImageView = headerBinding.imageView
//        Glide.with(this)
//            .load(profileImgUrl)
//            .into(profileCircleImageView)

        // (2) (Add fragment_cart id "R.id.nav_cart")
        /*
        AppBarConfiguration = berisi kumpulan id yang ada di dalam menu NavigationDrawer (baris 3).
        Jika id yang ada di dalam menu NavigationDrawer ditambahkan di AppBarConfiguration,
        maka AppBar akan berbentuk Menu NavigationDrawer. Jika tidak ditambahkan, maka akan
        menampilkan tanda panah kembali.
         */
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_cart
            ), drawerLayout
        )
        //-----
        /*
        setupActionBarWithNavController = digunakan untuk mengatur judul AppBar agar sesuai dengan
        Fragment yang ditampilkan.
         */
        setupActionBarWithNavController(navController, appBarConfiguration)
        /*
        setupWithNavController = digunakan supaya NavigationDrawer menampilkan Fragment yang
        sesuai ketika menu dipilih
         */
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /*
    Kode di bawah digunakan untuk mengatur ketika tombol back ditekan. Misalnya ketika Anda
    di halaman CartFragment, jika Anda tekan tombol back, maka aplikasi tidak langsung keluar,
    melainkan akan menuju ke startDestination yang ada di Navigation Graph, yaitu HomeFragment.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}