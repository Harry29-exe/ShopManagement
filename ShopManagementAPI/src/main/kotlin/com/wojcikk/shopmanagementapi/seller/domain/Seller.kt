package com.wojcikk.shopmanagementapi.seller.domain

import com.fasterxml.jackson.databind.BeanProperty
import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import javax.persistence.*

@Entity
class Seller(
    user: UserEntity
) {

    @Id
    private val privateId: Long = 0

    private var isActive: Boolean = true

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private var user: UserEntity? = user

}