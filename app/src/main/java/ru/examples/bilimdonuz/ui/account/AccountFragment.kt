package ru.examples.bilimdonuz.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.common.getString
import ru.examples.bilimdonuz.databinding.AccountFragmentBinding
import ru.examples.bilimdonuz.model.Account

class AccountFragment ():Fragment(R.layout.account_fragment){

    private var accountbinding:AccountFragmentBinding?=null
    private val accountViewModel:AccountViewModel by activityViewModels()
    private val navController by lazy { Navigation.findNavController(requireActivity(),R.id.fragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                val binding=AccountFragmentBinding.bind(view)
        accountbinding=binding
        binding.accountToolbar.setNavigationOnClickListener {
            navController.navigate(R.id.books_navigation)
        }

        accountViewModel.accountRead()
        accountViewModel.accountReadLd.observe(viewLifecycleOwner, Observer {
            binding.tvName.text="${it.name}  ${it.surname}"
            binding.edAccountName.setText(it.name)
            binding.edAccountSurname.setText(it.surname)
            binding.edAccountAge.setText(it.age)
        })
        binding.btSave.setOnClickListener {
             val age=binding.edAccountAge.getString()
             val name=binding.edAccountName.getString()
             val surname=binding.edAccountSurname.getString()
              accountViewModel.accountWrite(Account(name,surname,age))
              navController.navigate(R.id.books_navigation)
        }

    }

}