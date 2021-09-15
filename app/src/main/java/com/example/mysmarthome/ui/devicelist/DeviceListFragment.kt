package com.example.mysmarthome.ui.devicelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.DeviceListFragmentBinding
import com.example.mysmarthome.model.*
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceListFragment : Fragment(), DeviceListAdapter.OnDeviceClickListener {
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
            val itemTouchHelper = ItemTouchHelper(getItemTouchHelper())
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun getItemTouchHelper() = object :
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
            viewModel.deleteDevices(listOf(device))
            val snackBar =
                Snackbar.make(binding.root, getString(R.string.deleting_device), Snackbar.LENGTH_LONG)
                    .addCallback(object : BaseCallback<Snackbar>() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)
                            if (event == DISMISS_EVENT_TIMEOUT) {
                                viewModel.deleteDevices(listOf(device))
                            }
                        }
                    }).setAction(getString(R.string.undo_delete)) {
                        viewModel.insertDevice(device).observe(viewLifecycleOwner) { rowId ->
                            if (rowId != 1L)
                                adapter.notifyItemChanged(position)
                        }
                    }
            snackBar.show()
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
                    if (chipGroup.checkedChipIds.isNotEmpty())
                        displayFilteredList()
                    else
                        cancelFilter()
                }
            }
        }
        binding.cancelFilterBtn.setOnClickListener { cancelFilter() }
        binding.profileBtn.setOnClickListener {
            findNavController().navigate(
                DeviceListFragmentDirections.actionDeviceListFragmentToUserProfileFragment()
            )
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

    private fun cancelFilter() {
        filterOn = false
        if (binding.chipGroup.checkedChipIds.isNotEmpty())
            binding.chipGroup.clearCheck()
        binding.cancelFilterBtn.visibility = View.GONE
        displayDevices()
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

    override fun onDeviceClick(device: Device) {
        when (device) {
            is Light -> findNavController().navigate(
                DeviceListFragmentDirections.actionDeviceListFragmentToLightDetailFragment(
                    device
                )
            )
            is Heater -> findNavController().navigate(
                DeviceListFragmentDirections.actionDeviceListFragmentToHeaterDetailFragment(
                    device
                )
            )
            is RollerShutter -> findNavController().navigate(
                DeviceListFragmentDirections.actionDeviceListFragmentToRollerShutterDetailFragment(
                    device
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}