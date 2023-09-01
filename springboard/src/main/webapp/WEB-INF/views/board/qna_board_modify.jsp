<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>MVC 게시판</title>
	<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
	</script>
</head>

<body>
<!-- 게시판 수정 -->
<form action="/BoardModifyUserCheck.bo" method="post" name="modifyform">
<input type="hidden" name="BOARD_NUM" value= "${board.BOARD_NUM}">
<table>
	<tr>
		<td colspan="2">MVC 게시판</td>
	</tr>
	<tr>
		<td>제 목</td>
		<td>
			<input name="BOARD_SUBJECT" size="50" maxlength="100" 
				value="${board.getBOARD_SUBJECT()}">
		</td>
	</tr>
	<tr>
		<td>내 용</td>
		<td>
			${board.getBOARD_CONTENT()}
		</td>
	</tr>
	<c:if test="!${(board.BOARD_FILE==null)}">
	<tr>
		<td>파일 첨부</td>
		<td>
			&nbsp;&nbsp;${board.getBOARD_FILE() }
		</td>
	</tr>
	</c:if>

	<tr>
		<td>비밀번호</td>
		<td>
			<input name="BOARD_PASS" type="password">
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr>
		<td colspan="2">
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
		</td>
	</tr>
</table>
</form>
<!-- 게시판 수정 -->
</body>
</html>