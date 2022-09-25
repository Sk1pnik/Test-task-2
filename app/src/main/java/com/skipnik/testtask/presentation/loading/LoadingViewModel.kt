package com.skipnik.testtask.presentation.loading

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.skipnik.testtask.data.PreferenceManager
import com.skipnik.testtask.data.net.LoadApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val api: LoadApi,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val preference = preferenceManager.preferencesFlow

    val link = preference.map {
        serverResponse(it)
    }

    private suspend fun serverResponse(preferenceKey: String) = withContext(Dispatchers.IO) {
        if (preferenceKey == "") {
            Log.d("SOMETHING", "CAllled")
            val response = api.response()
            preferenceManager.updateLink(response.home)
            response.link
        } else {
            preferenceKey
        }
    }
}