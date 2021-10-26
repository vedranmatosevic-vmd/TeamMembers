package com.vmdevelopment.futsalzagi.clanovi.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.clanarine.list.ListaClanarinaFragmentDirections
import com.vmdevelopment.futsalzagi.clanovi.SharedViewModel
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.viewmodel.ClanViewModel
import com.vmdevelopment.futsalzagi.databinding.FragmentUrediClanaBinding

class UrediClanaFragment : Fragment() {
    private var _binding: FragmentUrediClanaBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UrediClanaFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mClanViewModel: ClanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrediClanaBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.trenutnaImeEditText.setText(args.currentItem.ime)
        binding.trenutnaPrezimeEditText.setText(args.currentItem.prezime)
        binding.trenutnaOibEditText.setText(args.currentItem.oib)
        binding.trenutnaRodjendanEditText.setText(args.currentItem.datumRodjenja)
        binding.trenutnaDatumUpisaEditText.setText(args.currentItem.datumUclanjenja)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.uredi_clana_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val  action = UrediClanaFragmentDirections.actionUrediClanaFragmentToClanarinePoClanuFragment(args.currentItem)
        when (item.itemId) {
            R.id.menu_save -> urediClana()
            R.id.menu_clanarine_po_clanu -> findNavController().navigate(action)
            R.id.menu_delete -> potvrdiBrisanjeClana()
        }
        return super.onOptionsItemSelected(item)
    }

    // Prikaži AlertDialog kao potvrdu brisanja Člana
    private fun potvrdiBrisanjeClana() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Da") { _,_ ->
            mClanViewModel.obrisiClana(args.currentItem)
            Toast.makeText(requireContext(), "Uspješno ste uklonili člana!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_urediClanaFragment_to_listaClanovaFragment)
        }
        builder.setNegativeButton("Ne") { _,_ -> }
        builder.setTitle("Obrisati '${args.currentItem.ime} ${args.currentItem.prezime}'?")
        builder.setMessage("Jeste li sigurni da želite obrisati '${args.currentItem.ime} ${args.currentItem.prezime}'?")
        builder.create().show()
    }

    private fun urediClana() {
        val ime = binding.trenutnaImeEditText.text.toString()
        val prezime = binding.trenutnaPrezimeEditText.text.toString()
        val oib = binding.trenutnaOibEditText.text.toString()
        val datumRodjenja = binding.trenutnaRodjendanEditText.text.toString()
        val datumUpisa = binding.trenutnaDatumUpisaEditText.text.toString()

        val validation = mSharedViewModel.provjeriPodatke(ime)
        if (validation) {
            // Uređivanje trenutnog člana
            val uredjenClan = Clan(
                args.currentItem.id,
                ime,
                prezime,
                oib,
                datumRodjenja,
                datumUpisa,
                true
            )
            mClanViewModel.urediClana(uredjenClan)
            Toast.makeText(requireContext(), "Uspješno ste uredili člana!", Toast.LENGTH_SHORT).show()
            // Vrati se nazad
            findNavController().navigate(R.id.action_urediClanaFragment_to_listaClanovaFragment)
        } else {
            Toast.makeText(requireContext(), "Molimo vas da unesete ime", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}