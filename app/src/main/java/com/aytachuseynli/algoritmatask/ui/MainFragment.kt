package com.aytachuseynli.algoritmatask.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aytachuseynli.algoritmatask.common.utils.ConnectivityUtil
import com.aytachuseynli.algoritmatask.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = SocketAdapter()
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
        viewModel.fetchData(requireContext())
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.adapter = adapter

        // Observe socket model list and update adapter
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.socketModelList.collect { socketList ->
                adapter.submitList(socketList)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        // Observe connection status and update UI background color
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.socketIsConnected.collect { isConnected ->
                val backgroundColor = if (isConnected) Color.GREEN else Color.RED
                binding.connectionIndicator.setBackgroundColor(backgroundColor)
            }
        }

        // Register connectivity receiver to listen for changes
        ConnectivityUtil.registerConnectivityReceiver(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Unregister connectivity receiver when fragment is destroyed
        ConnectivityUtil.unregisterConnectivityReceiver(requireContext())
    }
}