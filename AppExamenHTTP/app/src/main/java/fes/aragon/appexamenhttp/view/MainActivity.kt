package fes.aragon.appexamenhttp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import fes.aragon.appexamenhttp.R
import fes.aragon.appexamenhttp.view.adapters.RecyclerViewAdapter
import fes.aragon.appexamenhttp.databinding.ActivityMainBinding
import fes.aragon.appexamenhttp.model.User
import fes.aragon.appexamenhttp.viewmodel.ViewModel

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val users = ArrayList<User>()
    private var userSelect: User? = null
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        viewModel.users.observe(this) {
            users.addAll(it)
            binding.buttonAdd.isClickable = true
            adapter.notifyDataSetChanged()
        }

        binding.buttonAdd.setOnClickListener {
            if (isOnline()){
                binding.buttonAdd.isClickable = false
                viewModel.getUser(users.size)
            }else{
                msgErrorInternet()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            if (userSelect != null){
                viewModel.updateUser(userSelect!!)
            }else{
                msgNotUserSelect()
            }
        }

        binding.buttonDelete.setOnClickListener {
            if (userSelect != null){
                viewModel.deleteUser(userSelect!!)
                users.remove(userSelect)
            }else{
                msgNotUserSelect()
            }
        }
    }

    private fun initRecyclerView(){
        viewModel.getAllUser()
        adapter = RecyclerViewAdapter(users,this)
        binding.recyclerView.adapter = adapter
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun msgErrorInternet() = Toast.makeText(this,R.string.msg_error,Toast.LENGTH_SHORT).show()
    private fun msgNotUserSelect() = Toast.makeText(this,R.string.msg_not_select,Toast.LENGTH_SHORT).show()

    override fun onUserClick(user: User) {
        userSelect = user
    }
}