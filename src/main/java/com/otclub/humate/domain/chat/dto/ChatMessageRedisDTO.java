package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * redis common DTO
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageRedisDTO {
    private String chatRoomId;
    private String senderId;
    private String content;
    private MessageType messageType;

    public static ChatMessageRedisDTO from(ChatMessageRequestDTO requestDTO){
        return ChatMessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .senderId(requestDTO.getSenderId())
                .content(requestDTO.getContent())
                .messageType(requestDTO.getMessageType())
                .build();
    }

    public static ChatMessageRedisDTO ofMateActive(MateUpdateRequestDTO requestDTO, String nickname){
        MessageType messageType = (requestDTO.getIsClicked()==1 ? MessageType.MATE_ACTIVE : MessageType.MATE_INACTIVE);

        return ChatMessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .senderId(requestDTO.getMemberId())
                .content(nickname + messageType.getMsg())
                .messageType(messageType.MATE_ACTIVE)
                .build();
    }
}
