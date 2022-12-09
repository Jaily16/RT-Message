//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};
//尝试登录事件
function tryLogin(){

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if(username === "" || username === null || password === "" || password === null){
        alert("用户名或密码不能为空");
        return;
    }
    axios({
        method: 'post',
        url: '/users/login',
        data: {
            username: username,
            password: password
        }
    })
        .then((response) => {
            this.resInfo.data = response.data.data;
            this.resInfo.code = response.data.code;
            this.resInfo.msg = response.data.msg;
            if(this.resInfo.code === 11){
                alert(this.resInfo.msg);
                return;
            }
            if(this.resInfo.code === 12){
                alert(this.resInfo.msg);
                return;
            }
            window.location.href="../html/friends.html"
        }).catch(function (error) {
        console.log(error);
    });

}

