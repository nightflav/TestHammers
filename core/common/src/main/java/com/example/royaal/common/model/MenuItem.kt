package com.example.royaal.common.model

data class MenuItem(
    val type: MenuType,
    val title: String,
    val id: Int,
    val imgUrl: String,
    val description: String
) {
    companion object {
        val Empty = MenuItem(
            MenuType.Item, "", -1, "", ""
        )
    }
}

sealed interface MenuType {
    data object Item : MenuType
    data object Separator : MenuType
}
