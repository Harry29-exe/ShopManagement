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
class SalesStatisticsRepositoryImpl : SalesStatisticsRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun selectSalesReportData(timePeriod: TimePeriod): SalesReportDataDTO {

        val query = entityManager.createNativeQuery("""
            
            SELECT  sum(sii.price * (1 + sii.tax_rate) * (1 - sii.discount) * sii.quantity) as salesTotal, 
                    count(DISTINCT si.id) as transactions
            
            FROM sales_invoices si
                JOIN sales_invoice_items sii on si.id = sii.sales_invoice_id
                
            WHERE
                si.correction_id IS NULL
              AND
                si.issue_date between :from AND :to
                
            """.trimIndent())
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
        val query = entityManager.createNativeQuery("""
            
            SELECT  u.id,
                    concat(u.name, ' ', u.surname),
                    sum(sii.price * (1 + sii.tax_rate) * (1 - sii.discount) * sii.quantity) as salesTotal, 
                    count(DISTINCT si.id) as transactions
            
            FROM sales_invoices si
                JOIN sales_invoice_items sii on si.id = sii.sales_invoice_id
                JOIN users u on si.seller_id = u.id
                
            WHERE
                si.correction_id IS NULL
              AND
                si.issue_date between :from AND :to
                
            GROUP BY u.id
            
        """.trimIndent())

        query.setParameter("from", timePeriod.from, TemporalType.DATE)
        query.setParameter("to", timePeriod.to, TemporalType.DATE)

        val resultList = query.resultList
        if (resultList.size == 0) {
            throw IllegalStateException("Database should always return exactly one result")
        }

        return resultList.map {
            val result = it as Array<*>

            SellerSalesReportDTO(
                result[0] as Long,
                result[1] as String,
                (result[2] as BigInteger).toLong(),
                result[3] as BigDecimal
            )
        }

    }
}