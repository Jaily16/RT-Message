//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};
//用于存储该页面上的用户名信息
var username;
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
        }).catch(function (error) {
        console.log(error);
    });
}

function loadFriendList(){
    axios({
        method: 'get',
        url: '/users/friendList'
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            var friendList = resInfo.data;
            var list = document.getElementById("friend-list");
            list.innerHTML = '';
            for(let i = 0 ; i < friendList.length; i++){
                list.innerHTML +=
                    '<div class="card friend-card">\n' +
                    '<input class="button chat-button" type="button" id="' + friendList[i].username+ '+" onclick="chatWithFriend(this.id);" value="聊天">\n' +
                    '<input class="button delete-button" type="button" id="' + friendList[i].username+ '-" onclick="deleteFriend(this.id);" value="删除">\n' +
                    '<img class="avatar" src="../img/' + friendList[i].avatar + '.png">\n' +
                    '<div class="username">' + friendList[i].username + '</div>\n' +
                    '<div class="whole-name">\n' +
                    '<div class="name-part">' + friendList[i].lastname + '</div>\n' +
                    '<div class="name-part">' + friendList[i].firstname + '</div>\n' +
                    '</div>\n' +
                    '</div>';
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//在页面加载完成后立即初始化页面右上角的用户状态信息以及好友列表信息
window.onload = function (){
    loadUserInfo();
    loadFriendList();
}

//间隔检测更新好友列表,间隔时间5秒
setInterval("loadFriendList()","5000");

//获取用户名搜索框
var searchInput = document.getElementById("search-name");

//键盘按键被按下事件
searchInput.onkeydown = function (){
    var searchName = searchInput.value.trim();
    if(searchName === "" || searchName === null){
        return;
    }
    axios({
        method: 'get',
        url: '/users/search/' + searchName
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.data === false){
                document.getElementById("search-result").innerHTML = '';
                return;
            }
            document.getElementById("search-result").innerHTML =
                '<div class="card" id="search-card">\n' +
                '<input class="button add-button" type="button" id="add-friend-button" onclick="addFriend();" value="添加">\n' +
                '<img class="avatar" src="../img/' + resInfo.data.avatar +'.png">\n' +
                '<div class="username" id="add-friend">' + resInfo.data.username + '</div>\n' +
                '<div class="whole-name">\n' +
                '<div class="name-part">' + resInfo.data.lastname + '</div>\n' +
                '<div class="name-part">' + resInfo.data.firstname + '</div>\n' +
                '</div>\n' +
                '</div>';
        }).catch(function (error) {
        console.log(error);
    });
}

//按钮点击添加好友事件
function addFriend(){
    var friendName = document.getElementById("add-friend").innerHTML;
    if(friendName === "" || friendName === null){
        return;
    }
    if(friendName === this.username){
        alert("您不能添加自己为好友!");
        return;
    }
    axios({
        method: 'get',
        url: '/friends/' + this.username + '/' + friendName
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            alert(this.resInfo.msg);
            if(this.resInfo.data === true){
                loadFriendList();
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//按钮点击删除好友事件
function deleteFriend(buttonName){
    var friendName = buttonName.substring(0, buttonName.length - 1);
    axios({
        method: 'delete',
        url: '/friends/' + this.username + '/' + friendName
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            if(this.resInfo.data === true){
                loadFriendList();
            }
        }).catch(function (error) {
        console.log(error);
    });

}

//按钮点击与好友聊天事件
function chatWithFriend(buttonName){
    var friendName = buttonName.substring(0, buttonName.length - 1);
    axios({
        method: 'get',
        url: '/friends/startChat/' + friendName
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            if(this.resInfo.data === true){
                window.location.href = "../html/friendChat.html"
            }
        }).catch(function (error) {
        console.log(error);
    });
}