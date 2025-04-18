package com.ms.report.api.controller

import com.ms.report.api.controller.request.ReportRequest
import com.ms.report.api.controller.response.ReportResponse
import com.ms.report.api.service.ReportService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("v1/api/reports")
class ReportController(
    private val reportService: ReportService
) {

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun requestReport(@Valid @RequestBody request: ReportRequest): ReportResponse {
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
}