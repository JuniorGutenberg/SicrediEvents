package com.orbital.home.ui.details.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.orbital.core.data.base.DataState
import com.orbital.core.enums.FormatEnum
import com.orbital.core.fragment.BaseFragment
import com.orbital.core.utils.FormatUtils
import com.orbital.home.data.details.remote.model.DetailsRequest
import com.orbital.sicredi_ui.R
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.databinding.FragmentDetailsBinding
import com.orbital.home.ui.details.view.bottomSheet.DetailsBottomSheet
import com.orbital.home.ui.details.view.contract.DetailsContract
import com.orbital.home.ui.common.enums.ParamsEnum
import com.orbital.home.ui.details.viewModel.DetailsViewModel
import com.orbital.home.ui.details.viewModel.DetailsViewModelFactory

class DetailsFragment:BaseFragment(), DetailsContract {
    private lateinit var binding: FragmentDetailsBinding
    private var item:EventsResponse.Body?=null
    private var price:String=""
    private var date:String=""

    private val viewModel:DetailsViewModel by viewModels{
        DetailsViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras()
        observer()
        initComponents()
    }
    private fun observer(){
        viewModel.check.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success->{successCheckIn()}
                is DataState.Error ->{errorCheckIn(it.exception.toString())}
            }
        }
    }

    private fun errorCheckIn(toString: String) {
        Toast.makeText(context,getString(com.orbital.home.R.string.details_check_error),Toast.LENGTH_LONG).show()
        Log.e(DetailsFragment::class.java.name,toString)
    }

    private fun successCheckIn() {
        Toast.makeText(context,getString(com.orbital.home.R.string.details_check_sucess),Toast.LENGTH_LONG).show()
    }

    private fun initComponents(){
        setImage()
        setTexts()
        setClicks()
    }
    private fun setClicks(){
        binding.apply {
            btnShare.setOnClickListener{
                shareEvent()
            }
            btnCheck.setOnClickListener {
                openBottomSheet()
            }
        }
    }
    private fun openBottomSheet(){
        val bottomSheetDetails = DetailsBottomSheet(this)
        bottomSheetDetails.show((context as FragmentActivity).supportFragmentManager, "BottomSheetDetails")
    }
    private fun shareEvent(){
        val intent = Intent(Intent.ACTION_SEND)
        val msg = gerarMsg()


        intent.putExtra(Intent.EXTRA_SUBJECT,getString(com.orbital.home.R.string.details_title_share))
        intent.putExtra(Intent.EXTRA_TEXT,msg)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,getString(com.orbital.home.R.string.details_share_name)))
    }
    private fun gerarMsg():String{
        return item?.title.toString()+
                "\n"+
                "\n"+ getString(com.orbital.home.R.string.details_share_day)+" "+date+
                "\n"+
                "\n"+ getString(com.orbital.home.R.string.details_share_price)+" "+price+
                "\n"+
                "\n"+ getString(com.orbital.home.R.string.details_share_final)
    }
    private fun setImage(){
        binding.apply {
            Glide.with(this@DetailsFragment)
                .load(item?.image)
                .centerCrop()
                .error(R.drawable.ic_img_not_found)
                .into(ivBackground)
        }
    }
    private fun setTexts(){
       binding.apply {
           tvDesc.text = item?.description

           price = FormatEnum.Format.CODE_REAL.value + FormatUtils.formatCurrency(item?.price)
           tvPrice.text = price

           date =  item?.let { FormatUtils.timeToDate(it.date.toString(),FormatEnum.Format.FORMAT_DATA_BRAZIL.value) }
               .toString()
           tvCalendar.text = date
       }
    }
    private fun getExtras(){
        item = arguments?.getParcelable(ParamsEnum.PARAMS_ITEM.value)
    }

    private fun getViewBinding():View{
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun confirmEmail(name:String, email:String) {
        viewModel.checkIn(getBody(name, email))
    }

    private fun getBody(name:String, email:String):DetailsRequest.Body{
        return DetailsRequest.Body(
            item?.id.toString(),
            name,
            email
        )
    }
}