package com.Jaily.controller;

import com.Jaily.entity.FriendMessage;
import com.Jaily.entity.User;
import com.Jaily.service.FriendService;
import com.Jaily.service.UserService;
import com.Jaily.utility.Code;
import com.Jaily.utility.FriendInfo;
import com.Jaily.utility.MessageType;
import com.Jaily.utility.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @Value("${rt.save-file-path}")
    private String saveFilePath;

    @Value("${rt.request-file-path}")
    private String requestFilePath;

    @GetMapping("/{name1}/{name2}")
    private Result<Boolean> addFriend(@PathVariable("name1") String username, @PathVariable("name2") String friendName){
        Code code = friendService.addFriend(username, friendName);
        if(code == Code.FRIEND_ADD_AGAIN){
            return new Result<>(code.getCode(), false, "该用户已经是您的好友,请勿重复添加!");
        }
        return new Result<>(code.getCode(), true, "好友添加成功");
    }

    @DeleteMapping("/{name1}/{name2}")
    private Result<Boolean> deleteFriend(@PathVariable("name1") String username, @PathVariable("name2") String friendName){
        friendService.deleteFriend(username, friendName);
        return new Result<>(Code.FRIEND_DELETE_SUCCESS.getCode(), true);
    }

    @GetMapping("/startChat/{friend}")
    private Result<Boolean> enterChatRoom(@PathVariable("friend") String friendName,
                                          HttpServletRequest req) throws Exception {
        //使用Session存储与那个好友聊天的信息
        HttpSession session = req.getSession();
        //使用URL编码存储用户数据
        friendName = URLEncoder.encode(friendName, "UTF-8");
        session.setAttribute("friend", friendName);
        return new Result<>(Code.FRIEND_ADD_SESSION_FINISHED.getCode(), true);

    }

    @GetMapping("/chat/friend")
    private Result<?> getChatFriend(HttpServletRequest req){
        HttpSession session = req.getSession();
        String friendName = (String) session.getAttribute("friend");
        if(friendName == null){
            return new Result<>(Code.FRIEND_GET_SESSION_FAILED.getCode(), false,"没有获取到好友信息");
        }
        friendName = URLDecoder.decode(friendName);
        User friend = userService.getUserByName(friendName);
        FriendInfo friendInfo = new FriendInfo(friendName, friend.getAvatar());
        return new Result<>(Code.FRIEND_GET_SESSION_SUCCESS.getCode(), friendInfo);
    }

    @PostMapping("/chat")
    private Result<Boolean> chatWithFriend(@RequestBody FriendMessage friendMessage){
        if(!friendService.chat(friendMessage)){
            return new Result<Boolean>(Code.FRIEND_MESSAGE_ADD_FAILED.getCode(), false,"发送失败");
        }
        return new Result<Boolean>(Code.FRIEND_MESSAGE_ADD_SUCCESS.getCode(), true);
    }

    @PostMapping("/fileChat/{user}/{friend}")
    public Result<Boolean> fileChat(@RequestParam(value = "file") MultipartFile file,
                              @PathVariable("user") String username,
                              @PathVariable("friend") String friendName) {
        File folder = new File(saveFilePath);
        String filePath="";
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            file.transferTo(new File(folder, newName));
            filePath = requestFilePath + newName;
            FriendMessage friendMessage = new FriendMessage();
            friendMessage.setSender(username);
            friendMessage.setRecipient(friendName);
            friendMessage.setType(MessageType.FILE.getType());
            friendMessage.setContent(filePath);
            if(!friendService.chat(friendMessage)){
                return new Result<>(Code.FRIEND_MESSAGE_ADD_FAILED.getCode(), false,"发送失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(Code.FILE_UPLOAD_FAILED.getCode(), false,"文件上传失败");
        }
        return new Result<>(Code.FRIEND_MESSAGE_ADD_SUCCESS.getCode(), true);
    }

    @GetMapping("/chat/{user}/{friend}")
    private Result<List<FriendMessage>> getChattingMessage(@PathVariable("user") String user, @PathVariable("friend") String friend){
        List<FriendMessage> chattingMessage = friendService.getChattingMessage(user, friend);
        return new Result<List<FriendMessage>>(Code.FRIEND_MESSAGE_GET_FINISHED.getCode(), chattingMessage);
    }

}
