/*
 * Copyright 2023 Renaud Subiger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.subiger.hl7v2.messagesender.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Renaud Subiger
 * @since 1.0
 */
public class MessageRequest {

    @Pattern(regexp = "[^:]+:\\d{2,5}", message = "Destination is not valid")
    private String destination;

    @NotBlank(message = "Message is required")
    private String message;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "destination='" + destination + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
