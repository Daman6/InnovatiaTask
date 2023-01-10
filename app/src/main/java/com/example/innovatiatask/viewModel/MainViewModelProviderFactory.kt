package com.example.innobuzztask.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innovatiatask.repo.Repo
import com.example.innovatiatask.viewModel.MainViewModel

class MainViewModelProviderFactory(val repo: Repo,val context:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo,context) as T
    }
}