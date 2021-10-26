package com.vmdevelopment.futsalzagi.clanovi.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentListaClanovaBinding

class ListaClanovaFragment : Fragment() {
    private var _binding: FragmentListaClanovaBinding? = null
    private val binding get() = _binding!!

    private val mClanViewModel: ClanViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentListaClanovaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup RecyclerView
        setupRecyclerView()

        // Observe LiveData
        mClanViewModel.dohvatiSveClanove.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        // Set menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override
    fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lista_clanova_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_clanarine -> findNavController().navigate(R.id.action_listaClanovaFragment_to_listaClanarinaFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}