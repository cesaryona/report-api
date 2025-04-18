package com.ms.report.api.service.mapper

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.repository.entity.ReportEntity
import com.ms.report.api.repository.entity.ReportHistoryEntity
import com.ms.report.api.repository.entity.enums.ReportStatus
import org.springframework.stereotype.Component

@Component
class ReportMapper {

    fun toEntity(reportRequest: ReportRequest): ReportEntity {
        return ReportEntity(
            userId = reportRequest.userId,
            type = reportRequest.reportType,
            category = reportRequest.reportCategory,
        )
    }

    fun toResponse(entity: ReportEntity): ReportResponse {
        return ReportResponse(
            requestId = entity.id,
            message = "Report in ${entity.type.name.lowercase()} format created",
            status = entity.status.name,
            createdAt = entity.createdAt
        )
    }

    fun toHistoryEntity(entity: ReportEntity): ReportHistoryEntity {
        return ReportHistoryEntity(
            report = entity,
            message = "Report created successfully."
        )
    }

}