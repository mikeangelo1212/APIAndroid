package com.example.examenu3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.examenu3.Album.Album
import com.example.examenu3.databinding.FragmentSecondBinding
import com.example.examenu3.viewmodels.SecondFragmentViewModel
import com.example.examenu3.viewmodels.SecondFragmentViewModelFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    val secondFragmentViewModel : SecondFragmentViewModel by viewModels{
        SecondFragmentViewModelFactory((requireActivity().application as ExamenApplication).repository)
    }

    val args: SecondFragmentArgs by navArgs()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("Perro","Nuestro argumento es: ${args.claveAlbum}")
//        if(args.claveAlbum.isNotEmpty()){
//            Log.d("Perro","No estamos solos: ${args.claveAlbum}")
//
//            secondFragmentViewModel.getAlbum(args.claveAlbum).observe(viewLifecycleOwner){album->
//            if (album != null) {
//                    android.util.Log.d(
//                        "Perro",
//                        "Encontramos nuestro objeto ${args.claveAlbum}, objeto editable"
//                    )
//                    binding.edtNombre.setText(album.nombre)
//                    binding.edtClaveAlbum.setText(album.claveAlbum)
//                    binding.edtGrupo.setText(album.grupo)
//                    binding.edtLanzamiento.setText(album.añoLanzamiento.toString())
//                    binding.btnGuardarAlbum.text = "Editar"
//                    binding.btnBorrarAlbum.isVisible = true
//                    binding.edtClaveAlbum.isEnabled = false
//
//                }
//            }
//
//        }
//        else binding.btnBorrarAlbum.isVisible=false
//
//        binding.btnGuardarAlbum.setOnClickListener {
//
//            val nombre = binding.edtNombre.text.toString()
//            val clave_album = binding.edtClaveAlbum.text.toString()
//            val grupo = binding.edtGrupo.text.toString()
//            val anoLanzamiento = binding.edtLanzamiento.text.toString().toInt()
//
//            val album = Album(nombre, clave_album, grupo, anoLanzamiento)
//            secondFragmentViewModel.insertAlbum(album)
//
//            //preguntamos si estamos editando
//            if(args.claveAlbum.isNotEmpty()){
//                Toast.makeText(requireActivity(), "Elemento editado", Toast.LENGTH_SHORT).show()
//            }
//            else Toast.makeText(requireActivity(), "Elemento guardado", Toast.LENGTH_SHORT).show()
//
//
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
//
//        binding.btnBorrarAlbum.setOnClickListener{
//            secondFragmentViewModel.delete(args.claveAlbum)
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//            Toast.makeText(requireContext(), "Elemento borrado", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}