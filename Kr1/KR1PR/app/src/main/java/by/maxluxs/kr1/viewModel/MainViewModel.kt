package by.maxluxs.kr1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.repositories.HomeFlowersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HomeFlowersRepository
) : ViewModel() {

    fun addFlower(flower: HomeFlower) = viewModelScope.launch { repository.insertFlower(flower) }

}