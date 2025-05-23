package com.ms.report.api.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

data class ReportResponse(

    val requestId: UUID?,
    val history: List<ReportHistoryResponse>,
    val s3Location: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    val createdAt: LocalDateTime
)