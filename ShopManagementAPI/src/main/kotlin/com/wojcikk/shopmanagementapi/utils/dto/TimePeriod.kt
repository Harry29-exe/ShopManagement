package com.wojcikk.shopmanagementapi.utils.dto

import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.util.Date

class TimePeriod(
    val from: Date,
    val to: Date
): Validated {

    override fun isValid(): Boolean {
        return from.before(to)
    }
}