package com.ms.report.api.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.ms.report.api.repository.entity.enums.ReportStatus
import java.time.LocalDateTime

data class ReportHistoryResponse(

    val message: String,
    val status: ReportStatus,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    val createdAt: LocalDateTime

)
