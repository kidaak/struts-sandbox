<%@taglib uri="/webwork" prefix="ww" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title><ww:text name="change.title"/></title>
        <link href="<ww:url value="/css/mailreader.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body>

    <p>
       <ww:text name="change.message"/>
    </p>

    <p>
        <a href="<ww:url action="Logon!input"/>">
            <ww:text name="change.try"/>
        </a>
    </p>

    </body>
</html>
