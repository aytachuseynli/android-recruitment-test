package com.aytachuseynli.algoritmatask.ui

import androidx.lifecycle.ViewModel
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class MainViewModel @Inject constructor(private val repo: SocketRepository): ViewModel() {


}

