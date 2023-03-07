package github.paulmburu.network.models

data class WasteTypeDto(
    val id: String,
    val name: String,
    val category: String,
    val points: Int,
    val image_url: String
)