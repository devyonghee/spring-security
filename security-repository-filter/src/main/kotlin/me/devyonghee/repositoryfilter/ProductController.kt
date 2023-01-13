package me.devyonghee.repositoryfilter

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productRepository: ProductRepository
) {

    @GetMapping("/products/{text}")
    fun findProductsContaining(@PathVariable text: String): List<Product> {
        return productRepository.findAllByNameContains(text)
    }

    @GetMapping("/products/query/{text}")
    fun findProductsWithQuery(@PathVariable text: String): List<Product> {
        return productRepository.findAllByNameContainsWithQuery(text)
    }
}
