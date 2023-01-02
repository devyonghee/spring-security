package me.devyonghee.securityapplication.application

import me.devyonghee.securityapplication.domain.Product
import me.devyonghee.securityapplication.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}
