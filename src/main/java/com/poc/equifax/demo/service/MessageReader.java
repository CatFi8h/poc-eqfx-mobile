package com.poc.equifax.demo.service;

import com.poc.equifax.demo.web.exception.DataSourceNotFoundException;
import com.poc.equifax.demo.web.model.Message;
import com.poc.equifax.demo.web.model.MessagesList;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class MessageReader {

    private List<Message> messages = new ArrayList<>();

    public MessagesList getMessages() {
        try (InputStream resource = new ClassPathResource(
                "static/messages.json").getInputStream()) {
            Object parse = new JSONParser().parse(new InputStreamReader(resource, "UTF-8"));
            JSONArray messagesListArray = (JSONArray) parse;
            messages = mapToMessageList(messagesListArray);
            return MessagesList.builder()
                    .list(messages)
                    .totalMessagesCount(messages.size())
                    .build();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new DataSourceNotFoundException("Message JSON is corrupted");
        }
    }

    public long getUnreadMessagesCount() {
        return messages.stream().filter(message -> !message.isMessageRead()).count();
    }

    public void markMessageAsRead(Long id) {
        messages.stream().filter(message -> message.getId().equals(id)).forEach(Message::setMessageRead);
    }

    private List<Message> mapToMessageList(JSONArray messagesArray) {
        final List<Message> messageList = new ArrayList<>();
        for (Object o : messagesArray) {
            final JSONObject jsonObject = (JSONObject) o;
            final Number id = (Number) jsonObject.get("id");
            final String message = (String) jsonObject.get("message");
            final Boolean isMessageRead = (Boolean) jsonObject.get("statusRead");
            messageList.add(
                    Message.builder()
                            .message(message)
                            .id(id.longValue())
                            .isMessageRead(isMessageRead)
                            .build());
        }
        return messageList;
    }
}
