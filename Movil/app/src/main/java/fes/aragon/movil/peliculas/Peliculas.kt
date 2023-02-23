package fes.aragon.movil.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fes.aragon.movil.R

class Peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val buttonPlay: FloatingActionButton = findViewById(R.id.floatingButtonPlay)
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
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}