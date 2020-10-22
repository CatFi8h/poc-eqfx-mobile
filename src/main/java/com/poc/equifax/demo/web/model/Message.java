package com.poc.equifax.demo.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private Long id;
    private String message;
    private boolean isMessageRead;

    public void setMessageRead() {
        isMessageRead = true;
    }
}
