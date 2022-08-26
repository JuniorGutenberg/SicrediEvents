package com.orbital.home.ui.events.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.orbital.core.data.base.DataState
import com.orbital.core.fragment.BaseFragment
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.databinding.FragmentHomeBinding
import com.orbital.home.ui.events.view.adapter.EventsAdapter
import com.orbital.home.ui.events.viewModel.EventsViewModel
import com.orbital.home.ui.events.viewModel.EventsViewModelFactory
import com.orbital.sicredi_ui.utils.ViewUtils

class HomeFragment:BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel:EventsViewModel by viewModels{
        EventsViewModelFactory()
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
        getEvents()
        observer()
        initComponents()
    }

    private fun getEvents(){
        viewModel.getEvents()
    }

    private fun observer(){
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success ->{successEvents(it.data)}
                is DataState.Error ->{errorEvents(it.exception.toString())}
            }
        }
    }

    private fun initComponents(){
        binding.apply {
            rvEvents.isNestedScrollingEnabled = false
            rvEvents.setHasFixedSize(true)
            rvEvents.layoutManager = LinearLayoutManager(context)
        }
        context?.let { ViewUtils.showLoading(it) }
    }

    private fun errorEvents(msg:String) {
        Toast.makeText(context, "Error: $msg", Toast.LENGTH_LONG).show()
        ViewUtils.hideLoading()
    }

    private fun successEvents(data: List<EventsResponse.Body>) {
        binding.rvEvents.adapter = context?.let { EventsAdapter(data, it, this) }
        ViewUtils.hideLoading()
    }

    private fun getViewBinding():View{
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
}