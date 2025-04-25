package com.ms.report.api.service

import com.ms.report.api.config.properties.AwsProperties
import com.ms.report.api.repository.ReportRepository
import com.ms.report.api.repository.entity.enums.ReportStatus
import com.report.utils.service.S3Service
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ReportDownloadService(
    private val reportService: ReportService,
    private val s3Service: S3Service,
    private val properties: AwsProperties
) {

    private val bucket: String = properties.bucket

    fun downloadReport(reportId: UUID): ResponseEntity<ByteArray> {
        val reportEntity = reportService.getById(reportId)

        if (reportEntity.reportHistory.none { it.status == ReportStatus.COMPLETED }) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Report is not ready for download.")
        }

        if (reportEntity.s3Location.isNullOrEmpty()) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report S3 location not found")
        }

        try {
            val s3Object = s3Service.downloadFile(bucket, reportEntity.s3Location)
            val fileName = reportEntity.s3Location.substringAfterLast("/")

            val contentType = when {
                fileName.endsWith(".pdf", ignoreCase = true) -> MediaType.APPLICATION_PDF
                fileName.endsWith(".csv", ignoreCase = true) -> MediaType.parseMediaType("text/csv")
                fileName.endsWith(
                    ".xlsx",
                    ignoreCase = true
                ) -> MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

                else -> MediaType.APPLICATION_OCTET_STREAM
            }

            return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
                .body(s3Object)

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to download report: ${ex.message}")
        }
    }
}