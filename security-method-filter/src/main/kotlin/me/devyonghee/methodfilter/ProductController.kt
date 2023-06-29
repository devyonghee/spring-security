package me.devyonghee.methodfilter

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/sell")
    fun sellProduct(): List<Product> {
        return productService.sellProducts(
            mutableListOf(
                Product("beer", "readUser"),
                Product("candy", "readUser"),
                Product("chocolate", "writeUser")
            )
        )
    }

    @GetMapping("/find")
    fun findProducts(): List<Product> {
        return productService.findProducts()
    }
}
