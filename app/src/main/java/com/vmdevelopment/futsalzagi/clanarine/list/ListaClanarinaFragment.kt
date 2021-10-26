package com.vmdevelopment.futsalzagi.clanarine.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.models.Clanarina
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentClanarineBinding

class ListaClanarinaFragment : Fragment() {
    private var _binding: FragmentClanarineBinding? = null
    private val binding get() = _binding!!

    private val mClanViewModel: ClanViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListaClanarinaAdapter by lazy { ListaClanarinaAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClanarineBinding.inflate(inflater, container, false)

        binding.clanarineRecyclerView.adapter = adapter
        binding.clanarineRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mClanViewModel.dohvatiSveClanarine.observe(viewLifecycleOwner, Observer { data ->
            val listaClanarinaPoMjesecu: MutableList<Clanarina> = mutableListOf()
            data.forEach {
                if (mSharedViewModel.parseMjeseciToInt(it.mjesec) == mSharedViewModel.trenutniMjesec() - 1) listaClanarinaPoMjesecu.add(it)
            }
            adapter.setData(listaClanarinaPoMjesecu)
        })

        binding.floatingActionButtonClanarine.setOnClickListener {
            findNavController().navigate(R.id.action_listaClanarinaFragment_to_dodajClanarinuFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lista_clanarina_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clanarine_menu_mjesec -> prikaziDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun prikaziDialog() {
        val mjeseci = R.array.mjeseci
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Mjesec:")
        builder.setItems(mjeseci, DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                0 -> prikaziClanarine(0)
                1 -> prikaziClanarine(1)
                2 -> prikaziClanarine(2)
                3 -> prikaziClanarine(3)
                4 -> prikaziClanarine(4)
                5 -> prikaziClanarine(5)
                6 -> prikaziClanarine(6)
                7 -> prikaziClanarine(7)
                8 -> prikaziClanarine(8)
                9 -> prikaziClanarine(9)
                10 -> prikaziClanarine(10)
                11 -> prikaziClanarine(11)
            }
        })

        val dialog = builder.create()
        dialog.show()
    }

    private fun prikaziClanarine(mjesec: Int) {
        mClanViewModel.dohvatiSveClanarine.observe(viewLifecycleOwner, Observer { data ->
            val listaClanarina: MutableList<Clanarina> = mutableListOf()
            data.forEach {
                if (mSharedViewModel.parseMjeseciToInt(it.mjesec) == mjesec) {
                    listaClanarina.add(it)
                }
            }
            adapter.setData(listaClanarina)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}