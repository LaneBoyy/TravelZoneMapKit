package ru.laneboy.travelzonemapkit.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.laneboy.travelzonemapkit.R
import ru.laneboy.travelzonemapkit.databinding.FragmentBottomSheetTestBinding

class FragmentBottomSheetTest : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetTestBinding
    private lateinit var viewModel: MainViewModel
    private var landmarkListAdapter: LandmarkListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.landmarkList.observe(this) {
            landmarkListAdapter?.submitList(it)
        }

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView() {
        with(binding.rvLandmarkList) {
            landmarkListAdapter = LandmarkListAdapter()
            adapter = landmarkListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        landmarkListAdapter?.onLandmarkItemClickListener = {
            val intent = Intent(context, LandmarkItemActivity::class.java)
            intent.putExtra("TITLE", it.name)
            intent.putExtra("DESCRIPTION", it.description)
            startActivity(intent)

        }
    }
}