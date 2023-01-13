package me.devyonghee.methodfilter

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreFilter
import org.springframework.stereotype.Service

@Service
class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    fun sellProducts(products: List<Product>): List<Product> {
        return products;
    }

    @PostFilter("filterObject.owner == authentication.name")
    fun findProducts(): List<Product> {
        return mutableListOf(
            Product("beer", "readUser"),
            Product("candy", "readUser"),
            Product("chocolate", "writeUser")
        )
    }
}
