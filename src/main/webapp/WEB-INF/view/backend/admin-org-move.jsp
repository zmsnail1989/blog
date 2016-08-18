<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="https://git.oschina.net/cool_snail/blog.git"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/lib/html5.js"></script>
<script type="text/javascript" src="/lib/respond.min.js"></script>
<script type="text/javascript" src="/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="/static/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/static/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css">
    <style>
        ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:200px;overflow-y:scroll;overflow-x:auto;}
    </style>
</head>
<body>

    <form id="form" class="form form-horizontal" method="post">
    
    	<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">源节点名称：</label>
			<div class="formControls col-xs-5 col-sm-7">
				 ${source.name}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">目标节点名称：</label>
			<div class="formControls col-xs-5 col-sm-7">
				<input type="text" class="input-text" value="" placeholder=""
					id="targetName" name="targetName">
            <input type="hidden" id="targetId" name="targetId"/>
			</div>
			<div class="col-xs-3 col-sm-3"><a id="menuBtn" href="#"><i style="font-size: 20px" class="Hui-iconfont">&#xe667;</i></a></div>
		</div>
		<div class="row cl">
				<div class="col-5 col-offset-2">
					<button type="submit" class="btn btn-primary radius">移动</button>
				</div>
			</div>
    </form>

    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>

    <script src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
    <script>
        $(function () {
            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick
                }
            };

            var zNodes =[
                <c:forEach items="${targetList}" var="o">
                { id:${o.id}, pId:${o.parentId}, name:"${o.name}", open:${o.rootNode}},
                </c:forEach>
            ];

            function onClick(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree"),
                        nodes = zTree.getSelectedNodes(),
                        id = "",
                        name = "";
                nodes.sort(function compare(a,b){return a.id-b.id;});
                for (var i=0, l=nodes.length; i<l; i++) {
                    id += nodes[i].id + ",";
                    name += nodes[i].name + ",";
                }
                if (id.length > 0 ) id = id.substring(0, id.length-1);
                if (name.length > 0 ) name = name.substring(0, name.length-1);
                $("#targetId").val(id);
                $("#targetName").val(name);
                hideMenu();
            }

            function showMenu() {
                var cityObj = $("#targetName");
                var cityOffset = $("#targetName").offset();
                $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

                $("body").bind("mousedown", onBodyDown);
            }
            function hideMenu() {
                $("#menuContent").fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);
            }
            function onBodyDown(event) {
                if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                    hideMenu();
                }
            }

            $.fn.zTree.init($("#tree"), setting, zNodes);
            $("#menuBtn").click(showMenu);
        });
    </script>
</body>
</html>