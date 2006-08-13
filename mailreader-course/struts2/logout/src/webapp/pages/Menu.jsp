<%@ taglib prefix="s" uri="/tags" %>
<html>
<head>
    <title>MailReader - Menu</title>
</head>

<body>
<h3>Menu Options for <s:property value="user.fullName"/></h3>
<ul>
    <li><a href="<s:url action="Register" />">
        Edit your registration profile
    </a>
    </li>
    <li><a href="<s:url action="Logout"/>">
        Log out of MailReader application
    </a>
</ul>
</body>
</html>
