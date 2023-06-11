<!DOCTYPE html>
<html>
<head>
    <title>영화목록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/default.css">
    <style>
        input[type='number'] {
            width:50px;
            text-align:center;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
    <h3>게시글 목록</h3>
    <form action="./movieList" method="get">
        <p>
            페이지 : <input type="number" name="page" min="1" value="${limit.page}"
                         required autofocus/>행 : <input type="number" name="count" min="5"
                                                        step="5" value="${limit.count}" required/>
            <button type="submit">검색</button>
            <a href="./movieForm">글쓰기</a>
        </p>
    </form>
    <table class="list">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>감독</th>

        </tr>
        <c:forEach var="movie" items="${movieList}">
            <tr>
                <td>${movie.movieId}</td>
                <td><a
                        href="./movie?movieId=${movie.movieId}">${movie.titleEncoded}</a>
                    <!-- titleEncoded는 제목에서 html을 못쓰게 한다 -->
                    <!-- >112. 제목 &lt;script&gt;alert(&#34;abc&#34;);&lt;/script&gt; &lt;h1&gt;큰글자&lt;/h1&gt;</p> -->
                </td>
                <td><a href="../movie?movieId=${movie.movieId}">${movie.director}</a></td>

            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>