package com.aytachuseynli.algoritmatask.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aytachuseynli.algoritmatask.data.network.SocketInstance
import com.aytachuseynli.algoritmatask.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = WebSocketAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        // Observe socket model list and update adapter
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.socketModelList.collect { socketList ->
                adapter.submitList(socketList)
            }
        }

        // Observe connection status and update UI text color
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isOnline.collect { isConnected ->
                if (isConnected) {
                    binding.connectionStatusTextView.text = "Connected"
                    binding.connectionStatusTextView.setTextColor(Color.BLACK)
                } else {
                    binding.connectionStatusTextView.text = "No internet connection"
                    binding.connectionStatusTextView.setTextColor(Color.RED)
                }
            }
        }

        // Check internet connectivity
        viewModel.checkInternetConnectivity(requireContext())
        SocketInstance.establishConnection()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

