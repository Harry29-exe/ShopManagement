package com.wojcikk.shopmanagementapi.products.repository

import com.wojcikk.shopmanagementapi.products.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepo : JpaRepository<Product, Long> {

    fun findByPubId(pubId: UUID): Product?

}