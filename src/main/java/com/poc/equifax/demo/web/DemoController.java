package com.poc.equifax.demo.web;

import com.poc.equifax.demo.service.MessageReader;
import com.poc.equifax.demo.web.model.MessagesList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("membercenter/api/v1/messages")
@RestController()
public class DemoController {

    private final MessageReader messageReader;

    @GetMapping()
    public MessagesList getMessage() {
        return messageReader.getMessages();
    }

    @PutMapping(value = "/{id}")
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

    @GetMapping(value = "unread")
    public String messageCount() {
        return String.format("%s messages left unread", messageReader.getUnreadMessagesCount());
    }

}
