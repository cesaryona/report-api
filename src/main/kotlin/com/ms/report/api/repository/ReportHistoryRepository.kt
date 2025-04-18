package com.ms.report.api.repository

import com.ms.report.api.repository.entity.ReportHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReportHistoryRepository : JpaRepository<ReportHistoryEntity, UUID> {


}