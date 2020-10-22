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
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class MessageReader {

    List<Message> messages = new ArrayList<>();

    public MessagesList getMessages() {
        try (FileReader fileReader = new FileReader(ClassLoader.getSystemResource("static/messages.json").getPath())) {
            Object parse = new JSONParser().parse(fileReader);
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
