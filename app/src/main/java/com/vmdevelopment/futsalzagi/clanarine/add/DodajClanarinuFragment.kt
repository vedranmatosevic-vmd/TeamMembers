package com.vmdevelopment.futsalzagi.clanarine.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.ClanoviDao
import com.vmdevelopment.futsalzagi.data.models.Clanarina
import com.vmdevelopment.futsalzagi.data.models.Mjeseci
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentClanarineBinding
import com.vmdevelopment.futsalzagi.databinding.FragmentDodajClanarinuBinding

class DodajClanarinuFragment : Fragment() {
    private var _binding: FragmentDodajClanarinuBinding? = null
    private val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mClanViewModel: ClanViewModel by viewModels()

    private var list: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDodajClanarinuBinding.inflate(inflater, container, false)

        binding.spinnerMjeseci.setSelection(mSharedViewModel.trenutniMjesec() - 1)

        setHasOptionsMenu(true)

        popuniSpinner()

        return binding.root
    }

    private fun popuniSpinner() {

        mClanViewModel.dohvatiSveClanove.observe(viewLifecycleOwner, Observer { data ->
            data.forEach {
                list.add(it.ime + " " + it.prezime)
            }
            val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list)
            binding.spinnerClanovi.adapter = adapter
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dodaj_clana_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mClan = binding.spinnerClanovi.selectedItem.toString()
        val mMjesec = binding.spinnerMjeseci.selectedItem.toString()
        val mIznos = binding.iznosEditText.text.toString()
        val mPlaceno = binding.placenoCheckBox.isChecked

        val validation = mSharedViewModel.provjeriPodatkeClanarine(mClan, mMjesec)
        if (validation) {
            // Insert data to database
            val newData = Clanarina(
                0,
                mClan,
                mSharedViewModel.parseMjeseci(mMjesec),
                mIznos.toInt(),
                mPlaceno
            )
            mClanViewModel.unesiClanarinu(newData)
            Toast.makeText(requireContext(), "Uspješno ste dodali članarinu", Toast.LENGTH_SHORT).show()
            // Vrati se na prethodni fragment
            findNavController().navigate(R.id.action_dodajClanarinuFragment_to_listaClanarinaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}