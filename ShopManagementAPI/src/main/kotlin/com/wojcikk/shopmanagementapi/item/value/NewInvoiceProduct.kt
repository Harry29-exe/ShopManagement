package com.wojcikk.shopmanagementapi.item.value

import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.math.BigDecimal

class NewInvoiceItem(
    val quantity: Int,
    val discount: BigDecimal = BigDecimal(0),
    val name: String? = null,
    val price: BigDecimal? = null,
    val taxRate: BigDecimal? = null,
    val productId: Long? = null
) : Validated {

    override fun isValid(): Boolean {
        return !(productId == null &&
                (price == null || taxRate == null || name == null))
    }

}
