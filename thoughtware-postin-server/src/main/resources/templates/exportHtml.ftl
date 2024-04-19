<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POSTIN-HTML</title>
    <style>

        ul{
            list-style: none;
            margin: 0;
            padding: 0;
        }

        /* CSS 样式可以在这里定义 */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .header {
            border-bottom: 1px solid #e4e4e4;
            padding: 0 0 0 26px;
        }

        .container {
            display: flex;
            flex-direction: row;
            height: calc(100vh - 60px);
        }

        .sidebar {
            width: 250px;
            border-right: 1px solid #e4e4e4;
        }

        .content {
            flex-grow: 1;
            padding: 20px;
        }

        .sidebarTitle{
            padding: 10px 20px;
            border-bottom: 1px solid #e4e4e4;
            background: #eeeeee;
        }

        .apiList-title{
            font-weight: bold;
            font-size: 18px;
            margin: 0 0 10px 10px;
        }

        .tree-li{
            padding: 10px 20px;
            font-size: 13px;
            border-bottom: 1px solid #e4e4e4;
            cursor: pointer;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
        .tree-li:hover{
            background: #dedede;
        }

        .table-list {
            list-style-type: none;
            padding: 0;
        }

        .table-header-box {
            /*font-weight: bold;*/
            border-bottom: 1px solid #e4e4e4;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .list-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            cursor: pointer;
        }

        .list-item:hover{
            background: #dedede;
        }

        .item-name {
            flex-basis: 30%;
        }

        .item-address {
            flex-basis: 50%;
            word-break: break-all;
        }

        .item-status {
            flex-basis: 20%;
        }

        .item-request-type {
            padding: 4px 8px;
            border-radius: 4px;
            margin: 0 10px 0 0;
        }

        .request-type-post {
            background-color: #ff7f50;
            color: #fff;
        }

        .request-type-get {
            background-color: #32cd32;
            color: #fff;
        }

        .request-type-put {
            background-color: #e1cc41;
            color: #fff;
        }

        .request-type-delete {
            background-color: #ff4500;
            color: #fff;
        }

        .request-type-default {
            background-color: #6397f2;
            color: #fff;
        }


        .detail-container {
            background-color: #f9f9f9;
            padding: 10px;
            border-radius: 5px;
            margin-top: 20px;
        }

        .detail-container p {
            margin: 5px 0;
        }

        #detailContainer{
            width: 100%;
            height: 100%;
            display: none;
        }

        #backBtn{
            width: 80px;
            padding: 5px;
            border: 1px solid #e4e4e4;
            text-align: center;
            cursor: pointer;
            color: #6f6f6f;
        }
        #backBtn:hover{
            color: black;
        }

        #detailBox{
            padding: 20px;
            margin: 10px 0;
            border: 1px solid #e4e4e4;
        }

        #apiNameElement{
            font-size: 16px;
            font-weight: bold;
        }

        .itemBox{
            display: flex;
            gap: 20px;
            align-items: center;
            margin:  0 0 20px;
        }

        #statusElement{
            border-radius: 4px;
            padding: 5px;
            color: white;
            font-size: 12px;
        }

        #protocolType{
            background: rgb(0, 120, 212);
            padding: 5px;
            border-radius: 4px;
            color: white;
            font-size: 13px;
        }

        .fontSize{
            font-size: 13px;
        }

        .title-box{
            font-size: 18px;
        }

        /* 表格容器样式 */
        .table-container {
            /*margin: 20px 0;*/
        }

        /* 表格样式 */
        .table {
            list-style: none;
            padding: 0;
            margin: 0;
            display: table;
            width: 100%;
            border-collapse: collapse;
        }

        /* 表格表头样式 */
        .table-header {
            display: table-row;
        }

        /* 表格内容项样式 */
        .table-data {
            display: table-row;
        }

        /* 表格单元格样式 */
        .table span {
            display: table-cell;
            padding: 8px;
            border: 1px solid #ddd;
        }

        /* 表格单元格宽度样式 */
        .table span:first-child {
            width: 20%;
        }

        .table span:nth-child(2){
            width: 20%;
        }


        .table span:nth-child(3),
        .table span:nth-child(4){
            width: 10%;
        }


        .table span:last-child {
            width: 20%;
        }

        .request-title{
            margin: 10px 0 8px;
            font-size: 13px;
            color: #747474;
        }

    </style>
