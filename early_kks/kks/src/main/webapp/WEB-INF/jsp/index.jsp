
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <%
	String path = request.getContextPath();
   %>
<html>



<script src="<%=path%>/jquery.min.js"></script>
<script type="text/javascript">

    function update() {
        window.location.href="http://localhost:8080/kks/bcsupdatedata";
    }

    function add() {
        window.location.href="http://localhost:8080/kks/bcsadddata";
    }

    function uploadandadd() {
        window.location.href="http://localhost:8080/kks/upload";
    }

    function download() {
        window.location.href="http://localhost:8080/kks/download";
    }


    $(document).ready(function() {
        $("#publishArticle").click(function() {
            publishArticle();
        });
    });

    function publishArticle(){
        window.location.href="http://localhost:8080/kks/bcsadddata";
    }


    $(function($) {
        /*alert(121234)
        // 登录点击
        $("#btnSubmit").bind('click',function () {
            alert(111);
        });*/
    });

    $(function($) {
        /*$("#btnSubmit").bind('click',function () {
            alert(111);
        });*/

        $("#btnSubmit").click(function() {
            window.location.href = "http://localhost:8080/kks/bcsadddata";
        });
    });


</script>
<body>
<h1 style="text-align: center;background-color: coral">欢迎来到BCS文件处理系统</h1>

<button id="publishArticle" >新增数据测试</button>
<input id="btnSubmit" class="btn btn-success btn-block btn-lg" type="button" value="更新数据测试">
<div align="center"><input  type="button" value="更新数据" onclick="update()" style="text-align: center;background-color: chartreuse;size: 50px"></div>
<div align="center" style="margin-top: 20px"><input type="button" value="获取新增数据" onclick="add()" style="text-align: center;background-color: chartreuse" ></div>
<div align="center" style="margin-top: 20px"><input type="button" value="上传文件-获取新增数据" onclick="uploadandadd()" style="text-align: center;background-color: chartreuse" ></div>
<div align="center" style="margin-top: 20px"><input type="button" value="文件下载" onclick="download()" style="text-align: center;background-color: chartreuse" ></div>

　　

</body>
</html>
