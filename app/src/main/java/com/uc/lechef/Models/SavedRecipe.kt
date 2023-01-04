package com.uc.lechef.Models

data class SavedRecipe(
    val CreatedAt: String,
    val DeletedAt: Any,
    val Email: String,
    val ID: Int,
    val Name: String,
    val Notification: Boolean,
    val Password: String,
    val Profile_picture: String,
    val Resep: List<Resep>,
    val UpdatedAt: String
)