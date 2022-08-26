package com.orbital.home.ui.events.view.adapter.viewHolder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.orbital.sicredi_ui.R
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.databinding.ItemEventsBinding
import com.orbital.home.ui.common.enums.ParamsEnum

class EventsViewHolder(private var binding: ItemEventsBinding,
                       private var context: Context,
                       private var fragment: Fragment):RecyclerView.ViewHolder(binding.root) {


   fun bind(item: EventsResponse.Body){
       item.image?.let { setImage(it) }
       binding.tv.text = item.title

       itemView.setOnClickListener {
           val bundle = Bundle().apply {
               putParcelable(ParamsEnum.PARAMS_ITEM.value,item)
           }
           fragment.findNavController().navigate(com.orbital.home.R.id.action_go_to_details,bundle)
       }
   }
    private fun setImage(image:String){
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

        Glide.
        with(context)
            .load(image)
            .apply(requestOptions)
            .error(R.drawable.ic_img_not_found)
            .into(binding.iv)
    }


}