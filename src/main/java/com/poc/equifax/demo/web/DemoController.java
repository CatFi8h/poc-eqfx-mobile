package com.poc.equifax.demo.web;

import com.poc.equifax.demo.service.MessageReader;
import com.poc.equifax.demo.web.model.MessagesList;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("membercenter/api/v1/messages")
@RestController()
public class DemoController {

    private final MessageReader messageReader;
    private static final String COUNT_FILTER_UNREAD = "unread";

    @GetMapping()
    public MessagesList getMessage() {
        return messageReader.getMessages();
    }

    @PutMapping(value = "/{id}/mark-read")
    public String markMessageAsRead(@PathVariable("id") Long messageId) {
        if (messageId == null) {
            return "error";
        }
        messageReader.markMessageAsRead(messageId);
        return String.format("Message with ID: %s marked as READ", messageId);
    }

    @GetMapping(value = "/{id}/pdf")
    public String messagePdf(@PathVariable("id") Long messageId) {
        if (messageId == null) {
            return "error";
        }
        return String.format("Message with ID: %s exported to PDF", messageId);
    }

    @GetMapping(value = "/count")
    public String messageCount(@Parameter(name =  "filter", example = "unread")
                                   @RequestParam(name = "filter") String filter) {
        String result = "Unsupported filter";
        if (COUNT_FILTER_UNREAD.equals(filter)) {
            result = String.format("%s messages left unread", messageReader.getUnreadMessagesCount());
        } else if (filter == null) {
            result = String.format("Total messages is %s", messageReader.getMessagesCount());
        }
        return result;
    }

}
