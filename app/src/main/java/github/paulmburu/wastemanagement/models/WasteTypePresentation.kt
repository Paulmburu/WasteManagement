package github.paulmburu.wastemanagement.models

data class WasteTypePresentation(
    val id: String,
    val name: String,
    val category: String,
    val points: Int,
    val image_url: String
)