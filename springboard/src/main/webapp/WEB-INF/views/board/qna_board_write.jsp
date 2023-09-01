<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
--%>
<!DOCTYPE html>
<html>
<head>
	<title>MVC 게시판</title>
	<script language="javascript">
	function addboard(){
		//정확하게 해장 자료가 입력되어 있는지 체크하는 구문이 들어간다.
		boardform.submit();//문제가 없다면 저장 
	}
	</script>
</head>
<body>
<!-- 게시판 등록 -->
<h1>게시글 쓰기</h1>
<form action="./BoardAddAction.bo" method="post" name="boardform">
	<!-- 게시글을 쓰고자 할때 enctype 반드시 지정해줘야함 -->
	<label for="">작성자</label>
	<input type="text" name="BOARD_NAME" id="BOARD_NAME" /><br />
	
	<label for="">비밀번호</label>
	<input type="password" name="BOARD_PASS" id="BOARD_PASS" /><br />
	
	<label for="BOARD_SUBJECT">제목</label>
	<input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT"  size="50" maxlength="100"/><br />
	
	<label for="BOARD_CONTENT">내용</label>
	<textarea name="BOARD_CONTENT" id="BOARD_CONTENT" cols="60" rows="15"></textarea><br />
	
<!-- 	<label for="BOARD_FILE">파일첨부</label>
	<input type="file" name="BOARD_FILE" id="BOARD_FILE"/> <span>파일 최대크기 : 5MB</span> <br />
 -->
			<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>

</form>
<!-- 게시판 등록 -->
</body>
</html>