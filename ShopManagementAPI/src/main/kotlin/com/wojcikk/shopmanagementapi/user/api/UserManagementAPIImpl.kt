package com.wojcikk.shopmanagementapi.user.api

import com.wojcikk.shopmanagementapi.user.dto.UserDTO

class UserManagementAPIImpl : UserManagementAPI {

    override fun createUser(request: UserManagementAPI.CreateUser): UserDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<UserDTO> {
        TODO("Not yet implemented")
    }

    override fun getByUsername(username: String): UserDTO {
        TODO("Not yet implemented")
    }

    override fun updateUserDetails(username: String, request: UserManagementAPI.UpdateUserDetails): UserDTO {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String) {
        TODO("Not yet implemented")
    }

}