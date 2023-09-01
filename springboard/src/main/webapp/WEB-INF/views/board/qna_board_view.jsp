<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>MVC 게시판</title>
	<style>
		table{
			width: 70%;
			margin: 0 auto;
		}
		table,td,th{
			border: 1px solid black;
 			border-collapse: collapse;
		}
		.boardContent{
			margin: 0 auto;
			width: 95%;
			height: 300px;
			
		}
	</style>
</head>

<body>
<!-- 게시판 수정 -->
<table>
	<tr>
		<td colspan="5">MVC 게시판</td>
	</tr>
	
	<tr>
		<td>제 목</td>
		<td>${board.BOARD_SUBJECT}</td>
	</tr>
	
	<tr>
		<td colspan="2">
		</td>
	</tr>
	
	<tr>
		<td>내 용</td>
		<td>
			<div class="boardContent">
				${board.BOARD_CONTENT}
			</div>
		</td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>
		
		<c:if test="${ !board.BOARD_FILE == null }">
			<a href="./boardupload/${board.BOARD_FILE}" >
			${board.BOARD_FILE} </a>
		</c:if>
		
		</td>
	</tr>
	<tr>
		<td colspan ="2">
			<a href="./BoardReplyView.bo?num=${board.BOARD_NUM}">
			[답변]
			</a>&nbsp;&nbsp;
			<a href="./BoardModify.bo?num=${board.BOARD_NUM}">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="./BoardDelete.bo?num=${board.BOARD_NUM}">
			[삭제]
			</a>&nbsp;&nbsp;
			<a href="./BoardList.bo">[목록]</a>&nbsp;&nbsp;
		</td>
	</tr>
</table>
<!-- 게시판 수정 -->
</body>
</html>