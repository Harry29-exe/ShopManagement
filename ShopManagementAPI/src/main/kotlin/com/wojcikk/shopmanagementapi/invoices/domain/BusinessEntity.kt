package com.wojcikk.shopmanagementapi.invoices.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BusinessEntity(
    val entityName: String
) {

    @Column(unique = true, updatable = false)
    val nip: String? = null
    @Column
    val email: String? = null
    @Column
    val phoneNumber: String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(nullable = false, updatable = false, unique = true)
    val pubId: UUID = UUID.randomUUID()

}