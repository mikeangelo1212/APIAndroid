package com.example.examenu3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenu3.databinding.FragmentFirstBinding
import com.example.examenu3.models.adapters.CancionAdapter
import com.example.examenu3.utils.TokenManager
import com.example.examenu3.viewmodels.FirstFragmentViewModel
import com.example.examenu3.viewmodels.FirstFragmentViewModelFactory



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val firstFragmentViewModel : FirstFragmentViewModel by viewModels{
        FirstFragmentViewModelFactory( (requireActivity().application as ExamenApplication).repository )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test", "Iniciamos el fragmento")

        //TokenManager.guardarToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Mywibm9tYnJlIjoibWlndWVsIiwiaWF0IjoxNzE3MjkyNDMzLCJleHAiOjE3MTk0NTI0MzN9.Yy2b0EfI_8BzZ0Uo9dQrpwEUcdnGEMYRaKb2A5QKb5g")
        //esta madre es cuando le picamos a un objeto
        val adapter = CancionAdapter{
            onItemClick(it)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //esto es para observar cambios en tiempo real de la base de datos,
        // se muestran en el adapter
        firstFragmentViewModel.canciones.observe(viewLifecycleOwner, Observer { canciones ->
            canciones?.let {
                Log.d("Test", "Recycler view pal centro y pa dentro ${canciones}")
                adapter.submitList(it)
            }
        })

        Log.d("Test", "Tratamos de meter los objetos al recyclerView")

        firstFragmentViewModel.getCanciones()
    }

    private fun onItemClick(cancion: Cancion) {
        Toast.makeText(requireContext(), "Clic a: ${cancion.Titulo}", Toast.LENGTH_SHORT).show()
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(cancion.idCancion.toString())
        Log.d("Test","${cancion.Titulo}, ${cancion.Artista}")
        findNavController().navigate(action)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}