package com.apps.eduardomorales.gapsitest.Models

import java.io.Serializable

data class Products(
val name: String,
val thumbnail: String,
val location: String,
val price: Float

): Serializable {

}