package com.ms.report.api.dto

import com.ms.report.api.repository.entity.enums.ReportCategory
import com.ms.report.api.repository.entity.enums.ReportType
import java.util.UUID

data class ReportRequestDto(

    val userId: UUID,
    val reportType: ReportType,
    val reportCategory: ReportCategory
)
