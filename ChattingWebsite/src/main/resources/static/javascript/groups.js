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

function loadGroupList(){
    axios({
        method: 'get',
        url: '/users/groupList'
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            var groupList = resInfo.data;
            var list = document.getElementById("group-list");
            list.innerHTML = '';
            for(let i = 0 ; i < groupList.length; i++){
                list.innerHTML +=
                    '<div class="card group-card">\n' +
                    '<input class="button chat-button" type="button" id="' + groupList[i].groupId + '+" onclick="chatInGroup(this.id);" value="聊天">\n' +
                    '<input class="button delete-button" type="button" id="' + groupList[i].groupId + '-" onclick="exitGroup(this.id);" value="退出">\n' +
                    '<img class="avatar" src="../img/' + groupList[i].avatar + '.png">\n' +
                    '<div class="groupid">' + groupList[i].groupId + '</div>\n' +
                    '<div class="groupname">' + groupList[i].groupName + '</div>\n' +
                    '</div>';
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//在页面加载完成后立即初始化页面右上角的用户状态信息以及群聊信息
window.onload = function (){
    loadUserInfo();
    loadGroupList();
}

//获取群名搜索框
var searchInput = document.getElementById("search-id");

//键盘按键被按下事件
searchInput.onkeydown = function (){
    var searchId = searchInput.value.trim();
    if(searchId === "" || searchId === null){
        return;
    }
    axios({
        method: 'get',
        url: '/groups/' + searchId
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
                '<div class="card">\n' +
                '<input class="button add-button" type="button" id="join-group-button" onclick="joinInGroup();" value="加入">\n' +
                '<img class="avatar" src="../img/' + resInfo.data.avatar +'.png">\n' +
                '<div class="groupid" id="groupid">' + resInfo.data.groupId + '</div>\n' +
                '<div class="groupname">' + resInfo.data.groupName + '</div>\n' +
                '</div>'
        }).catch(function (error) {
        console.log(error);
    });
}

//加入群组请求
function join(groupId){
    axios({
        method: 'get',
        url: '/groupMember/' + groupId + '/' + this.username
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            alert(this.resInfo.msg);
            if(this.resInfo.data === true){
                loadGroupList();
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//按钮点击加入群组事件
function joinInGroup(){
    var groupId = document.getElementById("groupid").innerHTML;
    if(groupId === "" || groupId === null){
        return;
    }
    this.join(groupId);
}

//<input onKeyUp="this.value=this.value.replace(/[^\w]/g,'')" onPaste="this.value=this.value.replace(/[^\w]/g,'')">
//获取输入群号的输入框
var groupIdInput = document.getElementById("groupid");
//设置只能输入数字、字母和下划线的正则表达式
groupIdInput.onkeyup = function (){
    this.value=this.value.replace(/[^\w]/g,'');
}
groupIdInput.onpaste = function (){
    this.value=this.value.replace(/[^\w]/g,'');
}

//获取提交创建群聊表单按钮
var createGroupButton = document.getElementById("add-group-button");
//按钮点击事件
createGroupButton.onclick = function (){
    var groupId = document.getElementById("groupid").value.trim();
    if(groupId.length !== 4){
        alert("群号必须为4位数");
    }
    var groupName = document.getElementById("groupname").value.trim();
    var avatar = document.getElementById("group-avatar").value;
    if(groupName === '' || groupName === null){
        alert("创建群聊内容不能留空");
        return;
    }
    axios({
        method: 'post',
        url: '/groups',
        data: {
            groupId : groupId,
            groupName : groupName,
            avatar : avatar
        }
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            alert(resInfo.msg);
            if(resInfo.data === true){
                join(groupId);
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//按钮点击退出群聊事件
function exitGroup(buttonName){
    var groupId = buttonName.substring(0, buttonName.length - 1);
    axios({
        method: 'delete',
        url: '/groupMember/' + groupId + '/' + this.username
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            if(this.resInfo.data === true){
                loadGroupList();
            }
        }).catch(function (error) {
        console.log(error);
    });

}

//按钮点击进入群组聊天事件
function chatInGroup(buttonName){

}