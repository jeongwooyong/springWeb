<!DOCTYPE html>
<html>
<head>
  <title>게시글목록</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='number'] {
      width:50px;
      text-align:center;
    }
    textarea {
      width: 100%;
      height: 6.25em;
      border: none;
      resize: none;
    }
  </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>리뷰</h3>
  <form action="./addReview" method="get">
    <a><textarea name="contents" placeholder="500자 이내" required autofocus></textarea></a>
    <p>
    <p>평점<input type="number" name="grade" required> <button type="submit">저장</button></p>
    </p>
  </form>
  <h3>게시글 목록</h3>
  <form action="./reviewList" method="get">
    <p>
      Page : <input type="number" name="page" min="1" value="${limit.page}"
                   required autofocus/> Count : <input type="number" name="count" min="5"
                                                  step="5" value="${limit.count}" required/>
      <button type="submit">검색</button>
    </p>
  </form>
  <table class="list">
    <tr>
      <th></th>

    </tr>
    <c:forEach var="review" items="${reviewList}">
      <tr>

        <td>
          <p>NO.${review.reviewId},${review.name},
              ${review.cdate}, 평점:${review.grade}</p>
          <p
                href="./review?reviewId=${review.reviewId}">${review.contentsEncoded}</p>
          <!-- titleEncoded는 제목에서 html을 못쓰게 한다 -->
          <!-- >112. 제목 &lt;script&gt;alert(&#34;abc&#34;);&lt;/script&gt; &lt;h1&gt;큰글자&lt;/h1&gt;</p> -->
        </td>
      </tr>
    </c:forEach>
  </table>
</main>
</body>
</html>