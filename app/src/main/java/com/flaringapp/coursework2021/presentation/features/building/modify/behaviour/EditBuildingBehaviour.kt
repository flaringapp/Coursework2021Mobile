package com.flaringapp.coursework2021.presentation.features.building.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.presentation.features.building.modify.models.BuildingEditableData
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.koin.core.context.GlobalContext

@Parcelize
class EditBuildingBehaviour(
    override val building: Building
): ModifyBuildingBehaviour {

    @IgnoredOnParcel
    private val repository: EntityRepository by GlobalContext.get().inject()

    @IgnoredOnParcel
    override val preliminaryData: BuildingEditableData
        get() = BuildingEditableData(building)

    override suspend fun modifyBuilding(building: Building): CallResultNothing {
        return repository.editBuilding(building)
            .ignoreData()
    }
}