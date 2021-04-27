package com.flaringapp.coursework2021.data.repository.entity.models

sealed class RoomType {
    object OpenSpace: RoomType()
    object Private: RoomType()
}