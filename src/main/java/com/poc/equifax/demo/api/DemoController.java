package com.poc.equifax.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("membercenter/api/v1/messages")
@RestController()
public class DemoController {

    Integer count = 100;

    @GetMapping()
    public String getMessage() {
        return "message";
    }

    @PutMapping(value = "/{id}")
    public String markMessageAsRead(@PathVariable("id") Integer messageId) {
        if (messageId == null) {
            return "error";
        }
        return "message marked as read " + messageId;
    }

    @GetMapping(value = "/{id}/pdf")
    public String messagePdf(@PathVariable("id") String messageId) {
        if (messageId == null) {
            return "error";
        }
        return "message marked as read " + messageId;
    }

    @GetMapping(value = "unread")
    public String messageCount() {
        return String.format("%s messages left unread", count--);
    }

}
