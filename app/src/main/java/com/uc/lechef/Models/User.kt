package com.uc.lechef.Models

data class User(
    val CreatedAt: String,
    val DeletedAt: Any,
    val Email: String,
    val ID: Int,
    val Name: String,
    val Notification: Boolean,
    val Password: String,
    val Profile_picture: String,
    val Resep: Any,
    val UpdatedAt: String,
)