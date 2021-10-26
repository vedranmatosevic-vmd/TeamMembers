package com.vmdevelopment.futsalzagi.clanovi

import android.view.MenuItem
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vmdevelopment.futsalzagi.R

class BindingAdapter {

    companion object {

        @BindingAdapter("android:navigateToDodajClanaFragment")
        @JvmStatic
        fun navigateToDodajClanaFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listaClanovaFragment_to_dodajClanaFragment)
                }
            }
        }
        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View. INVISIBLE
            }
        }
    }
}