package github.paulmburu.network.mappers

import github.paulmburu.domain.models.*
import github.paulmburu.network.models.*


fun WasteTypeDto.toDomain(): WasteType {
    return WasteType(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url
    )
}