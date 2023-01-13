package me.devyonghee.repositoryfilter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.access.prepost.PostFilter

interface ProductRepository : JpaRepository<Product, Long> {

    @PostFilter("filterObject.owner == authentication.name")
    fun findAllByNameContains(text: String): List<Product>


    @Query("select p from Product p where p.name like %:text% and p.owner = ?#{authentication.name}")
    fun findAllByNameContainsWithQuery(text: String): List<Product>
}
