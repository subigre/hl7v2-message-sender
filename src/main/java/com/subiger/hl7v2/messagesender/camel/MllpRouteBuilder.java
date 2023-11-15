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
