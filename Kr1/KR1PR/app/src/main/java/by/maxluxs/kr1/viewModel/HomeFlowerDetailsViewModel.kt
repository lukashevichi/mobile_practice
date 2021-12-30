package by.maxluxs.kr1.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.repositories.HomeFlowersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFlowerDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: HomeFlowersRepository,
) : ViewModel() {

    val id: String = savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")

    val flower get() = repository.getFlowerById(id).asLiveData()

    fun saveFlower(homeFlower: HomeFlower) =
        viewModelScope.launch { repository.insertFlower(homeFlower) }

}