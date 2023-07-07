package me.devyonghee.securitydynamicacl.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int>
