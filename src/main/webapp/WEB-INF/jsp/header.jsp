<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wep Develope</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #f4f4f4; /* 헤더 배경색 */
            border-bottom: 1px solid #ccc;
            padding: 10px 20px; /* 헤더 여백 */
        }

        .nav {
            list-style: none; /* 기본 리스트 스타일 제거 */
            margin: 0;
            padding: 0;
            display: flex; /* 가로 정렬 */
            gap: 15px; /* 메뉴 간의 간격 */
            justify-content: center; /* 가운데 정렬 */
            align-items: center; /* 세로 가운데 정렬 */
        }

        .nav li {
            display: inline;
        }

        .nav a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .nav a:hover {
            background-color: #ddd;
            color: #000;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<header>
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/">홈</a></li>
        <li><a href="${pageContext.request.contextPath}/app/user/userList">회원</a></li>
        <li><a href="${pageContext.request.contextPath}/app/article/articleList">게시글</a></li>
        <li><a href="${pageContext.request.contextPath}/app/movie/movieList">영화목록</a></li>
        <li><a href="${pageContext.request.contextPath}/app/song/songList">노래목록</a></li>
        <li><a href="${pageContext.request.contextPath}/app/post/postList">Post목록</a></li>
        <li><a href="${pageContext.request.contextPath}/app/review/reviewList">평점목록</a></li>
        <c:choose>
            <c:when test="${empty sessionScope.me_userId}">
                <li><a href="${pageContext.request.contextPath}/app/user/signinForm">로그인</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/signupForm">회원가입</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/app/user/myInfo">${sessionScope.me_name}님</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/signout">로그아웃</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</header>
<h1>Web Develope</h1>
</body>
</html>
