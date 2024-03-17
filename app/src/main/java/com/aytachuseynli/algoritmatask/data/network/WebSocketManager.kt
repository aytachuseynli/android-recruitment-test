package com.aytachuseynli.algoritmatask.data.network

import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.*
import javax.inject.Inject

@ActivityScoped
class WebSocketManager @Inject constructor(private val client: OkHttpClient) {


}