</head>
<body>
    <header class="header">
      <!-- 在这里插入头部内容 -->
      <h5 id="workspaceName" style="margin: 10px 0"> </h5>
    </header>

    <div class="container">
      <aside class="sidebar">
        <!-- 在这里插入左侧导航栏内容 -->
        <ul id="sidebarList" style="overflow: hidden">
          <li class="sidebarTitle">分组列表</li>
          <!-- 左侧导航栏项将在 JavaScript 中生成 -->
        </ul>
      </aside>

      <div class="content" style="overflow: auto">
        <!-- 在这里插入右侧内容 -->
        <div id="rightContent">
          <!-- 右侧内容将在 JavaScript 中生成 -->
            <ul id="apiList" class="table-list">
                <li class="table-header-box">
                    <span class="item-name">名称</span>
                    <span class="item-address">地址</span>
                    <span class="item-status">状态</span>
                </li>
            </ul>
            <div id="detailContainer" >
                <!-- 这里插入接口的详情内容 -->
                <div id="backBtn" onclick="backToList()">返回列表</div>

            </div>
        </div>
      </div>
    </div>
<script id="json-data">
    // 模拟数据
    // let jsonData = {"projectInfo":{"projectIcon":"/images/pi1.png","projectName":"默认空间","projectId":"bd26c6ec5c6e"},"apiGroupList":[{"children":[{"request":{"query":[],"header":[],"body":{"bodyType":"raw","raw":{"rawType":"text/plain","raw":"","id":"288dfa644b6f"}}},"path":"ws://192.168.10.14","name":"45345","updateTime":1709911795045,"id":"288dfa644b6f","protocolType":"ws","type":"ws","status":{"color":"orange","name":"开发中","id":"developmentid","type":"system"}}],"name":"dfdfd666666666666666666666666666666666666666666666666666","id":"a3c789b21f3c","type":"category"},{"children":[{"children":[{"request":{"query":[],"header":[],"id":"727ae5133763","body":{"bodyType":"none"}},"path":"/passport/login/bb","methodType":"get","response":{"result":[{"dataType":"json","name":"成功","id":"0df30579cd3d","httpCode":200,"jsonText":"{\"type\": \"object\",\"properties\": {}}"}],"header":[]},"name":"app5555555555555555555","updateTime":1712130470000,"id":"727ae5133763","protocolType":"http","type":"http","status":{"color":"orange","name":"开发中","id":"developmentid","type":"system"}}],"name":"565465","id":"697fbadf75ce","type":"category"},{"request":{"query":[],"header":[],"id":"1012c5dee0d64d1a9150295ab534c8e2","body":{"bodyType":"json","json":{"id":"1012c5dee0d64d1a9150295ab534c8e2","jsonText":"{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\"},\"fieldName1\":{\"type\":\"string\"}}}"}}},"path":"/config/createConfig","methodType":"post","response":{"result":[{"dataType":"json","name":"成功","id":"b99adc0c77bc","httpCode":200,"jsonText":"{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\",\"mock\":{\"mock\":\"@name\"}},\"fieldName1\":{\"type\":\"string\",\"mock\":{\"mock\":\"@first\"}}}}"}],"header":[]},"name":"test","updateTime":1704679498000,"id":"1012c5dee0d64d1a9150295ab534c8e2","protocolType":"http","type":"http","status":{"color":"orange","name":"开发中","id":"developmentid","type":"system"}}],"name":"test","id":"86e78329ea11","type":"category"},{"children":[{"children":[{"children":[{"request":{"query":[],"header":[],"id":"a960bbb4775e4a228272c069bd7b8b15","body":{"bodyType":"none"}},"path":"/passport/login/aaaa","methodType":"get","response":{"result":[{"dataType":"json","name":"成功","id":"329d364fe3a3","httpCode":200,"jsonText":"{\"type\": \"object\",\"properties\": {}}"}],"header":[]},"name":"test","updateTime":1701926857000,"id":"a960bbb4775e4a228272c069bd7b8b15","protocolType":"http","type":"http","status":{"color":"orange","name":"开发中","id":"developmentid","type":"system"}}],"name":"test2","id":"ccf35c4282fb","type":"category"}],"name":"test","id":"dddd358e9699","type":"category"},{"request":{"query":[{"name":"tets","id":"6cfa3b8110d2","sort":0,"value":"@domain","required":0}],"header":[{"name":"accept","id":"18ccdb89a291","sort":0,"value":"test","required":1}],"body":{"bodyType":"json","json":{"id":"e788dcac2291","jsonText":"{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\",\"mock\":{\"mock\":\"@name\"}},\"fieldName1\":{\"type\":\"string\"}},\"required\":[\"fieldName1\"]}"}}},"path":"ws://192.168.10.34:8090/ws","name":"WebSocket1766","updateTime":1697507326000,"id":"e788dcac2291","protocolType":"ws","type":"ws","status":{"color":"green","name":"已发布","id":"publishid","type":"system"}},{"request":{"afterScript":"","query":[],"preScript":"","header":[],"id":"219512b6cb74","body":{"bodyType":"json","json":{"id":"219512b6cb74","jsonText":"{\"type\":\"object\",\"properties\":{\"test\":{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\",\"mock\":{\"mock\":\"@name\"}},\"fieldName1\":{\"type\":\"string\",\"mock\":{\"mock\":\"@ip\"}}},\"required\":[\"fieldName\",\"fieldName1\"]}},\"required\":[\"test\"]}"}}},"path":"/passport/login/aaa","methodType":"get","response":{"result":[{"dataType":"json","name":"成功","id":"219512b6cb78","httpCode":200,"jsonText":"{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\",\"mock\":{\"mock\":\"@ip\"}},\"fieldName1\":{\"type\":\"string\",\"mock\":{\"mock\":\"@last\"}},\"test2\":{\"type\":\"object\",\"properties\":{\"fieldName\":{\"type\":\"string\"}},\"mock\":{\"mock\":\"@email\"},\"required\":[\"fieldName\"]}},\"required\":[\"root\"]}"},{"dataType":"raw","name":"erere","id":"48fabeb557df","httpCode":201}],"header":[]},"name":"name12444","updateTime":1713254346337,"id":"219512b6cb74","protocolType":"http","type":"http","status":{"color":"cyan","name":"测试","id":"testid","type":"system"}}],"name":"默认分组eeeeeeeeeeeeeeeeeeeeeeee","id":"a8ead30da71c","type":"category"}]}
    // JSON 数据
    let jsonData = ${jsonData}
