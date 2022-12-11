//用于接收后台返回的json数据
var resInfo={data:"", code:"", msg:""};
axios({
    method: 'get',
    url: '/users/verify'
})
    .then((response) => {
        resInfo.data = response.data.data;
        resInfo.code = response.data.code;
        resInfo.msg = response.data.msg;
        if(resInfo.data === false){
            alert(resInfo.msg);
            window.location.href="../html/login.html";
        }
    }).catch(function (error) {
    console.log(error);
});