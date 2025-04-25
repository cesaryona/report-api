package com.ms.report.api.service

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportRequestResponse
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.repository.ReportHistoryRepository
import com.ms.report.api.repository.ReportRepository
import com.ms.report.api.repository.entity.ReportEntity
import com.ms.report.api.service.mapper.ReportMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ReportService(
    private val reportRepository: ReportRepository,
    private val reportHistoryRepository: ReportHistoryRepository,
    private val mapper: ReportMapper,
    private val reportProducer: ReportProducer

) {

    fun createReportRequest(request: ReportRequest): ReportRequestResponse {
        val entity = reportRepository.save(mapper.toEntity(request))

        reportProducer.sendRequest(mapper.toDto(entity))

        return ReportRequestResponse(entity.id)
    }

    fun getById(reportId: UUID) : ReportEntity {
        return reportRepository.findById(reportId)
            .orElseThrow { RuntimeException("Report not found for id: $reportId") }
    }

    fun getReportRequest(reportId: UUID): ReportResponse {
        val history = reportHistoryRepository.findByReportId(reportId)

        return reportRepository.findById(reportId).map { mapper.toResponse(it, history) }
            .orElseThrow { RuntimeException("Report not found for id: $reportId") }
    }

    fun getUserReport(userId: UUID): ReportResponse {
        val history = reportHistoryRepository.findByReportId(userId)

        return reportRepository.findByUserId(userId).map { mapper.toResponse(it, history) }
            .orElseThrow { RuntimeException("Report for userId ${userId} not found") }
    }

}