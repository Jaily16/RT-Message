//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};
//用于存储该页面上的用户名信息
var username;
//用于存储该页面上的用户头像信息
var userAvatar;
//用于存储正在聊天的好友名称
var friendName;
//用于存储正在聊天的好友头像
var friendAvatar;
//查询用户状态信息
function loadUserInfo(){
    axios({
        method: 'get',
        url: '/users/info'
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            document.getElementById("lastname").innerHTML = resInfo.data.lastname;
            document.getElementById("firstname").innerHTML = resInfo.data.firstname;
            document.getElementById("avatar-login").src = '../img/' + resInfo.data.avatar + '.png';
            username = resInfo.data.username;
            userAvatar = resInfo.data.avatar;
        }).catch(function (error) {
        console.log(error);
    });
}

//加载聊天信息
function loadMessage(){
    axios({
        method: 'get',
        url: '/friends/chat/' + username + '/' + friendName
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            var messageList = resInfo.data;
            var list = document.getElementById("message-list");
            list.innerHTML = '';
            var messageClass;
            var avatar;
            var contentHtml;
            for(let i = 0 ; i < messageList.length; i++){
                if(messageList[i].sender === username){
                    messageClass = 'my-message';
                    avatar = userAvatar;
                }else {
                    messageClass = 'message';
                    avatar = friendAvatar;
                }
                if(messageList[i].type === 0){
                    //文本信息
                    contentHtml = '<div class="' + messageClass + '">' + messageList[i].content +'</div>\n';
                }
                else{
                    //文件信息
                    contentHtml = '<a class="' + messageClass +'" href="' + messageList[i].content + '" download="">点击下载文件</a>'
                }
                list.innerHTML +=
                    '<div>\n' +
                    '<img class="avatar-chat" src="../img/' + avatar + '.png">\n' +
                    contentHtml +
                    '</div>';
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//查询好友信息(好友用户名)
function loadFriendInfo(){
    axios({
        method: 'get',
        url: '/friends/chat/friend'
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.data === false){
                alert("未选择聊天好友!");
                window.location.href = "../html/friends.html";
            }
            friendName = resInfo.data.friendName;
            friendAvatar = resInfo.data.avatar;
            document.getElementById("friend-name").innerHTML = friendName;
            loadMessage();
        }).catch(function (error) {
        console.log(error);
    });
}


//在页面加载完成后立即初始化页面右上角的用户状态信息
window.onload = function (){
    loadUserInfo();
    loadFriendInfo();
}

//间隔检测更新实时信息,间隔时间0.3秒
setInterval("loadMessage()","300");

//获取发送信息按钮
var sendButton = document.getElementById("send-button");

//按钮点击发送文本信息事件
sendButton.onclick = function (){
    var content = document.getElementById("send-message").value;
    if(content === '' || content === null){
        return;
    }
    axios({
        method: 'post',
        url: '/friends/chat',
        data: {
            sender : username,
            recipient : friendName,
            type : 0,
            content : content
        }
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.data === false){
                alert(resInfo.msg);
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//获取上传文件按钮
var fileButton = document.getElementById("file-button");

//按钮点击上传文件事件
fileButton.onclick = function (){
    let file = document.getElementById("send-file").files[0];
    if(file == null){
        alert("请先选择要上传的文件");
        return;
    }
    const formData = new FormData();
    formData.append("file",file);
    axios({
        method: 'post',
        url: '/friends/fileChat/' + username + '/' + friendName,
        headers: {'Content-type' : 'multipart/form-data'},
        data: formData
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.data === false){
                alert(resInfo.msg);
            }
        }).catch(function (error) {
        console.log(error);
    });
}