</script>
<script >

    //解析数据
    //空间项目信息
    let projectJson = jsonData.projectInfo
    document.getElementById('workspaceName').innerHTML=projectJson.projectName;

</script>
    <script>
        //公共

        /**
         * 展示请求类型
         */
        const showRequestType = (type) => {
            const requestTypeSpan = document.createElement('span');
            requestTypeSpan.classList.add('item-request-type');

            switch (type) {
                case "post":
                    requestTypeSpan.textContent = "POST";
                    requestTypeSpan.classList.add('request-type-post',"fontSize");
                    break;
                case "get":
                    requestTypeSpan.textContent = "GET";
                    requestTypeSpan.classList.add('request-type-get',"fontSize");
                    break;
                case "put":
                    requestTypeSpan.textContent = "PUT";
                    requestTypeSpan.classList.add('request-type-put',"fontSize");
                    break;
                case "delete":
                    requestTypeSpan.textContent = "DELETE";
                    requestTypeSpan.classList.add('request-type-delete',"fontSize");
                    break;
                default:
                    requestTypeSpan.textContent = type.toUpperCase();
                    requestTypeSpan.classList.add('request-type-default',"fontSize");
                    break;
            }

            return requestTypeSpan;
        };

        /**
         * 返回列表
         */
        const backToList = () =>{
            let detailContainer = document.getElementById("detailContainer");
            detailContainer.style.display="none"
            let detailBox = document.getElementById("detailBox");
            detailBox.remove();

            ulList.style.display = "block";
        }

        /**
         * 详情中，请求，响应的头
         * @param detailBox
         * @param title
         */
        const addTitle = (detailBox,title) =>{
            let requestTitle = document.createElement('div');
            requestTitle.classList.add("request-title")
            requestTitle.textContent = title
            detailBox.appendChild(requestTitle)
        }
    </script>
    <script>
        //详情
        const clickItem = (item,node) =>{
            item.addEventListener("click", function() {

                // 隐藏表格
                ulList.style.display = "none";
                // 详情内容
                let detailContainer = document.getElementById("detailContainer");
                detailContainer.style.display="block"

                //详情
                let detailBox = document.createElement("div");
                detailBox.id="detailBox"

                //创建基础信息
                baseInfo(detailBox,node)

                //创建请求信息
                requestInfo(detailBox,node)

                if(node.protocolType==="http"){
                    //创建响应信息
                    responseInfo(detailBox,node.response)
                }


                detailContainer.appendChild(detailBox)

            });
        }
    </script>
    <script>
        const sidebarList = document.getElementById('sidebarList');

        let apiArr = []
        /**
         * 递归创建目录树
         */
        const createDirectoryTree = (data, level = 1) => {
            data.forEach(function (group) {

                // 创建目录树项
                const listItem = document.createElement('li');
                if(group.type==="category"){
                    listItem.textContent = group.name;
                    listItem.classList.add('tree-li');
                    listItem.style.paddingLeft = (level * 20) + "px";// 添加左侧缩进样式
                    sidebarList.appendChild(listItem);
                }else {
                    apiArr = [...apiArr,group]
                }

                if (group.children && group.children.length > 0) {
                    // 创建子目录树
                    const childList = document.createElement('ul');
                    childList.classList.add('child-list');
                    listItem.appendChild(childList);

                    createDirectoryTree(group.children, level + 1); // 递归创建子目录树，传递 level + 1

                    // 添加展开/折叠子目录的点击事件
                    listItem.addEventListener('click', function () {
                        childList.classList.toggle('hidden');
                    });
                }

                // 添加点击事件，显示相应的 nodelist 内容
                listItem.addEventListener('click', function () {
                    let detailContainer = document.getElementById("detailContainer")
                    detailContainer.innerHTML="<div id=\"backBtn\" onclick=\"backToList()\">返回列表</div>";
                    detailContainer.style.display = 'none'
                    ulList.style.display = "block";

                    let apiList=[];

                    group.children&&group.children.forEach(item=>{
                        if(item.type!=="category"){
                            apiList = [...apiList,item]
                        }
                    })

                    showNodeList(apiList, group.name);
                });
            });
        }
        /**
         * 显示右侧内容页的 nodelist
         */
        const showNodeList = (nodeList, title) => {
            const ulList = document.getElementById('apiList');
            ulList.innerHTML = ''; // 清空原有的内容

            // 添加标题
            const titleElement = document.createElement('div');
            titleElement.classList.add("apiList-title")
            titleElement.textContent = title;
            ulList.appendChild(titleElement);

            // 添加表头
            const tableHeader = document.createElement('li');
            tableHeader.classList.add('table-header-box');

            const nameHeader = document.createElement('span');
            nameHeader.classList.add('item-name');
            nameHeader.textContent = '名称';

            const addressHeader = document.createElement('span');
            addressHeader.classList.add('item-address');
            addressHeader.textContent = '地址';

            const statusHeader = document.createElement('span');
            statusHeader.classList.add('item-status');
            statusHeader.textContent = '状态';

            tableHeader.appendChild(nameHeader);
            tableHeader.appendChild(addressHeader);
            tableHeader.appendChild(statusHeader);

            ulList.appendChild(tableHeader);

            // 添加节点列表项
            if (nodeList&&nodeList.length === 0) {
                // 如果节点列表为空，显示提示信息
                const listItem = document.createElement('li');
                listItem.textContent = '暂无数据';
                ulList.appendChild(listItem);
            } else {
                // 如果节点列表不为空，显示节点列表
                nodeList&&nodeList.forEach(function (node) {
                    const listItem = document.createElement('li');
                    listItem.classList.add('list-item');

                    const nameSpan = document.createElement('span');
                    nameSpan.classList.add('item-name');
                    nameSpan.textContent = node.name;

                    const addressSpan = document.createElement('span');
                    addressSpan.classList.add('item-address');
                    const addressTextSpan = document.createElement('span');
                    addressTextSpan.textContent = node.path;

                    if(node.protocolType==="http"){
                        const requestTypeSpan = showRequestType(node.methodType);
                        addressSpan.appendChild(requestTypeSpan);
                    }else {
                        const requestTypeSpan = showRequestType("ws");
                        addressSpan.appendChild(requestTypeSpan);
                    }


                    addressSpan.appendChild(addressTextSpan);

                    const statusContainer = document.createElement('div');
                    statusContainer.classList.add('item-status');
                    const statusSpan = document.createElement('span');
                    statusSpan.style.backgroundColor = node.status.color;
                    statusSpan.style.borderRadius = '4px';
                    statusSpan.style.padding = '5px';
                    statusSpan.style.color = 'white';
                    statusSpan.textContent = node.status.name;
                    statusContainer.appendChild(statusSpan);

                    listItem.appendChild(nameSpan);
                    listItem.appendChild(addressSpan);
                    listItem.appendChild(statusContainer);

                    ulList.appendChild(listItem);
                    clickItem(listItem, node);
                });
            }
        }

        // 清空左侧目录树和右侧内容页
        // sidebarList.innerHTML = '';
        const ulList = document.getElementById('apiList');
        ulList.innerHTML = '';

        // 生成左侧目录树
        createDirectoryTree(jsonData.apiGroupList);

        console.log(apiArr)
        // 初始化右侧内容页，显示空的节点列表和表头
        showNodeList(apiArr, '所有接口');
    </script>

