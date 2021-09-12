package com.example.mysmarthome.ui.devicelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mysmarthome.model.ProductType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceListFragment : Fragment() {
    private val viewModel by viewModels<DeviceListVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getDevices().observe(viewLifecycleOwner) {
            //val list : List<Light> = it.filterIsInstance<Light>()
            Log.d("MainActivity", "onCreate: DEVICE FROM LOCAL $it }")
        }


        //onClick :
        // get buttonchecked and make list
        viewModel.getFilteredList(listOf(ProductType.ROLLER_SHUTTER)).observe(viewLifecycleOwner) {
            Log.d("MainActivity", "onCreate: DEVICES FILTERED $it }")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}