<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2019/7/4
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String path = request.getContextPath();
%>
<html>

<script src="<%=path%>/jquery.min.js"></script>
<script src="<%=path%>/jquery.form.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <title>bcs提交</title>
</head>

<body>
<h1 style="text-align: center;background-color: chocolate" >bsc-输出nari新增数据</h1>
<form id="form1" action="usertest" method="post" enctype="application/json;charset=utf-8">
    源文件路径：<input type="text" value="请输入源文件路径" name="sourcefiles[0].path"><br/>
    密码：<input type="text" name="users[0].password"><br/>
    用户名：<input type="text" name="users[1].username"><br/>
    密码：<input type="text" name="users[1].password"><br/>
    <input type="submit" id="submit" onclick="login()"><br/>



    <input type="text" value="" id="get"  onFocus="WdatePicker({disabledDays:[<?=$strdoctortime;?>]})"/> />
</form>

<font id="font">${message}</font>


<select id="orz">
    <option value="1">111</option>
    <option value="2">222</option>
    <option value="3">333</option>
</select>
<script>
    $("#orz").change(function(){
        $("#get").val($(this).val());
    });
</script>

<script type="text/javascript">
    // $(function ())是文档document加载完自动调用的函数
    $(function () {
        /*
        获取form元素，调用其ajaxForm(...)方法
        内部的function(data)的data就是后台返回的数据
        */
        $("#form1").ajaxForm(function (data) {

            alert(111)
            alert(data.status)

            alert(JSON.stringify(data))
                console.log(data);
                console.log("str:" + JSON.stringify(data));
            }
        );
    });

   function login() {
       document.getElementById("font").innerHTML="";
   }
    /*function login() {

        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "usertest" ,//url
            contentType:"application/json:charset=UTF-8",
            data: $('#form1').serialize(),
            success: function (result) {
                alert(result)
              /!*  console.log(result);//打印服务端返回的数据(调试用)
                if (result.resultCode == 200) {
                    alert("SUCCESS");
                }
                ;*!/
            },
            error : function() {
               alert("异常！");
            }
        });

        return false;
    }*/
</script>


</body>
</html>
