package fes.aragon.movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.NestedScrollingChild
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import fes.aragon.movil.peliculas.Peliculas
import fes.aragon.movil.practica2.ConstrainActivity
import fes.aragon.movil.practica3.NestedScrollViewActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicioToolsBar()
    }

    private fun inicioToolsBar(){
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.abrir,R.string.cerrar)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setNavigationIcon(R.drawable.unam_32)
        iniciarNavegacionView()
    }

    private fun iniciarNavegacionView(){
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val headerView = LayoutInflater.from(this).inflate(R.layout.header_main,navigationView,false)
        navigationView.addHeaderView(headerView)
     }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.contranit_layout -> {
                startActivity(Intent(this, ConstrainActivity::class.java))
            }
            R.id.nestedscrollview -> {
                startActivity(Intent(this,NestedScrollViewActivity::class.java))
            }
            R.id.peliculas -> {
                startActivity(Intent(this,Peliculas::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}