package com.wojcikk.shopmanagementapi.user.api

import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

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