package com.Jaily.controller;

import com.Jaily.entity.FriendMessage;
import com.Jaily.entity.Group;
import com.Jaily.entity.GroupMessage;
import com.Jaily.entity.User;
import com.Jaily.service.GroupService;
import com.Jaily.utility.Code;
import com.Jaily.utility.FriendInfo;
import com.Jaily.utility.MessageType;
import com.Jaily.utility.Result;
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

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Value("${rt.save-file-path}")
    private String saveFilePath;

    @Value("${rt.request-file-path}")
    private String requestFilePath;

    @PostMapping
    private Result<Boolean> addGroup(@RequestBody Group group){
        Code code = groupService.addGroup(group);
        if(code == Code.GROUP_ADD_AGAIN){
            return new Result<Boolean>(code.getCode(), false, "该群号已存在,请重新创建");
        }
        if(code == Code.GROUP_ADD_FAILED){
            return new Result<Boolean>(code.getCode(), false, "创建失败");
        }
        return new Result<Boolean>(code.getCode(), true, "创建成功");
    }

    @GetMapping("/{groupid}")
    private Result<?> searchGroup(@PathVariable("groupid") String groupId){
        Group group = groupService.getGroupById(groupId);
        if(group == null){
            return new Result<Boolean>(Code.SEARCH_GROUP_FAILED.getCode(), false);
        }
        return new Result<Group>(Code.SEARCH_GROUP_SUCCESS.getCode(), group);
    }

    @GetMapping("/startChat/{id}")
    private Result<Boolean> enterChatRoom(@PathVariable("id") String groupId,
                                          HttpServletRequest req) throws Exception {
        //使用Session存储与那个好友聊天的信息
        HttpSession session = req.getSession();
        //使用URL编码存储用户数据
        groupId = URLEncoder.encode(groupId, "UTF-8");
        session.setAttribute("group", groupId);
        return new Result<Boolean>(Code.GROUP_ADD_SESSION_FINISHED.getCode(), true);

    }

    @GetMapping("/chat/group")
    private Result<?> getChatGroup(HttpServletRequest req){
        HttpSession session = req.getSession();
        String groupId = (String) session.getAttribute("group");
        if(groupId == null){
            return new Result<Boolean>(Code.GROUP_GET_SESSION_FAILED.getCode(), false,"没有获取到群组信息");
        }
        groupId = URLDecoder.decode(groupId);
        return new Result<String>(Code.GROUP_GET_SESSION_SUCCESS.getCode(), groupId);
    }

    @PostMapping("/fileChat/{user}/{id}")
    public Result<Boolean> fileChat(@RequestParam(value = "file") MultipartFile file,
                                    @PathVariable("user") String username,
                                    @PathVariable("id") String groupId) {
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
            GroupMessage groupMessage = new GroupMessage();
            groupMessage.setSender(username);
            groupMessage.setGroupId(groupId);
            groupMessage.setType(MessageType.FILE.getType());
            groupMessage.setContent(filePath);
            if(!groupService.chat(groupMessage)){
                return new Result<>(Code.GROUP_MESSAGE_ADD_FAILED.getCode(), false,"发送失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(Code.FILE_UPLOAD_FAILED.getCode(), false,"文件上传失败");
        }
        return new Result<>(Code.GROUP_MESSAGE_ADD_SUCCESS.getCode(), true);
    }

    @PostMapping("/chat")
    private Result<Boolean> chatInGroup(@RequestBody GroupMessage groupMessage){
        if(!groupService.chat(groupMessage)){
            return new Result<Boolean>(Code.GROUP_MESSAGE_ADD_FAILED.getCode(), false,"发送失败");
        }
        return new Result<Boolean>(Code.GROUP_MESSAGE_ADD_SUCCESS.getCode(), true);
    }

    @GetMapping("/chat/{id}")
    private Result<List<GroupMessage>> getChattingMessage(@PathVariable("id") String groupId){
        List<GroupMessage> chattingMessage = groupService.getChattingMessage(groupId);
        return new Result<List<GroupMessage>>(Code.GROUP_MESSAGE_GET_FINISHED.getCode(), chattingMessage);
    }
}
