#!/bin/bash
awslocal sqs create-queue --queue-name report-request-queue
awslocal sqs create-queue --queue-name report-status-queue