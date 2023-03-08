package github.paulmburu.local.mappers

import github.paulmburu.domain.models.*
import github.paulmburu.local.models.ProgressEntity
import github.paulmburu.local.models.WasteTypeEntity

fun WasteTypeEntity.toDomain(): WasteType {
    return WasteType(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url
    )
}

fun ProgressEntity.toDomain(): WasteType {
    return WasteType(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url
    )
}