package fes.aragon.appexamenhttp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import fes.aragon.appexamenhttp.view.adapters.RecyclerViewAdapter
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.model.User
import fes.aragon.appexamenhttp.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val users = ArrayList<User>()
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        viewModel.users.observe(this,{
            users.addAll(it)
            binding.buttonAdd.isClickable = true
            adapter.notifyDataSetChanged()
        })

        binding.buttonAdd.setOnClickListener {
            viewModel.getUser(users.size)
            binding.buttonAdd.isClickable = false
        }
    }

    private fun init(){
        viewModel.getAllUser()
        adapter = RecyclerViewAdapter(users)
        binding.recyclerView.adapter = adapter
    }

}