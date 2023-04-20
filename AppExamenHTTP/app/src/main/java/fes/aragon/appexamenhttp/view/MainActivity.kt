package fes.aragon.appexamenhttp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import fes.aragon.appexamenhttp.view.adapters.RecyclerViewAdapter
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.model.Result
import fes.aragon.appexamenhttp.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val users = ArrayList<Result>()
    private val dataViewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        dataViewModel.user.observe(this,{
            users.add(it)
            binding.buttonAdd.isClickable = true
            adapter.notifyDataSetChanged()
        })

        binding.buttonAdd.setOnClickListener {
            if (isOnline()){
                dataViewModel.getUser(users.size)
                binding.buttonAdd.isClickable = false
            }else{
                Toast.makeText(this,"No hay conexion a internet",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(users)
        binding.recyclerView.adapter = adapter
    }

    fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}