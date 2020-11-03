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

    @GetMapping()
    public MessagesList getMessage() {
        return messageReader.getMessages();
    }

    @PatchMapping(value = "/{id}")
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

    @GetMapping(value = "/unread-count")
    public String messageCount() {
        return String.format("%s messages left unread", messageReader.getUnreadMessagesCount());
    }

}
