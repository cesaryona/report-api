package com.ms.report.api.service

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.repository.ReportHistoryRepository
import com.ms.report.api.repository.ReportRepository
import com.ms.report.api.service.mapper.ReportMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReportService(
    private val reportRepository: ReportRepository,
    private val reportHistoryRepository: ReportHistoryRepository,
    private val mapper: ReportMapper,
    private val sqsService: SqsService
) {

    fun createReportRequest(request: ReportRequest): ReportResponse {
        val entity = reportRepository.save(mapper.toEntity(request))

        reportHistoryRepository.save(mapper.toHistoryEntity(entity))
        sqsService.sendMessage(entity.id, request.userId, request.reportType, request.reportCategory)

        return mapper.toResponse(entity)
    }

    fun getReportRequest(id: UUID): ReportResponse {
        return reportRepository.findById(id).map { mapper.toResponse(it) }
            .orElseThrow { RuntimeException("Report not found for id: $id") }
    }


    fun getUserReport(userId: UUID): ReportResponse {
        return reportRepository.findByUserId(userId).map { mapper.toResponse(it) }
            .orElseThrow { RuntimeException("Report for userId ${userId} not found") }
    }

}