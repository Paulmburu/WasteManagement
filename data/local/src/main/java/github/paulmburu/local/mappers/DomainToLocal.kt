package github.paulmburu.local.mappers

import github.paulmburu.domain.models.WasteType
import github.paulmburu.local.models.ProgressEntity
import github.paulmburu.local.models.WasteTypeEntity

fun WasteType.toLocal(): WasteTypeEntity {
    return WasteTypeEntity(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url
    )
}

fun WasteType.toProgressEntity(): ProgressEntity {
    return ProgressEntity(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url
    )
}