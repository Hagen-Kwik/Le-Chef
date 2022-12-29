package com.uc.lechef.Models


data class Resep(
    val CreatedAt: String,
    val Created_by: Int,
    val DeletedAt: Any,
    val Description: String,
    val Foto: String,
    val ID: Int,
    val Judul: String,
    val Jumlahrating: Int,
    val Listbahan: List<Listbahan>,
    val Portionsize: Int,
    val Rating: Int,
    val Steps: String,
    val UpdatedAt: String,
    val User: User,
    val Users: Any,
    val Video: String,
    val Timetaken: String
)