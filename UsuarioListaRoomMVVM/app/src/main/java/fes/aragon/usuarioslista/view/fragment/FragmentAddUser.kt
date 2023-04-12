package fes.aragon.usuarioslista.view.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import fes.aragon.usuarioslista.R
import fes.aragon.usuarioslista.model.User


class FragmentAddUser() : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogStoreClick(dialog: DialogFragment,user: User)
    }

    private lateinit var buttonCancel: Button
    private lateinit var buttonWrite: Button
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var age: TextView
    private lateinit var masculino: RadioButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_usuario, container, false)
        name = view.findViewById(R.id.name)
        email = view.findViewById(R.id.email)
        age = view.findViewById(R.id.age)
        masculino = view.findViewById(R.id.masculino)

        buttonCancel = view.findViewById(R.id.cancelar)
        buttonCancel.setOnClickListener {
            dismiss()
        }
        buttonWrite = view.findViewById(R.id.almacenar)
        buttonWrite.setOnClickListener {
            var sexo: String
            if (masculino.isChecked()){
                sexo = "Masculino"
            }else{
                sexo = "Femenino"
            }
            listener.onDialogStoreClick(this,
                User(name = name.text.toString(), email = email.text.toString(), age = age.text.toString().toInt(), sex = sexo)
            )
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException){
            throw ClassCastException((context.toString()))
        }
    }
}