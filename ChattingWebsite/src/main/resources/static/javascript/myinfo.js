//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};

//加载用户状态信息
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
            document.getElementById("middle-username").innerHTML = resInfo.data.username;
            document.getElementById("avatar-login").src = '../img/' + resInfo.data.avatar + '.png';
            document.getElementById("middle-avatar").src = '../img/' + resInfo.data.avatar + '.png';
            document.getElementById("changed-lastname").value = resInfo.data.lastname;
            document.getElementById("changed-firstname").value = resInfo.data.firstname;
            document.getElementById("username").value = resInfo.data.username + "  用户名不可更改";
            document.getElementById("password").value = resInfo.data.password;
            document.getElementById("birthday").value = resInfo.data.birthday;
            var selectSex = document.getElementById("sex");
            for(let i = 0; i < selectSex.length; i++){
                if(selectSex[i].value == resInfo.data.sex){
                    selectSex[i].selected = true;
                }
            }
            var selectAvatar = document.getElementById("avatar");
            for(let i = 0; i < selectAvatar.length; i++){
                if(selectAvatar[i].value == resInfo.data.avatar){
                    selectAvatar[i].selected = true;
                }
            }
            username = resInfo.data.username;
        }).catch(function (error) {
        console.log(error);
    });
}

//在页面加载完成后立即初始化页面的用户状态信息
window.onload = function (){
    loadUserInfo();
}

//获取修改个人信息按钮
var changeButton = document.getElementById("change-button");
//修改按钮点击事件
changeButton.onclick = function (){
    var lastname = document.getElementById("changed-lastname").value.trim();
    var firstname = document.getElementById("changed-firstname").value.trim();
    var username = document.getElementById("username").value.trim();
    username = username.substring(0, username.length - 9);
    var password = document.getElementById("password").value.trim();
    var birthday = document.getElementById("birthday").value;
    var sex = document.getElementById("sex").value === '1';
    var avatar = document.getElementById("avatar").value;
    if(lastname === '' || lastname === null || firstname === '' || firstname === null || password === '' || password === null){
        alert("修改内容不能留空");
        return;
    }
    if(password.length < 4){
        alert("密码长度不符合要求,密码必须为4-16位!");
        return;
    }
    axios({
        method: 'put',
        url: '/users/info',
        data: {
            lastname : lastname,
            firstname : firstname,
            username : username,
            password : password,
            birthday : birthday,
            sex : sex,
            avatar : avatar
        }
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            alert(resInfo.msg);

        }).catch(function (error) {
        console.log(error);
    });
}

//获取退出登录按钮
var signOutButton = document.getElementById("signout-button");
//登出按钮点击事件
signOutButton.onclick = function (){
    axios({
        method: 'get',
        url: '/users/signOut',
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            window.location.href = "../html/login.html"
        }).catch(function (error) {
        console.log(error);
    });
}