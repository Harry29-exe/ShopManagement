package com.wojcikk.shopmanagementapi.seller.domain

import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import java.util.*
import javax.persistence.*

@Entity
class Seller(
    user: UserEntity
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

    private var isActive: Boolean = true

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private var user: UserEntity? = user

}