<script>
    /**
     * 详情的基础信息
     */
    const baseInfo = (detailBox,node)=>{
        let nameBox = document.createElement("div");
        nameBox.classList.add("itemBox")

        let apiNameElement = document.createElement("div");
        apiNameElement.id="apiNameElement"
        apiNameElement.textContent = node.name;

        let statusElement = document.createElement("div");
        statusElement.id="statusElement"
        statusElement.textContent = node.status.name;
        statusElement.style.backgroundColor = node.status.color;

        nameBox.appendChild(apiNameElement)
        nameBox.appendChild(statusElement)

        let pathBox = document.createElement("div");
        pathBox.classList.add("itemBox")

        let protocolType = document.createElement("div");
        protocolType.textContent =  node.protocolType.toUpperCase();
        protocolType.id="protocolType"

        let apiAddressElement = document.createElement("div");
        apiAddressElement.textContent =  node.path;

        pathBox.appendChild(protocolType)

        if(node.protocolType==="http"){
            let methodType =  showRequestType(node.methodType);
            pathBox.appendChild(methodType)
        }

        pathBox.appendChild(apiAddressElement)

        // 将详情内容添加到容器
        detailBox.appendChild(nameBox);
        detailBox.appendChild(pathBox);
    }
</script>
<script>

    /**
     * 表格
     * @param detailBox  添加到详情div里
     * @param dataList  数组项
     * @param type  header，query 无需数据类型
     */
    const createTable = (detailBox, dataList, type) => {

            // 创建表格容器
            const tableContainer = document.createElement("div");
            tableContainer.classList.add("table-container");

            // 创建无序列表元素
            const ulElement = document.createElement("ul");
            ulElement.classList.add("table");

            // 创建表头项
            const headerItem = document.createElement("li");
            headerItem.classList.add("table-header");
            headerItem.innerHTML = "<span>名称</span><span>示例值</span><span>必须</span>" + (type !== "header" && type !== "query" ? "<span>数据类型</span>" : "") + "<span>说明</span>";

            // 将表头项添加到无序列表
            ulElement.appendChild(headerItem);

            // 创建表格内容项
            dataList.forEach((rowData) => {
                const dataItem = document.createElement("li");
                dataItem.classList.add("table-data");
                dataItem.innerHTML = "<span>" + rowData.name + "</span><span>" + (rowData.value || "") + "</span><span>" + (rowData.required === 1 ? "是" : "否") + "</span>" + (type !== "header" && type !== "query" ? "<span>" + rowData.dataType + "</span>" : "") + "<span>" + (rowData.description || "") + "</span>";

                // 将表格内容项添加到无序列表
                ulElement.appendChild(dataItem);
            });

            // 将无序列表添加到表格容器
            tableContainer.appendChild(ulElement);

            // 将表格容器添加到页面
            detailBox.appendChild(tableContainer);
        }
