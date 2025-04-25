package com.ms.report.api.service.mapper

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportHistoryResponse
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.dto.ReportRequestDto
import com.ms.report.api.repository.entity.ReportEntity
import com.ms.report.api.repository.entity.ReportHistoryEntity
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

    fun toResponse(entity: ReportEntity, history: List<ReportHistoryEntity>): ReportResponse {
        return ReportResponse(
            requestId = entity.id,
            history = history.map { historyEntity ->
                ReportHistoryResponse(
                    message = historyEntity.message,
                    status = historyEntity.status,
                    createdAt = historyEntity.createdAt
                )
            },
            s3Location = entity.s3Location,
            createdAt = entity.createdAt
        )
    }

    fun toDto(entity: ReportEntity): ReportRequestDto {
        return ReportRequestDto(
            reportId = entity.id,
            entity.type,
            reportCategory = entity.category
        )
    }
}