package com.example.mirestaurante.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mirestaurante.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMap()
    }

    private fun setupMap() {
        binding.googleMap.onCreate(null)
        binding.googleMap.onResume()
        binding.googleMap.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_TERRAIN

        val myLocation = LatLng(-34.6032649,-58.3863535)

        val cameraPosition = CameraPosition.Builder()
            .target(myLocation)
            .zoom(16f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        val locationMark = MarkerOptions().position(myLocation)

        map.apply {
            animateCamera(cameraUpdate)
            addMarker(locationMark)
            uiSettings.isZoomControlsEnabled = true
        }
    }
}