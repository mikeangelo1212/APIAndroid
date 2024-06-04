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
import androidx.lifecycle.Observer
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
        Log.d("Test","Nuestro argumento es: ${args.claveAlbum}")

        //TODO: necesitamos un metodo que nos traiga una sola cancion o mandar todo el objeto de vergazo
        if(args.claveAlbum.isNotEmpty()){
            Log.d("Test","No estamos solos: ${args.claveAlbum}")

            secondFragmentViewModel.cancion.observe(viewLifecycleOwner,Observer{cancion->
            if (cancion != null) {
                    Log.d(
                        "Test",
                        "Encontramos nuestro objeto ${args.claveAlbum}, objeto editable"
                    )
                    binding.edtTitulo.setText(cancion.first().Titulo)
                    binding.edtArtista.setText(cancion.first().Artista)
                    binding.btnGuardarAlbum.text = "Editar"
                    binding.btnBorrarAlbum.isVisible = true

                }
            })


            secondFragmentViewModel.getCancion(args.claveAlbum)

        }
        else {
            binding.btnBorrarAlbum.isVisible = false
        }

        binding.btnGuardarAlbum.setOnClickListener {

            val titulo = binding.edtTitulo.text.toString()
            val artista = binding.edtArtista.text.toString()
            var cancion= Cancion(null, titulo, artista)
            //preguntamos si estamos editando
            if(args.claveAlbum.isNotEmpty()){

                secondFragmentViewModel.updateCancion(cancion,args.claveAlbum.toInt())
                Toast.makeText(requireActivity(), "Elemento editado", Toast.LENGTH_SHORT).show()
            }
            else {
                secondFragmentViewModel.insertCancion(cancion)
                Toast.makeText(requireActivity(), "Elemento guardado", Toast.LENGTH_SHORT).show()
            }


            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.btnBorrarAlbum.setOnClickListener{
            secondFragmentViewModel.deleteCancion(args.claveAlbum)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            Toast.makeText(requireContext(), "Elemento borrado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}