package com.matejdev.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.matejdev.demo.base.view.BaseViewModel
import com.matejdev.demo.presentation.viewmodel.state.UserListState
import javax.inject.Inject

class ErrorViewModel
@Inject constructor() : BaseViewModel() {

    val state = MutableLiveData<UserListState>()

}
