<html>
<head>
    <title>Api Detail</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
        .string { color: green; }
        .number { color: darkorange; }
        .boolean { color: blue; }
        .null { color: magenta; }
        .key { color: red; }

        .showinfo{
            position: absolute;
            background-color: #eef1f8;
            width: 200px;
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            display: none;
        }
        .showinfo pre{
            padding: 5px;
            border: 1px solid #ccc;
            margin:0;
        }
        table,th,td{
            border:1px solid blue;
        }
    </style>
    <script src="./js/jquery.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            // $(".show-rough").mouseover(function(){
            //     var left = $(this).offset().left + $(this).width() +50;//计算div显示位置
            //     var top = $(this).offset().top + 50;
            //     var _jsonDate = $.parseJSON($(this).text());
            //     var showJson = syntaxHighlight(_jsonDate);
            //     $("#show-info").css({"left":left,"top":top}).show();
            //     $("#show-pre").html(showJson);
            // });
            // $(".show-rough").mouseout(function(){
            //     $("#show-info").hide().html();
            //     $("#show-pre").html();
            // })
        });
        //处理json数据，采用正则过滤出不同类型参数
        function syntaxHighlight(json) {
            if (typeof json != 'string') {
                json = JSON.stringify(json, undefined, 2);
            }
            json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
                var cls = 'number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'key';
                    } else {
                        cls = 'string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                } else if (/null/.test(match)) {
                    cls = 'null';
                }
                return '<span class="' + cls + '">' + match + '</span>';
            });
        };
    </script>
</head>
<body>
    <!--baseinfo-->
    <h3>基本信息:</h3>
    <span style="background: aqua">${method.requestType}</span> ${method.path} ${method.desc}
    <br><br>
    <!--params-->
    <h3>请求参数:</h3>
    <table width="500" border="1">
        <tr>
            <td width="20">参数名称</td>
            <td width="50">参数描述</td>
            <td width="10">是否必须</td>
            <td width="50">参数类型</td>
            <td width="100">参数类型JSON描述</td>
        </tr>
        <#list method.apiParamMetaList as param>
        <tr>
            <td width="20">${param.name}</td>
            <td width="50">${param.desc}</td>
            <td width="10">${param.required?string('是', '否')}</td>
            <td width="50">${param.dataType}</td>
            <td width="100" class="show-rough"><pre>${param.textDef}</pre></td>
        </tr>
        </#list>
    </table>
    <br>

    <h3>输出结果:</h3>
    <#assign result=method.apiResultMeta>
    <table width="800" border="1">
        <tr>
            <td>返回类型</td>
            <td>返回类型JSON描述</td>
        </tr>
        <tr>
            <td>${method.apiResultMeta.returnTypeText}</td>
            <td class="show-rough"><pre>${method.apiResultMeta.textDef}</pre></td>
        </tr>
    </table>

<div id="show-info" class="showinfo">
    <pre id="show-pre"></pre>
</div>
</body>
</html>