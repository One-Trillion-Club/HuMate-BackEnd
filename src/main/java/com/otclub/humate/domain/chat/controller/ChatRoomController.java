package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomDetailDTO;
import com.otclub.humate.domain.chat.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 채팅방 컨트롤러
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<Integer> chatRoomCreate(@RequestBody ChatRoomCreateRequestDTO requestDTO){
        Integer chatRoomId = chatRoomService.createChatRoom(requestDTO);

        return ResponseEntity.ok(chatRoomId);
    }

    @ResponseBody
    @GetMapping("/list/{memberId}") // {memberId} 임시
    public ResponseEntity<List<ChatRoomDetailDTO>> chatRoomList(@PathVariable("memberId") String memberId){
        List<ChatRoomDetailDTO> roomList = chatRoomService.findChatRoomList(memberId, 1);

        return ResponseEntity.ok(roomList);
    }

    @ResponseBody
    @GetMapping("/list/pending/{memberId}") // {memberId} 임시
    public ResponseEntity<List<ChatRoomDetailDTO>> chatRoomPendingList(@PathVariable("memberId") String memberId){
        List<ChatRoomDetailDTO> roomList = chatRoomService.findChatRoomList(memberId, 0);

        return ResponseEntity.ok(roomList);
    }
}
