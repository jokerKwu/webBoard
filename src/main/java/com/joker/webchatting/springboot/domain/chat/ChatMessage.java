package com.joker.webchatting.springboot.domain.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private MessageType type; // 메시지 타입
    private String content; // 내용
    private String sender; // 메시지 보낸사람
}