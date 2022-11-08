package ru.laneboy.travelzonemapkit.presentation

import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import ru.laneboy.travelzonemapkit.R
import ru.laneboy.travelzonemapkit.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity(), UserLocationObjectListener {

    private lateinit var binding: ActivityMapBinding
    private val userLocationLayer: UserLocationLayer? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var landmarkListAdapter: LandmarkListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(binding.root)

        initializeMap()
        requestLocationPermission()
        setupLocation()

        binding.btnOpenBS.setOnClickListener {
            FragmentBottomSheetTest().show(supportFragmentManager, "openBottomSheetTag")
        }
    }

    private fun initializeMap() {
        val mapView: MapView = findViewById(R.id.mapview)
        mapView.map.isRotateGesturesEnabled = false
        mapView.map.move(
            CameraPosition(TARGET_LOCATION, 16.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    private fun setupLocation() {
        val mapKit: MapKit = MapKitFactory.getInstance()
        mapKit.resetLocationManagerToDefault()
        setUserLocation()
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                PERMISSIONS_REQUEST_FINE_LOCATION
            )
        }
    }

    private fun setUserLocation() {
        val mapView: MapView = findViewById(R.id.mapview)
        val mapKit: MapKit = MapKitFactory.getInstance()
        mapKit.createUserLocationLayer(mapView.mapWindow)
        userLocationLayer?.isVisible = true
        userLocationLayer?.isHeadingEnabled = true
        userLocationLayer?.setObjectListener(this)
    }

    override fun onStop() {
        val mapView: MapView = findViewById(R.id.mapview)
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        val mapView: MapView = findViewById(R.id.mapview)
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
        val mapView: MapView = findViewById(R.id.mapview)
        userLocationLayer?.setAnchor(
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()),
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.83).toFloat())
        )
        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                this, R.drawable.user_arrow
            )
        )
        val pinIcon = userLocationView.pin.useCompositeIcon()

        pinIcon.setIcon(
            "icon",
            ImageProvider.fromResource(this, R.drawable.icon),
            IconStyle().setAnchor(PointF(0f, 0f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(0f)
                .setScale(1f)
        )

        pinIcon.setIcon(
            "pin",
            ImageProvider.fromResource(this, R.drawable.search_result),
            IconStyle().setAnchor(PointF(0.5f, 0.5f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(1f)
                .setScale(0.5f)
        )

        userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001
    }

    override fun onObjectRemoved(userLocationView: UserLocationView) {
    }

    override fun onObjectUpdated(userLocationView: UserLocationView, p1: ObjectEvent) {
    }


    companion object {
        const val MAPKIT_API_KEY = "0f59f418-349a-4b78-ba49-04ebddce4f5b"
        val TARGET_LOCATION = Point(51.765443, 55.123961);
        const val PERMISSIONS_REQUEST_FINE_LOCATION = 1
    }
}