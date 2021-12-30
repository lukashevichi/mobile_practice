package by.maxluxs.kr1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import by.maxluxs.kr1.Plants
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.repositories.HomeFlowersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFlowersViewModel @Inject constructor(
    private val repository: HomeFlowersRepository
) : ViewModel() {

    val flower = repository.getFlowers().asLiveData()

    fun insertFlower(flower: HomeFlower) = viewModelScope.launch { repository.insertFlower(flower) }

    fun insertFlowers(flower: List<HomeFlower>) =
        viewModelScope.launch { repository.insertFlowers(flower) }

    fun deleteFlower(flower: HomeFlower) = viewModelScope.launch { repository.deleteFlower(flower) }

}