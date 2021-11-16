
<%@ page import="com.koreait.board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO)request.getAttribute("data");
    int prevIboard = (int)request.getAttribute("prevIboard");
    int nextIboard = (int)request.getAttribute("nextIboard");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>디테일</title>
</head>
<body>
    <div>
        <a href="/del?iboard=<%=vo.getIboard() %>"><input type="button" value="삭제"></a>
        <a href="/mod?iboard=<%=vo.getIboard() %>"><input type="button" value="수정"></a>
        <a href="/list"><input type="button" value="리스트"></a>
    </div>

    <!--표현식-->
    <div>번호 : <%=vo.getIboard()%></div>
    <div>제목 : <%=vo.getTitle()%></div>
    <div>작성자 : <%=vo.getWriter()%></div>
    <div>작성일시 : <%=vo.getRdt()%></div>
    <div>내용 : <%=vo.getCtnt()%></div>

    <div>
        <% if (prevIboard != 0) { %>
        <a href="detail?iboard=<%=prevIboard%>"><input type="button" value="이전글"></a>
        <% } %>
        <% if (nextIboard != 0) { %>
        <a href="detail?iboard=<%=nextIboard%>"><input type="button" value="다음글"></a>
        <% } %>
    </div>
</body>
</html>
