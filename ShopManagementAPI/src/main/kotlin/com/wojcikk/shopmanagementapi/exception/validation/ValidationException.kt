package com.wojcikk.shopmanagementapi.exception.validation

class ValidationException(val actualValue: Any, reason: String) : RuntimeException(reason)