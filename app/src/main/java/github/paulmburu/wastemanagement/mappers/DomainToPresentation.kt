package github.paulmburu.wastemanagement.mappers



import github.paulmburu.domain.models.WasteType
import github.paulmburu.wastemanagement.models.WasteTypePresentation

fun WasteType.toPresentation(): WasteTypePresentation {
    return WasteTypePresentation(
        id = id,
        name = name,
        category = category,
        points = points,
        image_url = image_url,
    )
}

