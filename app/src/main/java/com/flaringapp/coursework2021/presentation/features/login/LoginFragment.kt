package com.flaringapp.coursework2021.presentation.features.login

import androidx.core.widget.doAfterTextChanged
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.FragmentLoginBinding
import com.flaringapp.coursework2021.presentation.base.ModelledFragment
import com.flaringapp.coursework2021.presentation.features.login.state.LoginState
import com.flaringapp.coursework2021.presentation.features.login.viewmodel.LoginModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ModelledFragment(R.layout.fragment_login) {

    override val model: LoginModel by viewModel()

    val binding by viewBinding(FragmentLoginBinding::bind)

    private var lastState: LoginState? = null

    override fun initViews() = with(binding) {
        inputLogin.setText(model.login)
        inputPassword.setText(model.password)

        subscribeToModel()

        inputLogin.doAfterTextChanged {
            model.handleLoginChanged(it.toString())
        }
        inputPassword.doAfterTextChanged {
            model.handlePasswordChanged(it.toString())
        }
        binding.buttonLogin.setOnClickListener {
            model.login()
        }
    }

    private fun subscribeToModel() {
        model.loginValidation.observe(viewLifecycleOwner) {
            it.apply(this)
        }
        model.passwordValidation.observe(viewLifecycleOwner) {
            it.apply(this)
        }

        model.stateLiveData.observe(viewLifecycleOwner) {
            lastState?.release(this)
            it.apply(this)
            lastState = it
        }
    }

}