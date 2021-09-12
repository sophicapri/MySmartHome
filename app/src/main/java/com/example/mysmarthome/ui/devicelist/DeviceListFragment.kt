package com.example.mysmarthome.ui.devicelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mysmarthome.databinding.DeviceListFragmentBinding
import com.example.mysmarthome.model.ProductType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceListFragment : Fragment(), OnDeviceLongClickListener {
    private val viewModel by viewModels<DeviceListVM>()
    private var _binding: DeviceListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DeviceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeviceListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        displayDevices()
        addListeners()
    }

    private fun setupRecyclerView() {
        adapter = DeviceListAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun displayDevices() {
        viewModel.devices.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    private fun addListeners() {
        binding.filterBtn.setOnClickListener {
            viewModel.getFilteredList(listOf(ProductType.HEATER))
                .observe(viewLifecycleOwner, Observer(adapter::submitList))
        }
        binding.cancelFilterBtn.setOnClickListener {
            displayDevices()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = "MainActivity"
    }

    override fun onDeviceLongClick() {
        //display edit mode views
    }
}