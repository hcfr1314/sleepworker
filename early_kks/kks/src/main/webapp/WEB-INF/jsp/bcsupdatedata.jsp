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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<head>
    <title>KKS-match</title>
</head>
<script src="<%=path%>/jquery.min.js"></script>
<body>
<h1 style="text-align: center;background-color: chocolate" >更新源文件数据</h1>
<form id="form1" action="/kks/kks/KksMatchAndUpdateController/updateKKSData" method="post" enctype="application/json;charset=UTF-8">
    <table id="table1" class="table table-border table-bordered table-striped" align="center" border="8">
        <thead>
        <tr class="text-c">
            <th>参数名称</th>
            <th>参数值</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>源文件路径</th>
            <th><input type="text" name="inputPath" placeholder="请输入源文件路径"></th>
        </tr>
        <tr>
            <th>目标文件</th>
            <th><input type="text" name="outputPath" placeholder="请输入目标文件"></th>
        </tr>

        </tbody>
    </table>
    <div style="text-align: center"  ><input type="submit" style="background-color: chartreuse;border:1px solid #F00; width:60px; height:50px" id="submit" onclick="login()"></div>
</form>

<div style="background-color: coral;text-align: center;width: 300px;height: 30px;margin-left: 530px
"><font style="margin-top: 50px" id="font" >${message}</font></div>


<script type="text/javascript">
    // $(function ())是文档document加载完自动调用的函数
    function login() {
        document.getElementById("font").innerHTML="";
    }

</script>

</body>
</html>
