<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    html, body {
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #f4f4f4;
      font-family: Arial, sans-serif;
    }

    main {
      width: 400px;
      padding: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    h3 {
      margin-bottom: 20px;
      font-size: 24px;
      color: #333;
    }

    .list {
      list-style: none;
      padding: 0;
      margin: 0 0 20px 0;
    }

    .list li {
      margin-bottom: 15px;
    }

    input[type="email"],
    input[type="password"],
    input[type="text"] {
      width: 90%;
      padding: 10px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    input[type="text"]:readonly {
      background-color: #f9f9f9;
      color: #aaa;
    }

    button {
      width: 100%;
      padding: 10px;
      font-size: 16px;
      color: #fff;
      background-color: #007bff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #0056b3;
    }

    a {
      text-decoration: none;
      font-size: 14px;
      color: #007bff;
      display: inline-block;
      margin-top: 10px;
    }

    .warn {
      margin-top: 15px;
      color: #d9534f;
      font-weight: bold;
    }
  </style>
</head>
<body>
<main>
  <h3>로그인</h3>
  <form action="signin" method="post">
    <ul class="list">
      <li><input type="email" name="email" placeholder="이메일" required autofocus /></li>
      <li><input type="password" name="password" placeholder="비밀번호" required /></li>
    </ul>
    <p>
      <button type="submit">로그인</button>
      <a href="./signupForm">회원가입</a>
    </p>
    <input type="text" name="redirectUrl" value="${param.redirectUrl}" placeholder="redirectUrl" readonly />
  </form>
  <c:if test="${param.mode=='FAILURE'}">
    <p class="warn">로그인 실패입니다.</p>
  </c:if>
</main>
</body>
</html>
