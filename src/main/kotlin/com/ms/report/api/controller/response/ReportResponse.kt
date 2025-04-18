package com.ms.report.api.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.UUID

data class ReportResponse(

    val requestId: UUID?,
    val message: String,
    val status: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    val createdAt: LocalDateTime
)