package com.ms.report.api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ms.report.api.config.properties.AwsProperties
import com.ms.report.api.dto.ReportRequestDto
import com.ms.report.api.repository.entity.enums.ReportCategory
import com.ms.report.api.repository.entity.enums.ReportType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.util.UUID

@Service
class SqsService(
    private val properties: AwsProperties,
    private val objectMapper: ObjectMapper,
    private val sqsClient: SqsClient
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun sendMessage(reportId: UUID, userId: UUID, reportType: ReportType, reportCategory: ReportCategory) {
        val reportRequestDto = ReportRequestDto(userId, reportType, reportCategory)

        val request = SendMessageRequest.builder()
            .queueUrl(properties.queue.reportRequest)
            .messageBody(objectMapper.writeValueAsString(reportRequestDto))
            .build();

        sqsClient.sendMessage(request);
        logger.info("Sent report ID: [${reportId}] request to SQS")
    }

}