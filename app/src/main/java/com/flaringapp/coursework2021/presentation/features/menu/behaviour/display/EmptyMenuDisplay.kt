package com.flaringapp.coursework2021.presentation.features.menu.behaviour.display

open class EmptyMenuDisplay: MenuDisplay {

    override val showBuildings: Boolean = false
    override val showManagers: Boolean = false
    override val showResidents: Boolean = false
    override val showRooms: Boolean = false
    override val showPayments: Boolean = false
    override val showAdminDashboard: Boolean = false
    override val showManagerDashboard: Boolean = false
}