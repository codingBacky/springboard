<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	현재 페이지 : ${ paging.page } <br />
	현재 페이지의 시작 number : ${ paging.startNum } <br />
	전체 자료의 개수 : ${ boardCount } <br />
	게시글의 정보 : <hr />
	<table>
	    <c:forEach var="board" items="${list}">
	      <tr>
	         <td>
	            ${board.getBOARD_NUM()}
	         </td>
	         
	         <td>
	            <c:choose>  
	               <c:when test="${board.getBOARD_RE_LEV() != 0}"> <!-- if와 동일 -->
	                  <c:forEach var="i" begin="0" end="${board.getBOARD_RE_LEV()}">
	                      &nbsp;&nbsp;
	                  </c:forEach>
	                  ▶
	               </c:when> 
	               <c:otherwise> 
	                  ▶
	               </c:otherwise> 
	            </c:choose>
	            <a href="./BoardDetailAction.bo?num=${ board.getBOARD_NUM() }">
	               ${board.getBOARD_SUBJECT()}
	            </a>
	         </td>
	         
	         <td>${ board.getBOARD_NAME() }</td>
	         <td>${ board.getBOARD_DATE() }</td>   
	         <td>${board.getBOARD_READCOUNT() }</td>
	      </tr>
	   	</c:forEach>
	   	<tr>
	      <td colspan = "5">
	         <c:choose>  
	            <c:when test="${paging.page<=1}"> 
	               [이전]&nbsp;
	            </c:when>
	            <c:otherwise> 
	               <a href="./BoardList.bo?page=${paging.page-1}">[이전]</a>&nbsp;
	            </c:otherwise> 
	         </c:choose>
	         
	         <c:forEach var="a" begin="${paging.startPage}" end="${paging.endPage}" step="1">
	             <c:choose>  
	               <c:when test="${a==paging.page}"> 
	                  [${a}]
	               </c:when>
	               <c:otherwise> 
	                  <a href="./BoardList.bo?page=${a}">[${a}]</a>&nbsp;
	               </c:otherwise> 
	            </c:choose>
	         </c:forEach>
	
	         <c:choose>  
	            <c:when test="${paging.page>=paging.maxPage}"> 
	               [다음]
	            </c:when>
	            <c:otherwise> 
	               <a href="./BoardList.bo?page=${paging.page+1}">[다음]</a>
	            </c:otherwise> 
	         </c:choose>
	      </td>
	   </tr>
	 	<c:if test="${empty list}">
	   		 <tr >
			 <td colspan="5">등록된 글이 없습니다.</td>
	   		</tr>
		</c:if>
	
	   <tr>
	      <td colspan="5">
	            <a href="./BoardWrite.bo">[글쓰기]</a>
	      </td>
	   </tr>
	</table>	
</body>
</html>