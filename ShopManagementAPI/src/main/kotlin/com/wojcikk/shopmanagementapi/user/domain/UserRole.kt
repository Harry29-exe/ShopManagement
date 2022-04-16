package com.wojcikk.shopmanagementapi.user.domain

import javax.persistence.*

@Entity
@Table(name = "user_roles")
class UserRole(
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    val role: Role,
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val user: UserEntity,
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

}