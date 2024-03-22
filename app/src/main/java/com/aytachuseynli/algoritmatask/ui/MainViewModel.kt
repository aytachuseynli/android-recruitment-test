package com.aytachuseynli.algoritmatask.ui

import androidx.lifecycle.ViewModel
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val socketRepository: SocketRepository
) : ViewModel() {

//    val socketModelList: Flow<List<SocketModel>> = socketRepository.getSocketModels()
}
