package com.ms.report.api.service

import com.ms.report.api.config.properties.AwsProperties
import com.ms.report.api.dto.ReportRequestDto
import com.ms.report.api.dto.ReportStatusRequestDto
import com.ms.report.api.repository.entity.enums.ReportStatus
import com.report.utils.factory.getReportStatusMessage
import com.report.utils.service.SqsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReportProducer(
    private val properties: AwsProperties,
    private val sqsService: SqsService
) {

    private val reportQueue = properties.queue.reportRequest
    private val reportStatusQueue = properties.queue.reportStatus

    fun sendRequest(request: ReportRequestDto) {
        sqsService.sendMessage(reportQueue, request)
        sqsService.sendMessage(reportStatusQueue, ReportStatusRequestDto(request.reportId, getReportStatusMessage(ReportStatus.PENDING)))
    }

}