(window.webpackJsonp=window.webpackJsonp||[]).push([[33],{1062:function(e,t,n){},1235:function(e,t,n){"use strict";n.r(t);var i=n(0),r=n.n(i),a=(n(671),n(662)),l=n(296),u=n(159),c=n(435),o=n(88),m=(n(1062),n(7)),s=n(695),d="D:\\a-dk-web\\thoughtware-postin-ui\\src\\setting\\system\\SysManagMenu.js";function N(){return(N=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n,i=arguments[t];for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(e[n]=i[n])}return e}).apply(this,arguments)}function b(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var i,r,a,l,u=[],c=!0,o=!1;try{if(a=(n=n.call(e)).next,0===t){if(Object(n)!==n)return;c=!1}else for(;!(c=(i=a.call(n)).done)&&(u.push(i.value),u.length!==t);c=!0);}catch(e){o=!0,r=e}finally{try{if(!c&&null!=n.return&&(l=n.return(),Object(l)!==l))return}finally{if(o)throw r}}return u}}(e,t)||function(e,t){var n;if(e)return"string"==typeof e?f(e,t):"Map"===(n="Object"===(n=Object.prototype.toString.call(e).slice(8,-1))&&e.constructor?e.constructor.name:n)||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?f(e,t):void 0}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function f(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,i=new Array(t);n<t;n++)i[n]=e[n];return i}function g(e){var t=e.settingMenu,n=e.route.routes,f=b(Object(i.useState)("/setting/systemRole"),2),g=f[0],h=f[1],y=(f=b(Object(i.useState)(),2))[0],v=f[1],E=JSON.parse(localStorage.getItem("authConfig")),w=window.location.hash.substr(1),k=(Object(i.useEffect)((function(){h(w),v(t)}),[w]),["/setting/orga","/setting/user","/setting/dir","/setting/userGroup"]),x=function(t){var n,i;if(!E.authType&&k.includes(t))return n=E.authServiceUrl,i=Object(m.getUser)().ticket,void window.open(n+"#"+t+"?ticket="+i,"_blank");e.history.push(t),h(t)},j=function(e){if(!E.authType&&k.includes(e))return r.a.createElement(s.a,{icon:"dakaixinyemian",className:"icon-s",__source:{fileName:d,lineNumber:58,columnNumber:24}})},S=(f=b(Object(i.useState)(["/setting/system"]),2))[0],C=f[1],O=function(e){return S.some((function(t){return t===e}))},M=function(e){O(e)?C(S.filter((function(t){return t!==e}))):C(S.concat(e))},A=function(e,t,n){return e.purviewCode?r.a.createElement(o.PrivilegeButton,{code:e.purviewCode,key:e.id,__source:{fileName:d,lineNumber:87,columnNumber:17}},r.a.createElement("li",{key:e.id,className:" orga-aside-li ".concat(e.id===g?"orga-aside-select":null),onClick:function(){return x(e.id)},style:{paddingLeft:"".concat(20*t,"px")},__source:{fileName:d,lineNumber:88,columnNumber:21}},r.a.createElement("div",{className:"aside-li",__source:{fileName:d,lineNumber:94,columnNumber:25}},r.a.createElement("div",{__source:{fileName:d,lineNumber:95,columnNumber:29}},n?r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:d,lineNumber:98,columnNumber:42}},r.a.createElement("use",{xlinkHref:"#icon-".concat(e.icon),__source:{fileName:d,lineNumber:99,columnNumber:45}})):null,e.title),j(e.id)))):r.a.createElement("li",{key:e.id,className:" orga-aside-li ".concat(e.id===g?"orga-aside-select":null),onClick:function(){return x(e.id)},style:{paddingLeft:"".concat(20*t,"px")},__source:{fileName:d,lineNumber:114,columnNumber:19}},r.a.createElement("div",{className:"aside-li",__source:{fileName:d,lineNumber:120,columnNumber:17}},r.a.createElement("div",{__source:{fileName:d,lineNumber:121,columnNumber:21}},n?r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:d,lineNumber:124,columnNumber:34}},r.a.createElement("use",{xlinkHref:"#icon-".concat(e.icon),__source:{fileName:d,lineNumber:125,columnNumber:37}})):null,e.title),j(e.id)))};return r.a.createElement(o.SystemNav,N({},e,{expandedTree:S,setExpandedTree:C,applicationRouters:y,outerPath:"/setting",notFoundPath:"/noaccess",__source:{fileName:d,lineNumber:230,columnNumber:9}}),r.a.createElement(a.a,{className:"sysmana-layout",__source:{fileName:d,lineNumber:238,columnNumber:13}},r.a.createElement(_,{className:"sysmana-sider",width:240,theme:"light",__source:{fileName:d,lineNumber:239,columnNumber:17}},r.a.createElement("div",{className:"thoughtware-orga-aside",__source:{fileName:d,lineNumber:244,columnNumber:21}},r.a.createElement("ul",{style:{padding:0},__source:{fileName:d,lineNumber:245,columnNumber:25}},(f=y)&&f.map((function(e){return e.children&&0<e.children.length?function e(t,n){var i=t.title,a=t.id,l=t.children,m=t.purviewCode;return t=t.icon,m?r.a.createElement(o.PrivilegeButton,{code:m,key:a,__source:{fileName:d,lineNumber:146,columnNumber:17}},r.a.createElement("li",{key:a,title:i,__source:{fileName:d,lineNumber:147,columnNumber:21}},r.a.createElement("div",{className:"orga-aside-item aside-li",onClick:function(){return M(a)},style:{paddingLeft:"".concat(20*n,"px")},__source:{fileName:d,lineNumber:148,columnNumber:25}},r.a.createElement("div",{className:"menu-name-icon",__source:{fileName:d,lineNumber:152,columnNumber:29}},r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:d,lineNumber:153,columnNumber:33}},r.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:d,lineNumber:154,columnNumber:37}})),r.a.createElement("span",{key:a,__source:{fileName:d,lineNumber:156,columnNumber:33}}," ",i)),r.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:d,lineNumber:158,columnNumber:29}},l?O(a)?r.a.createElement(u.a,{style:{fontSize:"12px"},__source:{fileName:d,lineNumber:162,columnNumber:51}}):r.a.createElement(c.a,{style:{fontSize:"12px"},__source:{fileName:d,lineNumber:163,columnNumber:51}}):"")),r.a.createElement("ul",{key:a,title:i,className:"orga-aside-ul ".concat(O(a)?null:"orga-aside-hidden"),__source:{fileName:d,lineNumber:169,columnNumber:25}},l&&l.map((function(t){return t.children&&t.children.length?e(t,1):A(t,1,!1)}))))):r.a.createElement("li",{key:a,title:i,__source:{fileName:d,lineNumber:184,columnNumber:17}},r.a.createElement("div",{className:"orga-aside-item aside-li",onClick:function(){return M(a)},style:{paddingLeft:"".concat(20*n,"px")},__source:{fileName:d,lineNumber:185,columnNumber:21}},r.a.createElement("div",{className:"menu-name-icon",__source:{fileName:d,lineNumber:189,columnNumber:25}},r.a.createElement("svg",{style:{width:16,height:16,margin:"0 5px 0 0"},"aria-hidden":"true",__source:{fileName:d,lineNumber:190,columnNumber:29}},r.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:d,lineNumber:191,columnNumber:33}})),r.a.createElement("span",{key:a,__source:{fileName:d,lineNumber:193,columnNumber:29}},i)),r.a.createElement("div",{className:"orga-aside-item-icon",__source:{fileName:d,lineNumber:195,columnNumber:25}},l?O(a)?r.a.createElement(u.a,{style:{fontSize:"12px"},__source:{fileName:d,lineNumber:199,columnNumber:47}}):r.a.createElement(c.a,{style:{fontSize:"12px"},__source:{fileName:d,lineNumber:200,columnNumber:47}}):"")),r.a.createElement("ul",{key:a,title:i,className:"orga-aside-ul ".concat(O(a)?null:"orga-aside-hidden"),__source:{fileName:d,lineNumber:206,columnNumber:21}},l&&l.map((function(t){return t.children&&t.children.length?e(t,1):A(t,1,!1)}))))}(e):A(e,null,!0)}))))),r.a.createElement(p,{className:"sysmana-content",__source:{fileName:d,lineNumber:252,columnNumber:17}},Object(l.a)(n))))}var _=a.a.Sider,p=a.a.Content;function h(){return(h=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n,i=arguments[t];for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(e[n]=i[n])}return e}).apply(this,arguments)}t.default=function(e){var t=[{title:"用户与权限",icon:"chengyuan",id:"accountMember",children:[{title:"部门",id:"/setting/orga",purviewCode:"orga"},{title:"用户",id:"/setting/user",purviewCode:"user"},{title:"用户目录",id:"/setting/dir",purviewCode:"user_dir"},{title:"用户组",id:"/setting/userGroup"},{title:"权限",icon:"jiaosequanxian",id:"/setting/systemRole"}]},{title:"消息",icon:"xiaoxi",id:"/setting",children:[{title:"消息发送方式",id:"/setting/messageSendType",purviewCode:"MSG_SendType"},{title:"消息通知方案",id:"/setting/message-notice",purviewCode:"MSG_Notice"}]},{title:"插件",icon:"plugin",id:"/setting/plugin",purviewCode:"plugin"},{title:"安全",icon:"anquan",id:"/setting/log",purviewCode:"security",children:[{title:"操作日志",id:"/setting/log",purviewCode:"log"},{title:"备份与恢复",id:"/setting/backups"}]},{title:"应用",icon:"xukezheng",id:"/setting/version",children:[{title:"版本与许可证",id:"/setting/version"},{title:"应用访问权限",id:"/setting/product-auth"}]}];return r.a.createElement(g,h({settingMenu:function(){try{return[].concat(t)}catch(e){return[].concat(t)}}},e,{__source:{fileName:"D:\\a-dk-web\\thoughtware-postin-ui\\src\\setting\\system\\SystemContent.js",lineNumber:153,columnNumber:9}}))}},435:function(e,t,n){"use strict";function i(e,t){return a.createElement(u.a,Object(r.a)(Object(r.a)({},e),{},{ref:t,icon:l}))}var r=n(3),a=n(0),l={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M890.5 755.3L537.9 269.2c-12.8-17.6-39-17.6-51.7 0L133.5 755.3A8 8 0 00140 768h75c5.1 0 9.9-2.5 12.9-6.6L512 369.8l284.1 391.6c3 4.1 7.8 6.6 12.9 6.6h75c6.5 0 10.3-7.4 6.5-12.7z"}}]},name:"up",theme:"outlined"},u=n(13);i.displayName="UpOutlined",t.a=a.forwardRef(i)},695:function(e,t,n){"use strict";var i=n(0),r=n.n(i),a="D:\\a-dk-web\\thoughtware-postin-ui\\src\\common\\IconCommon.js";t.a=function(e){var t=e.icon,n=e.style,i=e.className;e=e.onClick;return r.a.createElement("svg",{style:n,className:i,"aria-hidden":"true",onClick:e,__source:{fileName:a,lineNumber:10,columnNumber:9}},r.a.createElement("use",{xlinkHref:"#icon-".concat(t),__source:{fileName:a,lineNumber:11,columnNumber:13}}))}}}]);