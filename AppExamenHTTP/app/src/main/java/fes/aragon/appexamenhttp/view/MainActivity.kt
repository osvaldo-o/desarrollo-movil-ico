package fes.aragon.appexamenhttp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
        adapter = RecyclerViewAdapter(users)
        binding.recyclerView.adapter = adapter
        dataViewModel.user.observe(this,{
            users.add(it)
            binding.buttonAdd.isClickable = true
            adapter.notifyDataSetChanged()
        })
        binding.buttonAdd.setOnClickListener {
            dataViewModel.getUser(users.size)
            binding.buttonAdd.isClickable = false
        }
    }

}