package by.maxluxs.kr1.view.adapters

import by.maxluxs.kr1.model.vo.HomeFlower

interface FlowersCallback {
    fun openFlower(flower: HomeFlower)
    fun deleteFlower(flower: HomeFlower)
}