</script>
<script>
    /**
     * textArea
     * 文本框
     */
    const createRaw =(detailBox,data)=>{
        let rawEle=document.createElement("textarea");
        rawEle.style.width="100%";
        rawEle.style.height="150px";
        rawEle.style.border="1px solid #e4e4e4";
        rawEle.style.outline="none";
        rawEle.textContent=data

        detailBox.appendChild(rawEle)
    }
</script>
<script>
    /**
     * 请求体
     */
    const requestBodyUnit = (detailBox,node)=>{
        let body = node.request.body;

        if(node.protocolType==="http"){
            switch (body.bodyType) {
                case "formdata":
                    if(body.formdata!==null&&body.formdata.length>0){
                        addTitle(detailBox,"Form-Data")
                        createTable(detailBox,body.formdata)
                    }
                    break;
                case "formUrlencodedUnit":
                    if(body.formUrlencodedUnit!==null&&body.formUrlencodedUnit.length>0){
                        addTitle(detailBox,"Form-Urlencoded")
                        createTable(detailBox,body.formUrlencodedUnit)
                    }
                    break;
                case "json":
                    addTitle(detailBox,"Json")
                    createRaw(detailBox,body.json.jsonText)
                    break;
                case "raw":
                    addTitle(detailBox,"Raw")
                    createRaw(detailBox,body.raw.raw)
                    break;
            }
        }

        if(node.protocolType==="ws"){
            switch (body.bodyType) {
                case "json":
                    addTitle(detailBox,"Json")
                    createRaw(detailBox,body.json.jsonText)
                    break;
                case "raw":
                    addTitle(detailBox,"Raw")
                    createRaw(detailBox,body.raw.raw)
                    break;
            }
        }
    }
