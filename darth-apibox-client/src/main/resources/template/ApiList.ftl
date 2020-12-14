<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <#list apiData.apis as api>
        <table width="500" border="1">
            <tr>
                <td><b>${api.desc} ${api.name}</b></td>
            </tr>
            <#list api.apiMethodMetaList as method>
                <tr>
                    <td>
                        <a href="${api.name}-${method.name}.html">
                            <span style="background: aqua">${method.requestType}</span> ${method.path} ${method.desc}
                        </a>
                    </td>
                </tr>
            </#list>
        </table>
        <br>
    </#list>
    </body>
</html>