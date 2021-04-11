package ru.examples.bilimdonuz

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ru.examples.bilimdonuz.databinding.SettingFragmentBinding

class SettingsFragment :Fragment(R.layout.setting_fragment){

    lateinit var settingsFragment: SettingFragmentBinding
    private val navController by lazy {
        Navigation.findNavController(requireActivity(),R.id.fragment)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=SettingFragmentBinding.bind(view)
        settingsFragment=binding

        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(R.id.books_navigation)
                    }

                })
    }
}