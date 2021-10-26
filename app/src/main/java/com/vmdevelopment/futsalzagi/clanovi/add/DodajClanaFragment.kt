package com.vmdevelopment.futsalzagi.clanovi.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentDodajClanaBinding

class DodajClanaFragment : Fragment() {
    private var _binding: FragmentDodajClanaBinding? = null
    private val binding get() = _binding!!

    private val mClanViewModel: ClanViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDodajClanaBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dodaj_clana_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            unesiClanaUBazu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun unesiClanaUBazu() {
        val mIme = binding.imeEditText.text.toString()
        val mPrezime = binding.prezimeEditText.text.toString()
        val mOib = binding.oibEditText.text.toString()
        val mRodjendan = binding.rodjendanEditText.text.toString()
        val mDatumUpisa = binding.datumUpisaEditText.text.toString()

        val validation = mSharedViewModel.provjeriPodatke(mIme)

        if (validation) {
            // Unesi clana u bazu podataka
            val noviClan = Clan(
                0,
                mIme,
                mPrezime,
                mOib,
                mRodjendan,
                mDatumUpisa,
                true
            )
            mClanViewModel.unesiClana(noviClan)
            Toast.makeText(requireContext(), "Uspješno ste dodali člana", Toast.LENGTH_SHORT).show()
            // Vrati se na prethodni fragment
            findNavController().navigate(R.id.action_dodajClanaFragment_to_listaClanovaFragment)
        } else {
            Toast.makeText(requireContext(), "Molimo vas da unesete ime", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}