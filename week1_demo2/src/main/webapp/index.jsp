<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .custom-checkbox {
            margin-right: 5px;
        }

        button.signin-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        button.signin-button:hover {
            background-color: #0056b3;
        }

        p {
            margin-top: 15px;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
<div class="login-container">
    <h1>Login</h1>
    <form action="ControlServlet?action=login" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="txtUserName" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="txtPassWord" required>
        </div>
        <div class="form-group">
            <input type="checkbox" id="remember-me" class="custom-checkbox">
            <label for="remember-me">Keep me signed in</label>
        </div>
        <button type="submit" class="signin-button">Sign In</button>
        <p><a href="#">Forgot Password?</a></p>
    </form>
</div>
</body>
</html>