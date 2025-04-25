package com.ms.report.api.controller

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportRequestResponse
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.service.ReportDownloadService
import com.ms.report.api.service.ReportService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("v1/api/reports")
class ReportController(
    private val reportService: ReportService,
    private val reportDownloadService: ReportDownloadService
) {

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    fun requestReport(@Valid @RequestBody request: ReportRequest): ReportRequestResponse {
        return reportService.createReportRequest(request)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getReportStatus(@PathVariable id: UUID): ReportResponse {
        return reportService.getReportRequest(id)
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserReports(@PathVariable userId: UUID): ReportResponse {
        return reportService.getUserReport(userId)
    }

    @GetMapping("/download/{reportId}")
    fun downloadReport(@PathVariable reportId: UUID): ResponseEntity<ByteArray> {
        return reportDownloadService.downloadReport(reportId)
    }

}