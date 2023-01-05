package com.uc.lechef.Models

data class ResepFromBahanSearch(
    val length: Int,
    val listbahan: List<String>,
    val resep: List<Resep>,
    val status: String
)