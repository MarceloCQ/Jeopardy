<%-- 
    Document   : registration
    Created on : Apr 21, 2015, 10:53:41 PM
    Author     : ZyanyaVE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Send e-mail to someone@example.com:</h2>
        <form action="MAILTO:someone@example.com" method="post" enctype="text/plain">
        Name:<br>
        <input type="text" name="name" value="your name"><br>
        E-mail:<br>
        <input type="text" name="mail" value="your email"><br>
        Comment:<br>
        <input type="text" name="comment" value="your comment" size="50"><br><br>
        <input type="submit" value="Send">
<input type="reset" value="Reset">
</form>
    </body>
</html>
