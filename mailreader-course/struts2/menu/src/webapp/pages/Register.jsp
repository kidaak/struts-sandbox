<%@ taglib prefix="s" uri="/tags" %>
<html>
<head>
    <title>MailReader - Register</title>
</head>

<body onLoad="self.focus();document.Register_save.username.focus()">

<s:actionerror/>
<s:form action="Register_save" validate="true">

    <s:textfield label="Username" name="username"/>

    <s:password label="Password" name="password"/>

    <s:password label="(Repeat) Password" name="password2"/>

    <s:textfield label="Full Name" name="fullName"/>

    <s:textfield label="From Address" name="fromAddress"/>

    <s:textfield label="Reply To Address" name="replyToAddress"/>

    <s:submit value="Save" name="Save"/>

</s:form>


</body>
</html>
