package com.vmdevelopment.futsalzagi.clanarine.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.models.Clanarina
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentClanarinePoClanuBinding

class ClanarinePoClanuFragment : Fragment() {
    private var _binding: FragmentClanarinePoClanuBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ClanarinePoClanuFragmentArgs>()

    private val mClanViewModel: ClanViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListaClanarinaPoClanuAdapter by lazy { ListaClanarinaPoClanuAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClanarinePoClanuBinding.inflate(inflater, container, false)

        binding.clanarinePoClanuRecyclerView.adapter = adapter
        binding.clanarinePoClanuRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mClanViewModel.dohvatiSveClanarine.observe(viewLifecycleOwner, Observer { data ->
            val listaClanarinaPoClanu: MutableList<Clanarina> = mutableListOf()
            data.forEach {
                if (it.clan == args.currentClan.ime + " " + args.currentClan.prezime) listaClanarinaPoClanu.add(it)
            }
            adapter.setData(listaClanarinaPoClanu)
        })

        binding.floatingActionButtonClanarinePoClanu.setOnClickListener {
            findNavController().navigate(R.id.action_clanarinePoClanuFragment_to_dodajClanarinuFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}