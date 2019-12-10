
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>文件上传</title>
</head>
<script src="<%=path%>/jquery.min.js"></script>

<script>

    $(document).ready(function() {
        $("#publishArticle").click(function() {
            publishArticle();
        });
    });

    function publishArticle(){
        window.location.href="http://localhost:8080/kks/index";
    }

</script>
<body>

<div align="center"><button id="publishArticle" >返回首页</button></div>
	<form method="get" action="downloaddata" enctype="multipart/form-data" id="formId">

		<table id="table1" class="table table-border table-bordered table-striped" align="center" border="8">
			<thead>
			<tr class="text-c">
				<th>序号</th>
				<th>参数名称</th>
				<th>参数值</th>
			</tr>
			</thead>
			<tbody>
			<tr class="text-c">
				<th>0</th>
				<td><div>文件名称</div></td>
				<td><input type="text" class="input-text" placeholder="请输入文件名称" name="fileName" ></td>
			</tr>

			<tr>
				<th>2</th>
				<td>操作</td>
				<td><input type="submit" value="提交" /></td>
			</tr>
			</tbody>
		</table>
	</form>

   ${message.status}
</body>
</html>
