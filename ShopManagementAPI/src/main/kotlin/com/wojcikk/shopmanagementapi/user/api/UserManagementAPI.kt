package com.wojcikk.shopmanagementapi.user.api

import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import org.springframework.web.bind.annotation.*

@RequestMapping("/user/")
interface UserManagementAPI {

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): UserDTO

    @GetMapping("all")
    fun getAll(): List<UserDTO>

    @GetMapping("{username}")
    fun getByUsername(@PathVariable username: String): UserDTO

    @PatchMapping("{username}")
    fun updateUserDetails(@PathVariable username: String, @RequestBody request: UpdateUserDetails): UserDTO

    @DeleteMapping("{username}")
    fun deleteUser(@PathVariable username: String)


    class CreateUserRequest(val username: String, val name: String, val surname: String, val password: String)

    class UpdateUserDetails(val name: String?, val surname: String?)

}