# HL7v2 Message Sender

[![Docker](https://github.com/subigre/hl7v2-message-sender/actions/workflows/docker.yml/badge.svg)](https://github.com/subigre/hl7v2-message-sender/actions/workflows/docker.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=subigre_hl7v2-message-sender&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=subigre_hl7v2-message-sender)
[![Known Vulnerabilities](https://snyk.io/test/github/subigre/hl7v2-message-sender/badge.svg)](https://snyk.io/test/github/subigre/hl7v2-message-sender/)

HL7v2 Message Sender is a very simple web application to send HL7v2 messages to a remote MLLP endpoint.

## Getting Started

1. Start the container

    ```shell
    docker run -p 8080:8080 --name hl7v2-message-sender subigre/hl7v2-message-sender
    ```
2. Navigate to http://localhost:8080
