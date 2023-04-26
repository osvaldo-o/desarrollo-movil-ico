package fes.aragon.oauth.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import fes.aragon.oauth.databinding.FragmentUpdateBinding
import fes.aragon.oauth.model.User

class UpdateFragment(private val user: User) : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogUpdate(dialog: UpdateFragment, user: User)
    }

    lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        binding.EditTextName.setText(user.name)
        binding.EditTextLast.setText(user.last)
        binding.EditTextEmail.setText(user.email)
        binding.buttonUpdate.setOnClickListener {
            listener.onDialogUpdate(this,User(user.id,binding.EditTextName.text.toString(),binding.EditTextLast.text.toString(),binding.EditTextEmail.text.toString(),user.image))
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
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