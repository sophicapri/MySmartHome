package com.example.mysmarthome.ui.devicelist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.DeviceListFragmentBinding
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.ProductType
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceListFragment : Fragment(), OnDeviceLongClickListener {
    private val viewModel by viewModels<DeviceListVM>()
    private var _binding: DeviceListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DeviceListAdapter
    private var filterOn: Boolean = false

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
        adapter = DeviceListAdapter(this)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            val itemTouchHelper = ItemTouchHelper(itemTouchCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun displayDevices() {
        viewModel.devices.observe(viewLifecycleOwner) {
            if (!filterOn)
                adapter.submitList(it)
        }
    }

    private fun addListeners() {
        binding.apply {
            chipGroup.children.forEach {
                (it as Chip).setOnCheckedChangeListener { _, _ ->
                    if (it.isChecked)
                        displayFilteredList()
                }
            }
        }
        binding.cancelFilterBtn.setOnClickListener {
            filterOn = false
            binding.chipGroup.clearCheck()
            binding.recyclerView.smoothScrollToPosition(0)
            binding.cancelFilterBtn.visibility = View.GONE
            displayDevices()
        }
    }

    private fun displayFilteredList() {
        filterOn = true
        viewModel.getFilteredList(getProductTypes())
            .observe(viewLifecycleOwner) {
                if (filterOn)
                    adapter.submitList(it)
            }
    }

    private fun getProductTypes(): ArrayList<ProductType> {
        val productTypes = ArrayList<ProductType>()
        binding.chipGroup.checkedChipIds.forEach { id ->
            binding.cancelFilterBtn.visibility = View.VISIBLE
            when (id) {
                R.id.light_filter -> productTypes.add(ProductType.LIGHT)
                R.id.heater_filter -> productTypes.add(ProductType.HEATER)
                R.id.roller_shutter_filter -> productTypes.add(ProductType.ROLLER_SHUTTER)
            }
        }
        return productTypes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeviceLongClick(position: Int) {

    }

    private var itemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            val device: Device = adapter.currentList[position]
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Confirm deletion?")
            builder.setPositiveButton("DELETE") { _, _ ->
                viewModel.deleteDevices(listOf(device))
            }.setNegativeButton("CANCEL") { _, _ ->
                adapter.notifyItemChanged(position)
            }.show()
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}