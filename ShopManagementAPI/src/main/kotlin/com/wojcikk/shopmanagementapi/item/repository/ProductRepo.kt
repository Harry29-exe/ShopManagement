package com.wojcikk.shopmanagementapi.item.repository

import com.wojcikk.shopmanagementapi.item.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo : JpaRepository<Product, Long> {
}