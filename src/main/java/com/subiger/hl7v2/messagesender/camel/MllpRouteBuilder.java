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

package com.subiger.hl7v2.messagesender.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mllp.MllpConstants;
import org.springframework.stereotype.Component;

/**
 * @author Renaud Subiger
 * @since 1.0
 */
@Component
public class MllpRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("direct:send-message")
            .routeId("send-message")
            .toD("mllp://${header.destination}")
            .setBody(header(MllpConstants.MLLP_ACKNOWLEDGEMENT))
            .end();
        // @formatter:on
    }
}
