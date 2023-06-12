<%--
  Created by IntelliJ IDEA.
  User: WooYong
  Date: 2023-06-12
  Time: 오후 8:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>글 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/default.css">
    <style>
        input[type='text'] {width:90%;}
        textarea {width:90%; height:200px;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
    <h3>글 수정</h3>
    <form action="./updateSong" method="post">
        <p><input type="text" name="title" value="${song.titleEncoded}"
                  placeholder="제목" required autofocus/></p>
        <p><input type="text" name="name" value="${song.name}"
                  placeholder="가수이름" required autofocus/></p>
        <p>
            <button type="submit">저장</button>
        </p>
        <input type="hidden" name="songId" value="${song.songId}"/>
    </form>
</main>
</body>
</html>