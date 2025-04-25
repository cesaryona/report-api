package com.ms.report.api.dto

import com.report.utils.factory.dto.ReportStatusMessage
import java.util.*

data class ReportStatusRequestDto(

    val reportId: UUID,
    val reportstatus: ReportStatusMessage
)
