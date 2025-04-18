package com.ms.report.api.controller.request

import com.ms.report.api.repository.entity.enums.ReportCategory
import com.ms.report.api.repository.entity.enums.ReportType
import jakarta.validation.constraints.NotNull
import java.util.*

data class ReportRequest(

    @field:NotNull(message = "UserId is required")
    val userId: UUID,

    @field:NotNull(message = "Report type is required")
    val reportType: ReportType,

    @field:NotNull(message = "Report category is required")
    val reportCategory: ReportCategory

)