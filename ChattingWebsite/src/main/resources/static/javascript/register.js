//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};

//获取用户名输入框
var usernameInput = document.getElementById("username");

//失去焦点事件
usernameInput.onblur = function (){
    //输入的用户名
    var username = usernameInput.value.trim();
    if(username === '' || username === null){
        return;
    }
    axios({
        method: 'get',
        url: '/users/' + username
    })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.data === false){
                document.getElementById("username-err").style.display = 'block';
            } else {
                document.getElementById("username-err").style.display = 'none';
            }
        }).catch(function (error) {
        console.log(error);
    });
}

//获取密码输入框
var passwordInput = document.getElementById("password");

//失去焦点事件
passwordInput.onblur = function (){
    var password = document.getElementById("password").value.trim();
    if(password.length < 4){
        document.getElementById("password-err").style.display = 'block';
    } else {
        document.getElementById("password-err").style.display = 'none';
    }
}

//获取提交注册表单按钮
var registerButton = document.getElementById("register-button");
//按钮点击事件
registerButton.onclick = function (){
    var lastname = document.getElementById("lastname").value.trim();
    var firstname = document.getElementById("firstname").value.trim();
    var username = document.getElementById("username").value.trim();
    var password = document.getElementById("password").value.trim();
    var birthday = document.getElementById("birthday").value;
    var sexValue = document.getElementById("sex").value;
    var sex = (sexValue === '1');
    var avatar = document.getElementById("avatar").value;
    if(lastname === '' || lastname === null || firstname === '' || firstname === null || username === '' || username === null || password === '' || password === null){
        alert("注册内容不能留空");
        return;
    }
    if(password.length < 4){
        alert("密码长度不符合要求");
        return;
    }
    axios({
        method: 'post',
        url: '/users/register',
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
            if(resInfo.data === true){
                window.location.href="../html/login.html"
            }
        }).catch(function (error) {
        console.log(error);
    });
}