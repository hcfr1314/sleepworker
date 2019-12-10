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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head>
    <title>bcs提交</title>

    <style>

       /* #div {

            text-align: center;
            width: 300px;
            height: 100px;
            background: #3EFD9B;


        }*/

    </style>

</head>

<body>
<h1 style="text-align: center;background-color: chocolate">BCS-输出nari新增数据</h1>
<span class="l" ><a href="javascript:;" onclick="add()" class="btn btn-primary radius"><i style="margin-left: 800px" class="Hui-iconfont">&#xe600;</i> 添加行</a></span>
<form id="addform" action="updateKKSData" method="post" enctype="application/json;charset=UTF-8" >
    <table id="table1" class="table table-border table-bordered table-striped" align="center" border="8">
        <thead>
        <tr class="text-c">
            <th>序号</th>
            <th>参数名称</th>
            <th>参数值</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <th>0</th>
            <td><div>调度名称</div></td>
            <td><input type="text" class="input-text" placeholder="请输入调度名称" name="scheduler_name" ></td>
            <td><input class="btn btn-primary radius" type="button" value="......."></td>
        </tr>

        <tr class="text-c">
            <th>1</th>
            <td><div>目标文件路径</div></td>
            <td><input type="text" class="input-text" placeholder="请输入目标文件路径" name="outputPath" ></td>
            <td><input class="btn btn-primary radius" type="button"  value="......."></td>
        </tr>

        <tr class="text-c">
            <th>2</th>
            <td> <div>源文件路径</div></td>
            <td><input type="text" class="input-text" placeholder="请输入源文件路径" name="inputPathList[0].path" ></td>
            <td><input class="btn btn-primary radius" type="button" onclick="del(this);" value="删除"></td>
        </tr>
        </tbody>
    </table>

        <div align="center" text-align="center"><input type="submit" style="background-color: chartreuse;border:1px solid #F00; width:60px; height:50px;margin-right: 0px" id="submit" onclick="login()"></div>
</form>
<div  id="div" align="center" style="background-color:coral;color:black;width: 400px;height: 20px;margin: 0 auto"><font id="font">${message}</font></div>


<script type="text/javascript">
    // $(function ())是文档document加载完自动调用的函数
    /*  $(function () {

          /!*
          获取form元素，调用其ajaxForm(...)方法
          内部的function(data)的data就是后台返回的数据
          *!/
          $("#addform").ajaxForm(function (data) {
              alert(111)
                  console.log(data);
                  console.log("str:" + JSON.stringify(data));
              }
          );
      });*/
    function login() {

            document.getElementById("font").innerHTML="";
       /* $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "updateKKSData",//url
            data: $('#addform').serialize(),
            success: function (data) {
                alert(1111)
                alert(data.status)
                /!* console.log(result);//打印服务端返回的数据(调试用)
                 if (data.status == "成功") {
                     alert("SUCCESS");
                 }
                 ;*!/
            },
            error: function () {
                alert("异常！");
            }
        });*/

    }

    var i=2;
    function del(e) {
        $(e).parent().parent().remove();
        i--;
    }

    function add() {
        i++;
        $("#table1").find('tbody').append("<tr class=\"text-c\">\n" +
            "        <th>"+i+"</th>\n" +
            "        <td> <div>源文件路径</div></td>\n"+
            "        <td ><input type=\"text\" class=\"input-text\" placeholder=\"请输入源文件路径\" name=\"inputPathList["+(i-2)+"].path\"></td>\n" +
            "        <td ><input type=\"button\" class=\"btn btn-primary radius\" onclick=\"del(this);\" value=\"删除\"></td>\n" +
            "     </tr>")
    }
</script>


</body>
</html>
