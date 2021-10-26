package com.vmdevelopment.futsalzagi.clanarine.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.models.Clanarina
import com.vmdevelopment.futsalzagi.data.models.Mjeseci
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentUrediClanarinuBinding

class UrediClanarinuFragment : Fragment() {
    private var _binding: FragmentUrediClanarinuBinding? = null
    private val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mClanViewModel: ClanViewModel by viewModels()

    private val args by navArgs<UrediClanarinuFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrediClanarinuBinding.inflate(inflater, container, false)

        binding.currentClan.text = args.currentClanarinaItem.clan
        binding.currentSpinnerMjeseci.setSelection(mSharedViewModel.parseMjeseciToInt(args.currentClanarinaItem.mjesec))
        binding.currentIznosEditText.setText(args.currentClanarinaItem.iznos.toString())
        binding.currentPlacenoCheckBox.isChecked = args.currentClanarinaItem.placeno

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.uredi_clana_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> urediClanarinu()
            R.id.menu_delete -> potvrdiBrisanjeClanarine()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun potvrdiBrisanjeClanarine() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Da") { _,_ ->
            mClanViewModel.obrisiClanarinu(args.currentClanarinaItem)
            Toast.makeText(requireContext(), "Uspješno ste uklonili članarinu!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_urediClanarinuFragment_to_listaClanarinaFragment)
        }
        builder.setNegativeButton("Ne") { _,_ -> }
        builder.setTitle("Obrisati članarinu za '${args.currentClanarinaItem.mjesec} ${args.currentClanarinaItem.clan}'?")
        builder.setMessage("Jeste li sigurni da želite obrisati članarinu za ${args.currentClanarinaItem.mjesec} '${args.currentClanarinaItem.clan}'?")
        builder.create().show()
    }

    private fun urediClanarinu() {
        var clan = binding.currentClan.text.toString()
        var mjesec = binding.currentSpinnerMjeseci.selectedItem.toString()
        var iznos = binding.currentIznosEditText.text.toString()
        var placeno = binding.currentPlacenoCheckBox.isChecked


        var uredjenItem = Clanarina(
            args.currentClanarinaItem.id,
            clan,
            mSharedViewModel.parseMjeseci(mjesec),
            iznos.toInt(),
            placeno
        )
        mClanViewModel.urediClanarinu(uredjenItem)
        Toast.makeText(requireContext(), "Uspješno uređena članarina", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_urediClanarinuFragment_to_listaClanarinaFragment)
    }
}