package com.flaringapp.coursework2021.data.storage

interface DataStorage {

    var token: String

    var userId: String
    var name: String
    var surname: String
    var email: String

    var userType: String

    var buildingId: String

}