package fes.aragon.usuarioslista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity(), OnClickListener, FragmentDeleteUser.NoticeDialogListener, FragmentAddUser.NoticeDialogListener {

    private val db = UserApplication.database.userDao()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var userSelect: User
    private var users = mutableListOf<User>()
    private var isUserSelect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        generateData()
        starComponents()
        binding.agregar.setOnClickListener {
            var fragmentAddUser = FragmentAddUser()
            fragmentAddUser.isCancelable = false
            fragmentAddUser.show(supportFragmentManager,"fragmentAddUser")
        }
        binding.delete.setOnClickListener {
            if (isUserSelect){
                val fragmentDeleteUser = FragmentDeleteUser(userSelect)
                fragmentDeleteUser.isCancelable = false
                fragmentDeleteUser.show(supportFragmentManager,"fragmentDeleteUser")
            }else{
                Toast.makeText(this,"Select a user",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun starComponents() {
        generateData()
        userAdapter = UserAdapter(mutableListOf(),this)
        recyclerView = binding.recyclerview
        recyclerView.adapter = userAdapter
    }

    private fun generateData() {
        val queue = LinkedBlockingQueue<MutableList<User>>()
        Thread {
            val user = UserApplication.database.userDao()
                .getAllUser()
            queue.add(user)
        }.start()
    }

    override fun onClick(user: User, position: Int) {
        isUserSelect = true
        userSelect = user
    }

    override fun onDialogStoreClick(dialog: DialogFragment, user: User) {
        Thread {
            db.addUser(user)
        }.start()
        dialog.dismiss()
        starComponents()
    }

    override fun onDialogDeletedClick(dialog: DialogFragment, user: User) {
        Thread {
            db.deleteUser(user)
        }.start()
        isUserSelect = false
        dialog.dismiss()
        starComponents()
    }
}