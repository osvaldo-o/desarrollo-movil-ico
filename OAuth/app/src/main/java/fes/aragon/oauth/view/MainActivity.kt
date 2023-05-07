package fes.aragon.oauth.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import fes.aragon.oauth.view.adapters.RecyclerViewAdapter
import fes.aragon.oauth.view.fragments.DeleteFragment
import fes.aragon.oauth.view.fragments.UpdateFragment
import fes.aragon.oauth.viewmodel.ViewModel
import fes.aragon.oauth.R
import fes.aragon.oauth.TipoProvedor
import fes.aragon.oauth.databinding.ActivityMainBinding
import fes.aragon.oauth.model.local.UserEntity
import org.json.JSONObject

enum class TipoProvedor {
    CORREO,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnUserClickListener, UpdateFragment.NoticeDialogListener, DeleteFragment.NoticeDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var googleSignInOption: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: ViewModel by viewModels()
    private var userSelect: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        options() // Login

        viewModel.getAllUser()
        viewModel.users.observe(this) {
            adapter = RecyclerViewAdapter(it,this)
            recyclerView = binding.recyclerView
            recyclerView.adapter = adapter
        }

        binding.buttonAdd.setOnClickListener {
            if (isOnline()){
                viewModel.addUser()
            }else{
                msgErrorInternet()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            if (userSelect != null){
                val fragmentUpdate = UpdateFragment(this.userSelect!!)
                fragmentUpdate.isCancelable = false
                fragmentUpdate.show(supportFragmentManager,"fragmentUpdate")
            }else{
                msgNotUserSelect()
            }
        }

        binding.buttonDelete.setOnClickListener {
            if (userSelect != null){
                val fragmentDelete = DeleteFragment(this.userSelect!!)
                fragmentDelete.isCancelable = false
                fragmentDelete.show(supportFragmentManager,"fragmentDelete")
            }else{
                msgNotUserSelect()
            }
        }
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }


    override fun onDialogDeletedClick(dialog: DialogFragment, user: UserEntity) {
        viewModel.deleteUser(user)
        userSelect = null
        dialog.dismiss()
    }

    override fun onDialogUpdate(dialog: UpdateFragment, user: UserEntity) {
        viewModel.updateUser(user)
        userSelect = null
        dialog.dismiss()
    }

    override fun onUserClick(userEntity: UserEntity) {
        userSelect = userEntity
    }

    private fun options(){
        var bundle: Bundle? = intent.extras
        var email: String? = bundle?.getString("email")
        var provedor: String? = bundle?.getString("provedor")
        init(email ?: "", provedor ?: "")

        // Save Data Session
        val preferences = getSharedPreferences(getString(R.string.file_preferencia), Context.MODE_PRIVATE).edit()
        preferences.putString("email", email)
        preferences.putString("provedor", provedor)
        preferences.apply()
    }
    private fun init(email: String, provedor: String) {

        binding.closeSesion.setOnClickListener {
            val preferences = getSharedPreferences(
                getString(R.string.file_preferencia),
                Context.MODE_PRIVATE
            ).edit()
            preferences.clear()
            preferences.apply()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Google
        if (provedor == TipoProvedor.GOOGLE.name) {
            googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
            googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)
            val data = GoogleSignIn.getLastSignedInAccount(this)
            // Facebook
        } else if (provedor == TipoProvedor.FACEBOOK.name) {
            val accessToken = AccessToken.getCurrentAccessToken()
            Toast.makeText(this, "FACEBOOK", Toast.LENGTH_SHORT).show()
            if (accessToken != null) {
                val request: GraphRequest = GraphRequest.newMeRequest(
                    accessToken,
                    GraphRequest.GraphJSONObjectCallback({ obj: JSONObject, response: GraphResponse ->
                        val email = obj.getString("email")
                        val url = obj.getJSONObject("picture").getJSONObject("data")
                            .getString("url")
                    } as (JSONObject?, GraphResponse?) -> Unit))
                val parameters = Bundle()
                parameters.putString("fields", "id,name,link,email,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()
            }
        }
    }
    private fun msgErrorInternet() = Toast.makeText(this,R.string.msg_error,Toast.LENGTH_SHORT).show()
    private fun msgNotUserSelect() = Toast.makeText(this,R.string.msg_not_select,Toast.LENGTH_SHORT).show()

}