package fes.aragon.usuarioslista.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.usuarioslista.view.adapter.OnClickListener
import fes.aragon.usuarioslista.model.User
import fes.aragon.usuarioslista.view.adapter.UserAdapter
import fes.aragon.usuarioslista.databinding.ActivityMainBinding
import fes.aragon.usuarioslista.view.fragment.FragmentAddUser
import fes.aragon.usuarioslista.view.fragment.FragmentDeleteUser
import fes.aragon.usuarioslista.view.fragment.FragmentUpdateUser
import fes.aragon.usuarioslista.viewmodel.ViewModelUser

class MainActivity : AppCompatActivity(), OnClickListener, FragmentDeleteUser.NoticeDialogListener, FragmentAddUser.NoticeDialogListener, FragmentUpdateUser.NoticeDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var userSelect: User
    private var isUserSelect = false
    private val viewModelUser: ViewModelUser by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelUser.getAllUser()
        viewModelUser.users.observe(this,{
            userAdapter = UserAdapter(it,this)
            recyclerView = binding.recyclerview
            recyclerView.adapter = userAdapter
        })

        binding.add.setOnClickListener {
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

        binding.update.setOnClickListener {
            if (isUserSelect) {
                val fragmentUpdateUser = FragmentUpdateUser(userSelect)
                fragmentUpdateUser.isCancelable = false
                fragmentUpdateUser.show(supportFragmentManager,"fragmentUpdateUser")
            }else{
                Toast.makeText(this,"Select a user",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(user: User, position: Int) {
        isUserSelect = true
        userSelect = user
    }

    override fun onDialogStoreClick(dialog: DialogFragment, user: User) {
        viewModelUser.addUser(user)
        dialog.dismiss()
    }

    override fun onDialogDeletedClick(dialog: DialogFragment, user: User) {
        viewModelUser.deleteUser(user)
        dialog.dismiss()
        isUserSelect = false
    }

    override fun onDialogUpdate(dialog: DialogFragment, user: User) {
        viewModelUser.updateUser(user)
        dialog.dismiss()
    }
}