</script>
<script>
    /**
     * 创建详情的请求信息
     */
    const requestInfo = (detailBox,node) =>{
        let title = document.createElement("div");
        title.classList.add("title-box")
        title.textContent="请求信息"

        let request = node.request


        // 将详情内容添加到容器
        detailBox.appendChild(title);

        //请求头
        if(request.header!==null&&request.header.length>0){
            addTitle(detailBox,"请求头")
            createTable(detailBox,request.header,"header")
        }
        //query
        if(request.query!==null&&request.query.length>0){
            addTitle(detailBox,"Query")
            createTable(detailBox,request.query,"query")
        }

        //请求体
        if(request.body!==null){
            addTitle(detailBox,"请求体")
            requestBodyUnit(detailBox,node)
        }

    }

</script>
<script>
    /**
     * 返回参数
     */
    const  responseInfo = (detailBox,response) =>{

        if(response?.header!==null&&response?.header.length>0){
            addTitle(detailBox,"响应头")
            createTable(detailBox,response.header,"header")
        }


        addTitle(detailBox,"返回参数")
        if(response.result!==null&&response.result.length>0){
            response.result.map(item=>{
                addTitle(detailBox,item.name)

                if(item.dataType==="json"){
                    createRaw(detailBox,item.jsonText)
                }else {
                    createRaw(detailBox,item.raw)
                }

            })
        }
    }
</script>





</body>
</html>
