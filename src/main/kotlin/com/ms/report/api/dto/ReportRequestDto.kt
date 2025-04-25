package com.ms.report.api.dto

import com.ms.report.api.repository.entity.enums.ReportCategory
import com.ms.report.api.repository.entity.enums.ReportType
import java.util.*

data class ReportRequestDto(

    val reportId: UUID,
    val reportType: ReportType,
    val reportCategory: ReportCategory
)
