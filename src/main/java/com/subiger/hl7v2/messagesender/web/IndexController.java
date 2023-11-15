package com.subiger.hl7v2.messagesender.web;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.GenericMessage;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import com.subiger.hl7v2.messagesender.web.model.MessageRequest;
import jakarta.validation.Valid;
import org.apache.camel.ProducerTemplate;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Renaud Subiger
 * @since 1.0
 */
@Controller
@RequestMapping(path = "/")
public class IndexController {

    private final HapiContext hapiContext = new DefaultHapiContext();

    private final BuildProperties properties;

    private final ProducerTemplate producerTemplate;

    public IndexController(BuildProperties properties, ProducerTemplate producerTemplate) {
        this.properties = properties;
        this.producerTemplate = producerTemplate;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute(Constants.VERSION_ATTRIBUTE_NAME, properties.getVersion());
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute(Constants.REQUEST_ATTRIBUTE_NAME, new MessageRequest());
        return Constants.INDEX_VIEW_NAME;
    }

    @PostMapping
    public String post(@Valid @ModelAttribute(name = Constants.REQUEST_ATTRIBUTE_NAME) MessageRequest request,
                       BindingResult result, Model model) {
        PipeParser parser = hapiContext.getPipeParser();

        if (StringUtils.hasText(request.getMessage())) {
            try {
                parser.parse(formatMessage(request.getMessage(), true));
            } catch (HL7Exception e) {
                result.rejectValue("message", "invalid", "Message is invalid: " + e.getMessage());
            }
        }

        if (!result.hasErrors()) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("destination", request.getDestination());

            GenericMessage response =
                    producerTemplate.requestBodyAndHeaders("direct:send-message", formatMessage(request.getMessage(), true), headers, GenericMessage.class);

            String acknowledgmentCode;
            try {
                var terser = new Terser(response);
                acknowledgmentCode = terser.get("/.MSA-1");
            } catch (HL7Exception e) {
                throw new IllegalStateException(e);
            }

            model.addAttribute(Constants.REQUEST_ATTRIBUTE_NAME, request);
            model.addAttribute(Constants.RESPONSE_ATTRIBUTE_NAME, formatMessage(response.toString(), false));
            model.addAttribute(Constants.ACKNOWLEDGMENT_CODE_ATTRIBUTE_NAME, acknowledgmentCode);
        }

        return Constants.INDEX_VIEW_NAME;
    }

    private String formatMessage(String message, boolean request) {
        if (message == null) {
            return null;
        }
        return request ? message.replace("\n", "\r") : message.replace("\r", "\n");
    }
}