package com.example.examenu3

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.examenu3.databinding.FragmentLoginBinding
import com.example.examenu3.utils.TokenManager
import com.example.examenu3.viewmodels.LoginViewModel
import com.example.examenu3.viewmodels.LoginViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val loginFragmentViewModel : LoginViewModel by viewModels{
        LoginViewModelFactory( (requireActivity().application as ExamenApplication).repository )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test", "Iniciamos el fragmento de Login")

        //limpiamos todo
        TokenManager.borrarToken()
        binding.edtUsuario.text.clear()
        binding.edtPassword.text.clear()

        binding.btnLogin.setOnClickListener{
            var usuario=binding.edtUsuario.text.toString()
            var password=binding.edtPassword.text.toString()

            val user=User(null,usuario,password,0)
            Log.d("Test", "Intentaremos entrar al metodo de login")
            loginFragmentViewModel.login(user)
            Log.d("Test", "Token antes del if: ${TokenManager.leerToken()}")
            if(TokenManager.leerToken()!=""&&TokenManager.leerToken()!="err") {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToFirstFragment())
                Toast.makeText(
                    requireContext(),
                    "Bienvenido: ${TokenManager.usuario.nombre}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else Toast.makeText(
                requireContext(),
                "Login fallido",
                Toast.LENGTH_SHORT
            ).show()
        }
        //esto es para observar cambios en tiempo real de la base de datos,
        // se muestran en el adapter


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}