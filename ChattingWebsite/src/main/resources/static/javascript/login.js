function tryLogin(){

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if(username === "" || username === null || password === "" || password === null){
        alert("用户名或密码不能为空");
        return;
    }
    var resInfo={data:"", code:"", msg:""}
    axios({
        method: 'post',
        url: '/users/login',
        data: {
            username: username,
            password: password
            }
        })
        .then((response) => {
            resInfo.data = response.data.data;
            resInfo.code = response.data.code;
            resInfo.msg = response.data.msg;
            if(resInfo.code === 11){
                alert(resInfo.msg);
                return;
            }
            if(resInfo.code === 12){
                alert(resInfo.msg);
                return;
            }
            window.location.href="../html/friends.html"
    }).catch(function (error) {
        console.log(error);
    });;

}

