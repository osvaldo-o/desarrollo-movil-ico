package fes.aragon.apprandomdog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import fes.aragon.apprandomdog.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allBreeds()

        binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            search(item.lowercase())
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun allBreeds() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ServiceAPI::class.java).getAllBreed("breeds/list")
            val pug: ModeloDog? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val breeds: List<String> = pug?.images?: emptyList()
                    val arrayAdapter = ArrayAdapter(binding.root.context,R.layout.dropdown_item, breeds)
                    binding.autoCompleteTextView.setAdapter(arrayAdapter)
                }else{
                    msgError()
                }
            }
        }
    }

    private fun search(type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ServiceAPI::class.java).getRandomBreed("breed/$type/images/random")
            val pug: RandomDog? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val image: String? = pug?.message
                    Picasso.get().load(image).into(binding.imageView)
                }else{
                    msgError()
                }
            }
        }
    }

    private fun msgError() {
        Toast.makeText(this,"Error en conexion", Toast.LENGTH_SHORT).show()
    }

}