package com.wojcikk.shopmanagementapi.exception.resources

class ResourceNotExistException(
    val resourceClass: Class<out Any>,
    val fieldName: String,
    val fieldValue: Any
) : RuntimeException(
    message = "Resource: ${resourceClass.simpleName} " +
            "with field: $fieldName of value: $fieldValue " +
            "does not exist")