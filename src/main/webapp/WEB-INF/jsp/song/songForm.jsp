<%--
  Created by IntelliJ IDEA.
  User: WooYong
  Date: 2023-06-12
  Time: 오후 8:56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>글쓰기</title>
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
    <h3>글쓰기</h3>
    <form action="./addSong" method="post">
        <p><input type="text" name="title" placeholder="제목" required autofocus/></p>
        <p><input type="text" name="name" placeholder="가수이름" required autofocus/></p>
        <p>
            <button type="submit">등록</button>
        </p>
    </form>
</main>
</body>
</html>