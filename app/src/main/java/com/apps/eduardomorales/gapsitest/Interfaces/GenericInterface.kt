package com.apps.eduardomorales.gapsitest.Interfaces

import com.apps.eduardomorales.gapsitest.Models.Products

interface GenericInterface {
    fun itemClicked(position: Int)
    fun productsRequestResult(products: MutableList<Products>)
    fun requestError()
}