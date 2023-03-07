package github.paulmburu.domain.fakes

import github.paulmburu.domain.models.*

object Data {
    object WasteTypesData {
        val response = arrayListOf(
            WasteType(
                id = "id_1",
                name = "Plastic",
                category = "RECYCLABLE",
                points = 50,
                image_url = "https://media.istockphoto.com/id/1328523682/vector/crushed-plastic-bottle.jpg?s=612x612&w=0&k=20&c=QFnG19RO05TFYrYT9AVcm6akbdKor3JujbgeJz2H-64="
            ),
            WasteType(
                id = "id_1",
                name = "Food waste",
                category = "NON_RECYCLABLE",
                points = 100,
                image_url = "https://png.pngtree.com/png-vector/20200325/ourmid/pngtree-throwing-garbage-of-food-waste-in-trash-can-png-image_2164742.jpg"
            ),
            WasteType(
                id = "id_1",
                name = "Batteries",
                category = "HAZARDOUS",
                points = 200,
                image_url = "https://toppng.com/uploads/preview/charging-battery-icon-flat-battery-icon-11563051103fb9hqfatdg.png"
            ),
        )
    }
}