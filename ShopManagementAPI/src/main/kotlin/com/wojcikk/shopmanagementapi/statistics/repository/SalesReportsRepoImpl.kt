package com.wojcikk.shopmanagementapi.statistics.repository

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDataDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerSalesReportDTO
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.math.BigInteger
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TemporalType

@Repository
class SalesReportsRepoImpl : SalesReportsRepo {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun selectSalesReportData(timePeriod: TimePeriod): SalesReportDataDTO {

        val query = entityManager.createNativeQuery(
            """
            
            SELECT  coalesce(sum(sii.price * (1 + sii.tax_rate) * (1 - sii.discount) * sii.quantity), 0), 
                    count(DISTINCT si.id)
            
            FROM sales_invoices si
                JOIN sales_invoice_items sii on si.id = sii.sales_invoice_id
                
            WHERE
                si.correction_id IS NULL
              AND
                si.issue_date between :from AND :to
                
            """.trimIndent()
        )
        query.setParameter("from", timePeriod.from, TemporalType.DATE)
        query.setParameter("to", timePeriod.to, TemporalType.DATE)

        val resultList = query.resultList
        if (resultList.size == 0) {
            throw IllegalStateException("Database should always return exactly one result")
        }

        val result = resultList[0] as Array<*>
        return SalesReportDataDTO((result[1] as BigInteger).toLong(), result[0] as BigDecimal)
    }

    override fun selectReportDataGroupBySeller(timePeriod: TimePeriod): List<SellerSalesReportDTO> {
        val query = entityManager.createNativeQuery(
            """
            
            SELECT  u.id,
                    concat(u.name, ' ', u.surname),
                    count(DISTINCT si.id),
                    coalesce(sum(sii.price * (1 + sii.tax_rate) * (1 - sii.discount) * sii.quantity), 0)
                    
            FROM users u
                LEFT OUTER JOIN sales_invoices si on (
                    u.id = si.seller_id
                        AND
                    si.correction_id IS NULL
                        AND
                    si.issue_date between :from AND :to
                )
                
                LEFT JOIN sales_invoice_items sii on si.id = sii.sales_invoice_id

            GROUP BY u.id
            ;
            
        """.trimIndent()
        )

        query.setParameter("from", timePeriod.from, TemporalType.DATE)
        query.setParameter("to", timePeriod.to, TemporalType.DATE)

        val resultList = query.resultList
        if (resultList.size == 0) {
            throw IllegalStateException()
        }

        return resultList.map {
            val result = it as Array<*>

            SellerSalesReportDTO(
                (result[0] as BigInteger).toLong(),
                result[1] as String,
                (result[2] as BigInteger).toLong(),
                result[3] as BigDecimal
            )
        }

    }
}