package princessandknights.princesshoppang.webSocket.controller;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import princessandknights.princesshoppang.webSocket.model.Response;
import princessandknights.princesshoppang.webSocket.model.ResponseMessage;
import princessandknights.princesshoppang.webSocket.model.StatusCode;
import princessandknights.princesshoppang.webSocket.model.ChatRoom;
import princessandknights.princesshoppang.webSocket.repository.ChatRoomRepository;

@Slf4j
@RequiredArgsConstructor
@Controller
@CrossOrigin(origins="*")
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;


    // 전체 그룹 채팅 목록 조회
    @GetMapping("/room")
    @ResponseBody
    public ResponseEntity<Response<Object>> room() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
        chatRooms.stream().forEach(room -> room.setUserCount(chatRoomRepository.getUserCount(room.getRoomId())));

        return new ResponseEntity<>(new Response<>(StatusCode.OK, ResponseMessage.GROUP_LIST_SUCCESS, chatRooms),
                HttpStatus.OK);
    }

    // 그룹채팅 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(String name) {
        return chatRoomRepository.createChatRoom(name);
    }
//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoom createRoom(@RequestBody Map<String, String> requestData) {
//        String name = requestData.get("name");
//        return chatRoomRepository.createChatRoom(name);
//    }

    // 해당 그룹채팅방 ID로 접근하여 채팅방 선택
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        log.info("roomId: " + roomId);
        return chatRoomRepository.findRoomById(roomId);
    }


    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

//    @GetMapping("/user")
//    @ResponseBody
//    public String getUserInfo() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        return name;
//    }

}
