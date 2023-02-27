package fes.aragon.movil.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fes.aragon.movil.R
import fes.aragon.movil.databinding.ActivityPeliculasBinding
import fes.aragon.movil.practica3.NestedScrollViewActivity

class Peliculas : AppCompatActivity() {
    private lateinit var binding: ActivityPeliculasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.floatingButtonPlay.setOnClickListener {
            startActivity(Intent(this, Video::class.java))
        }
        /*val buttonPlay: FloatingActionButton = findViewById(R.id.floatingButtonPlay)
        val buttonComprar: FloatingActionButton = findViewById(R.id.floatingButtonComprar)
        val buttonDetalle: FloatingActionButton = findViewById(R.id.floatingButtonDetalle)
        val buttonSalir: FloatingActionButton = findViewById(R.id.floatingButtonSalir)

        buttonPlay.setOnClickListener{
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
        }
        buttonComprar.setOnClickListener {
            Toast.makeText(this, "Comprar pelicula", Toast.LENGTH_SHORT).show()
        }
        buttonDetalle.setOnClickListener {
            Toast.makeText(this, "Detalles", Toast.LENGTH_SHORT).show()
        }
        buttonSalir.setOnClickListener {
            Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}