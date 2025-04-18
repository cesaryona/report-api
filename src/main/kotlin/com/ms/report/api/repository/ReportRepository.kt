package com.ms.report.api.repository

import com.ms.report.api.repository.entity.ReportEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReportRepository : JpaRepository<ReportEntity, UUID> {

    fun findByUserId(userId: UUID): Optional<ReportEntity>

}