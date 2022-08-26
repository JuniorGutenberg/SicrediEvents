package com.orbital.home.ui.details.view.bottomSheet

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orbital.core.utils.ValidateUtils
import com.orbital.home.databinding.BottomSheetDetailsBinding
import com.orbital.home.ui.common.enums.IntsEnum
import com.orbital.home.ui.common.enums.ValidatesEnum
import com.orbital.home.ui.details.view.contract.DetailsContract

class DetailsBottomSheet(private var listener: DetailsContract) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return getViewBinding(inflater, container)
    }

    private fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = BottomSheetDetailsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cleanEditText()
        setEditText()
        setClick()
    }
    private fun setClick(){
        binding.apply {
            btnConfirm.setOnClickListener {
                listener.confirmEmail(itNome.getEditText().text.toString(),
                    itEmail.getEditText().text.toString())
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun cleanEditText(){
        binding.let { binding ->
            binding.apply {
                itEmail.cleanComponents()
                itNome.cleanComponents()
            }
        }
    }

    private fun setEditText(){
        setEmailInputText()
        setNameInputText()
        setListenerInput()
    }
    private fun setListenerInput(){
        binding.apply {
            itNome.getEditText().addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    validate(ValidatesEnum.Details.NAME.value)
                }

            })
            itEmail.getEditText().addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    validate(ValidatesEnum.Details.EMAIL.value)
                }

            })
        }
    }
    private fun validate(item:String){

        when(item){
            ValidatesEnum.Details.NAME.value-> validateName()
            ValidatesEnum.Details.EMAIL.value-> validateEmail()
        }

        binding.apply {

            btnConfirm.isEnabled = (ValidateUtils.isValidateName(itNome.getEditText().text.toString(),
                IntsEnum.Details.CHAR_FINAL.value) &&
                    ValidateUtils.isValidateEmail(itEmail.getEditText().text.toString()))
        }
    }
    private fun validateEmail() {
        binding.apply {
            if(ValidateUtils.isValidateEmail(itEmail.getEditText().text.toString())){
                itEmail.removeError()
            }else{
                itEmail.setError(getString(com.orbital.home.R.string.details_error_email))
            }
        }
    }

    private fun validateName() {
        binding.apply {
            if(ValidateUtils.isValidateName(itNome.getEditText().text.toString(),
                    IntsEnum.Details.CHAR_FINAL.value)){
                itNome.removeError()
            }else{
                val text = String.format(getString(com.orbital.home.R.string.details_error_name_variable,
                    IntsEnum.Details.CHAR_FINAL.value.toString()))
                itNome.setError(text)
            }
        }
    }
    private fun setNameInputText() {
        binding.let { binding ->
            binding.apply {
                itNome.setTextTilte("Nome Completo")
            }
        }
    }

    private fun setEmailInputText(){
        binding.let { binding ->
            binding.apply {
                itEmail.setTextTilte("E-mail")
                itEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            }
        }
    }
}