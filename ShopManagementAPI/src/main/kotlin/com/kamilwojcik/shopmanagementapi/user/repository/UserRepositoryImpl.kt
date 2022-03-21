package com.kamilwojcik.shopmanagementapi.user.repository

import com.kamilwojcik.shopmanagementapi.user.domain.UserEntity
import com.kamilwojcik.shopmanagementapi.utils.repository.AbstractCrudRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query

class UserRepositoryImpl(
    template: R2dbcEntityOperations
) :
    UserRepository,
    AbstractCrudRepository<UserEntity, Int>(template)
{

    override val table: UserEntity.Info = UserEntity.Info


    override suspend fun findByUsername(username: String): UserEntity? {
        return template.select(
            query(
                where(table.USERNAME).`is`(username)
            )
            , table.clazz)
            .awaitFirstOrNull()
    }

}