package com.wojcikk.shopmanagementapi.user.api

import com.wojcikk.shopmanagementapi.user.api.UserManagementAPI.UpdateUserDetails
import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import com.wojcikk.shopmanagementapi.user.service.UserRepoService
import com.wojcikk.shopmanagementapi.user.service.UserRepoService.UpdateUserName
import com.wojcikk.shopmanagementapi.utils.secure.isAdmin
import com.wojcikk.shopmanagementapi.utils.secure.usernameMatchOrIsAdmin
import org.springframework.web.bind.annotation.RestController

@RestController
class UserManagementAPIImpl(
    private val userService: UserRepoService
) : UserManagementAPI {

    override fun createUser(request: UserManagementAPI.CreateUserRequest): UserDTO = isAdmin {
        userService.create(
            UserRepoService.CreateUser(
                request.username,
                request.name,
                request.surname,
                request.password
            )
        )
    }

    override fun getAll(): List<UserDTO> = isAdmin {
        userService.getAll()
    }

    override fun getByUsername(username: String): UserDTO = usernameMatchOrIsAdmin(username) {
        userService.get(username)
    }

    override fun updateUserDetails(username: String, request: UpdateUserDetails): UserDTO =
        usernameMatchOrIsAdmin(username) {
            userService.updateDetails(
                UpdateUserName(
                    username, request.name, request.surname
                )
            )
        }

    override fun deleteUser(username: String) = isAdmin {
        userService.deleteUser(username)

        Unit
    }

}