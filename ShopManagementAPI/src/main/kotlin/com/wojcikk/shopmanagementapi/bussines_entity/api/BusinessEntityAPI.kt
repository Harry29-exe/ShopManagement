package com.wojcikk.shopmanagementapi.bussines_entity.api

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.bussines_entity.service.CreateBusinessEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/business-entities")
interface BusinessEntityAPI {

    @GetMapping("/all")
    fun getAll(): List<BusinessEntityDTO>

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): BusinessEntityDTO

    @PostMapping("/new")
    fun create(@RequestBody request: CreateBusinessEntityRequest)

}

typealias CreateBusinessEntityRequest = CreateBusinessEntity