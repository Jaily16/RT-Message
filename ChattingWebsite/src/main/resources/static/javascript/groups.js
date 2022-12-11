//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};

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

//在页面加载完成后立即初始化页面右上角的用户状态信息
window.onload = function (){
    loadUserInfo();
}