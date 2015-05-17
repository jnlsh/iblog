<#-------------------------------------------------------------------------------------------- 公用 -->
<#-- html -->
<#macro html t='' key='' desc='' qmask=2>
	<!DOCTYPE html>
	<html>
		<@head t=t key=key desc=desc/>
		<@bsbody qmask=qmask>
			<#nested>
		</@bsbody>
	</html>
</#macro>

<#-- head -->
<#macro head t='' key='' desc=''>
	<head>
		<!-- 编码 -->
		<meta charset="UTF-8" />
		
		<!-- ie -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1"/>
		
		<!-- for mobile -->
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<!-- for search-->
		<meta name="keywords" content="<#if key != ''>${key}<#else>${title}</#if>"/>
		<meta name="description" content="<#if desc != ''>${desc}<#else>${title}</#if>"/>
		<meta name="author" contect="qiaowenbin"/>
		<meta name="robots" contect="all"/>
		
		<!-- title -->
		<title><#if t != ''>${t}<#else>${title}</#if></title>
		
		<!-- favicon.ico -->
		<link href="${base}/favicon.ico" type="image/x-icon" rel="bookmark"/> 
		<link href="${base}/favicon.ico" type="image/x-icon" rel="icon"/> 
		<link href="${base}/favicon.ico" type="image/x-icon" rel="shortcut icon"/> 
		
		<!-- fill -->
		<style type="text/css">
			html,body{height: 100%;}
			#wrap{min-height: 100%;height: auto !important;height: 100%;margin: 0 auto -60px;}
			#push,#footer{height: 60px;}
			#footer{background-color: #f5f5f5;text-align:center;}
			@media ( max-width : 767px){#footer {margin-left:-20px;padding-left:20px;}}
		</style>

		<!-- base -->
		<script type="text/javascript">var base = '${base}';</script>
		
		<!-- jquery -->
		<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
		
		<!-- bootstrap -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.2.0/css/bootstrap.min.css">
		<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<!--[if lt IE 9]>
		<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!-- qiao.js -->
		<script type="text/javascript" src="${base}/WUI/qiao.js"></script>
		
		<!-- user js -->
		<#if user??>
			<script type="text/javascript">qiao.modifypwd.init();</script>
		<#else>
			<script type="text/javascript">qiao.login.init();</script>
		</#if>
		
		<#if title == 'uikoo9.com' && !devMode>
			<!-- baidu tongji -->
			<script>
				var _hmt = _hmt || [];
				(function() {
					var hm = document.createElement("script");
					hm.src = "//hm.baidu.com/hm.js?7117ec605df94953bcc641e344d24b95";
					var s = document.getElementsByTagName("script")[0]; 
					s.parentNode.insertBefore(hm, s);
				})();
			</script>
		</#if>
		
		<#nested>
	</head>
</#macro>

<#-- js -->
<#macro js web=false>
	<#if web><script type="text/javascript" src="${base}/WUI/web/web.js"></script></#if>
	
	<script type="text/javascript">
		<#nested>
	</script>
</#macro>

<#-- jsfile -->
<#macro jsfile js>
	<#if js == 'jquery'>
		<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
	</#if>
	<#if js == 'cookie'>
		<script type="text/javascript" src="http://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	</#if>
	<#if js == 'holder'>
		<script type="text/javascript" src="http://cdn.staticfile.org/holder/2.4.1/holder.js"></script>
	</#if>
	<#if js == 'bstro'>
		<link rel="stylesheet" href="http://uikoo9.qiniudn.com/@/js/bootstro/bootstro.min.css">
		<script type="text/javascript" src="http://uikoo9.qiniudn.com/@/js/bootstro/bootstro.min.js"></script>
	</#if>
	<#if js == 'qrcode'>
		<script type="text/javascript" src="http://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
		<script type="text/javascript">
			function qcodetochar(str){
			    var out, i, len, c;
			    out = "";
			    len = str.length;
			    for (i = 0; i < len; i++) {
			        c = str.charCodeAt(i);
			        if ((c >= 0x0001) && (c <= 0x007F)) {
			            out += str.charAt(i);
			        } else if (c > 0x07FF) {
			            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			        } else {
			            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			        }
			    }
			    return out;
			};
		</script>
	</#if>
</#macro>

<#-- uikoo9 -->
<#macro uikoo9>
	<li><a href="${base}/donate" target="_blank">捐助</a></li>
	<li><a href="${base}/me" target="_blank">关于我</a></li>
</#macro>

<#-------------------------------------------------------------------------------------------- bootstrap -->
<#-- bsbody -->
<#macro bsbody style='' class='' qmask=2 head=true foot=true>
<body <#if style != ''>style="${style}"</#if> <#if class != ''>class="${class}"</#if>>
	<#if qmask == 1>
		<script type="text/javascript" src="http://uikoo9.qiniudn.com/@/js/qmask/qmask-1.0.min.js"></script>
	</#if>
	<#if qmask == 2>
		<link rel="stylesheet" href="http://uikoo9.qiniudn.com/@/js/qmask/qmask-2.0.min.css">
		<script type="text/javascript" src="http://uikoo9.qiniudn.com/@/js/qmask/qmask-2.0.min.js"></script>
	</#if>
	<#if qmask == 3>
		<link rel="stylesheet" href="http://uikoo9.qiniudn.com/@/js/qmask/qmask-3.0.css">
		<script type="text/javascript" src="http://uikoo9.qiniudn.com/@/js/qmask/qmask-3.0.js"></script>
		<script type="text/javascript">qmask.show();</script>
	</#if>
	<#if qmask != 0>
		<script type="text/javascript">qiao.ajaxinit();</script>
	</#if>
	
	<div id="wrap">
		<#if head><@bshead/></#if>
	
		<#nested>

		<div id="push"></div>
	</div>
	
	<#if foot><@bsfoot/></#if>
</body>
</#macro>

<#-- bsfoot -->
<#macro bsfoot>
    <div id="footer">
		<div class="container">
			<p class="text-muted" style="margin:20px 0;">
				<a target="_blank" href="http://uikoo9.com/">uikoo9.com</a>&nbsp;&nbsp;&nbsp;
				<a target="_blank" href="http://www.miibeian.gov.cn/">京ICP备14036391号</a>
			</p>
		</div>
	</div>
</#macro>

<#-- bsmenu -->
<#macro bsmenu menus=''>
	<#if menus != ''>
		<div class="list-group">
			<#list menus as menu>
				<a href="javascript:void(0);" class="list-group-item menus" data="url:${menu.ucenter_menu_url};">${menu.ucenter_menu_title}</a>
			</#list>
		</div>
	</#if>
</#macro>

<#-- bslun -->
<#macro bslun pics...>
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin-bottom:20px;">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<#list pics as pic>
				<li data-target="#carousel-example-generic" data-slide-to="${pic_index}" <#if pic_index == 0>class="active"</#if>></li>
			</#list>
		</ol>
		
		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<#list pics as pic>
				<div class="item <#if pic_index == 0>active</#if>">
					<img src="http://uikoo9.qiniudn.com/@/img/${pic}" alt="${pic}">
				</div>
			</#list>
		</div>
		
		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</#macro>

<#-- bsnail -->
<#macro bsnail href='' title='' src='' data='' desc='' hasword=true class='col-xs-12 col-sm-12 col-md-4 col-lg-4'>
	<div class="${class}" style="text-align:center;">
		<div class="thumbnail">
			<a href="${href}" title="${title}" target="_blank">
				<#if src != ''>
					<img src="${src}">
				</#if>
				<#if data!=''>
					<img data-src="${data}">
				</#if>
			</a>
			<#if hasword>
				<div class="caption">
					<h2><a href="${href}">${title}</a></h2>
					<#if desc != ''><p>${desc}</p></#if>
					<p><#nested></p>
				</div>
			</#if>
		</div>
	</div>
</#macro>

<#-- bsbutton -->
<#macro bsbutton dis=false type="primary" href='javascript:void(0);' blank=false pos='left' size='' icon='' class='' style='' data='' title='' id=''>
	<a 	<#if id!=''>id="${id}"</#if> 
		<#if data!=''>data="${data}"</#if>
		<#if dis>disabled="disabled"</#if> 
		<#if blank>target="_blank"</#if>
		href="${href}" 
		class="btn btn-${type} <#if size != ''>btn-${size}</#if> <#if class!=''>${class}</#if>" 
		<#if style!=''> style="${style}"</#if>
		<#if title!=''> title="${title}"</#if>
	>
		<#if pos == 'left'><@bsicon icon=icon></@bsicon></#if>
		<#nested>
		<#if pos == 'right'><@bsicon icon=icon></@bsicon></#if>
	</a>
</#macro>

<#-- bsicon -->
<#macro bsicon icon=''>
	<#if icon != ''>
		<span class="glyphicon glyphicon-${icon}"></span>
	</#if>
</#macro>

<#-- bspro -->
<#macro bspro rate='0'>
	<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100" style="width:${rate}%;">${rate}%</div>
	</div>
</#macro>

<#-- bsul -->
<#macro bsul lis>
	<#if list??>
		<ul class="list-group">
			<#list lis as li>
				<li class="list-group-item">${li.text}</li>
			</#list>
		</ul>
	</#if>
</#macro>

<#-- bspanel -->
<#macro bspanel type='default' title='' style='' class='' fit=true id=''>
	<div class="panel panel-${type}<#if class!=''> ${class}</#if>" style="<#if fit>height:100%;</#if><#if style != ''>${style}</#if>">
		<#if title!=''><div class="panel-heading">${title}</div></#if>
		<div class="panel-body" <#if id!=''>id="${id}"</#if>>
			<#nested>
		</div>
	</div>
</#macro>

<#-- bsrow -->
<#macro bsrow title='title' desc='desc'>
	<div class="row">
		<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">${title}：</div>
		<div class="col-xs-12 col-sm-9 col-md-9 col-lg-9"><#nested></div>
	</div>
</#macro>

<#-- bstable -->
<#macro bstable url=''>
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover" <#if url!=''>data="url:${url}"</#if>>
			<#nested>
		</table>
	</div>
</#macro>

<#-- bsform -->
<#macro bsform h=false l=false class='' data='' id='' action=''>
	<form class="form<#if h>-horizontal</#if><#if l>-inline</#if> <#if class!=''>${class}</#if>" 
		role="form" method="post" 
		<#if data!=''>data="${data}"</#if> 
		<#if id!=''>id="${id}"</#if> 
		<#if action!=''>action="${action}"</#if>
	>
		<#nested>
	</form>
</#macro>

<#-- bsinput -->
<#macro bsinput type='text' title='title' name='name' value='' lg=false col=true input=true dis=false>
	<#if input>
		<div class="form-group">
			<label for="id-${name}" class="control-label <#if col>col-xs-12 col-sm-2 col-md-2 col-lg-2</#if>">${title}</label>
			<div class="<#if col>col-xs-12 col-sm-10 col-md-10 col-lg-10</#if>">
				<input type="${type}" name="${name}" value="${value}" placeholder="${title}" class="form-control <#if lg>input-lg</#if>" <#if dis>disabled</#if> id="id-${name}">
			</div>
		</div>
	<#else>
		<div class="form-group">
			<label class="control-label <#if col>col-xs-12 col-sm-2 col-md-2 col-lg-2</#if>">${title}</label>
			<div class="<#if col>col-xs-12 col-sm-10 col-md-10 col-lg-10</#if>">
				<#nested>
			</div>
		</div>
	</#if>
</#macro>

<#-- 
bsradios 
	like this:
	<@bsradios name='row.pro_type' ck='${(row.pro_type)!"020101"}' list=types/>
-->
<#macro bsradios list name ck>
	<#list list as item>
		<label class="radio-inline">
			<input type="radio" name="${name}" value="${item.value}" <#if item.value == ck>checked</#if>>${item.text}
		</label>
	</#list>
</#macro>

<#-- 
bsselects 
	like this:
	<@bsselects name='row.pro_type' ck='${(row.pro_type)!"020101"}' list=types/>
-->
<#macro bsselects list name ck>
	<select class="form-control" name="${name}">
		<#list list as item>
			<option value="${item.value}" <#if item.value == ck>selected</#if>>${item.text}</option>
		</#list>
	</select>
</#macro>

<#-- bstree -->
<#macro bstree t ck=false edit=false showurl=true editurl='/ucenter/menu' height='600'>
<#if t??>
	<div class="panel panel-info">
		<div class="panel-body" style="height:${height}px;overflow-y:auto;">
			<ul class="nav nav-list sidenav" id="treeul" data="url:${editurl};">
				<@bssubtree tree=t ck=ck edit=edit showurl=showurl/>
			</ul>
		</div>
	</div>
	<script type="text/javascript">qiao.bs.tree.init();</script>
</#if>
</#macro>

<#-- bssubtree -->
<#macro bssubtree tree open=true ck=false edit=false showurl=true>
<#if tree??>
	<li>
		<a href="javascript:void(0);" data="id:${tree.id};url:${tree.url};">
			<@bsicon icon='minus'/>
			<#if ck><input type="checkbox" class="treecheckbox" <#if tree.checked>checked</#if>/></#if>
			${tree.text}<#if showurl>(${tree.url})</#if>
			<#if edit>
				<span class="label label-primary bstreeadd">添加子菜单</span>
				<span class="label label-primary bstreeedit">修改</span>
				<span class="label label-danger	 bstreedel">删除</span>
			</#if>
		</a>
		<#if tree.children?? && tree.children?size gt 0>
			<ul style="padding-left:20px;" id="treeid_${tree.id}" class="nav collapse <#if open>in</#if>">
				<#list tree.children as subtree>
					<@bssubtree tree=subtree ck=ck edit=edit showurl=showurl/>
				</#list>
			</ul>
		</#if>
	</li>
</#if>
</#macro>

<#-- bspage -->
<#macro bspage page pagesizelist=[50,100]>
	<#assign pagenum = page.totalPage>
	<#if pagenum gt 1>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
				<#if pagenum lt 5>
					<ul class="pagination" style="margin:0;">
						<li <#if page.pageNumber == 1>class="disabled"</#if>>
							<@bsbutton icon='step-backward' class='crud crudfirst'/>
						</li>
						<li <#if page.pageNumber == 1>class="disabled"</#if>>
							<@bsbutton icon='chevron-left' class='crud crudprev'/>
						</li>
						<#list 1..pagenum as pn>
							<li <#if page.pageNumber == pn>class="active"</#if>>
								<a href="javascript:void(0);" class="cruda">${pn}</a>
							</li>
						</#list>
						<li <#if page.pageNumber == pagenum>class="disabled"</#if>>
							<@bsbutton icon='chevron-right' class='crud crudnext'/>
						</li>
						<li	<#if page.pageNumber == pagenum>class="disabled"</#if>>
							<@bsbutton icon='step-forward' class='crud crudlast' data='page:${pagenum};'/>
						</li>
						<li class="disabled">
							<a href="javascript:void(0);">共${pagenum}页，${page.totalRow}条</a>
						</li>
					</ul>
				<#else>
					<ul class="pagination" style="margin:0;">
						<li <#if page.pageNumber == 1>class="disabled"</#if>>
							<@bsbutton icon='step-backward' class='crud crudfirst'/>
						</li>
						<li <#if page.pageNumber == 1>class="disabled"</#if>>
							<@bsbutton icon='chevron-left' class='crud crudprev'/>
						</li>
						<li <#if page.pageNumber == 1>class="active"</#if>>
							<a href="javascript:void(0);" class="cruda">1</a>
						</li>
						<li class="disabled">
							<a href="javascript:void(0);" class="cruda">...</a>
						</li>
						<#list 1..pagenum as pn>
							<#if page.pageNumber==1>
								<#if (page.pageNumber+1 == pn) || (page.pageNumber+2 == pn) || (page.pageNumber+3 == pn)>
									<li <#if page.pageNumber == pn>class="active"</#if>>
										<a href="javascript:void(0);" class="cruda">${pn}</a>
									</li>
								</#if>
							</#if>
							<#if page.pageNumber==2>
								<#if (page.pageNumber == pn) || (page.pageNumber+1 == pn) || (page.pageNumber+2 == pn)>
									<li <#if page.pageNumber == pn>class="active"</#if>>
										<a href="javascript:void(0);" class="cruda">${pn}</a>
									</li>
								</#if>
							</#if>
							<#if page.pageNumber gt 2 && page.pageNumber+2 <= pagenum>
								<#if (page.pageNumber-1 == pn) || (page.pageNumber == pn) || (page.pageNumber+1 == pn)>
									<li <#if page.pageNumber == pn>class="active"</#if>>
										<a href="javascript:void(0);" class="cruda">${pn}</a>
									</li>
								</#if>
							</#if>
							<#if page.pageNumber==pagenum-1>
								<#if (page.pageNumber == pn) || (page.pageNumber-1 == pn) || (page.pageNumber-2 == pn)>
									<li <#if page.pageNumber == pn>class="active"</#if>>
										<a href="javascript:void(0);" class="cruda">${pn}</a>
									</li>
								</#if>
							</#if>
							<#if page.pageNumber==pagenum>
								<#if (page.pageNumber-1 == pn) || (page.pageNumber-2 == pn) || (page.pageNumber-3 == pn)>
									<li <#if page.pageNumber == pn>class="active"</#if>>
										<a href="javascript:void(0);" class="cruda">${pn}</a>
									</li>
								</#if>
							</#if>
						</#list>
						<li class="disabled">
							<a href="javascript:void(0);" class="cruda">...</a>
						</li>
						<li <#if page.pageNumber == pagenum>class="active"</#if>>
							<a href="javascript:void(0);" class="cruda">${pagenum}</a>
						</li>
						<li <#if page.pageNumber == pagenum>class="disabled"</#if>>
							<@bsbutton icon='chevron-right' class='crud crudnext'/>
						</li>
						<li	<#if page.pageNumber == pagenum>class="disabled"</#if>>
							<@bsbutton icon='step-forward' class='crud crudlast' data='page:${pagenum};'/>
						</li>
						<li class="disabled">
							<a href="javascript:void(0);">共${pagenum}页，${page.totalRow}条</a>
						</li>
					</ul>
				</#if>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="input-group">
					<div class="input-group-addon">每页</div>
					<select class="form-control pagesize" name="pagesize">
						<option value="10" <#if page.pageSize == 10>selected</#if>>10条</option>
						<#list pagesizelist as l>
							<option value="${l}" <#if page.pageSize == l>selected</#if>>${l}条</option>
						</#list>
					</select>
				</div>
			</div>
		</div>
	</#if>
</#macro>

<#-- bslist -->
<#macro bslist btn=true qpage=''>
	<@bspanel>
		<#if btn>
			<p>
				<@bsbutton class='addBtn' icon='plus'>添加</@bsbutton>
				<@bsbutton class='delBtn' icon='remove'>删除</@bsbutton>
				<@bsbutton class='queBtn' icon='search'>查询</@bsbutton>
				<@bsbutton class='relBtn' icon='repeat'>重置</@bsbutton>
			</p>
		</#if>
		<p><strong>${(qpage.str)!}</strong></p>
		<#nested>
		<@bspage page=qpage/>
	</@bspanel>
</#macro>

<#-------------------------------------------------------------------------------------------- 公用插件 -->
<#-- backtotop -->
<#macro backtotop>
	<div id="back-to-top" style="font-size:60px;color:#00BEFF;z-index:1100;cursor:pointer;position:fixed;right:40px;bottom:15px;"><span class="glyphicon glyphicon-plane"></span></div>
	<script type="text/javascript">$(function(){$("#back-to-top").qtotop();});</script>
</#macro>

<#-- ueditor -->
<#macro ueditor js=true init=true parseid=''>
	<#if js>
		<script type="text/javascript" charset="utf-8" src="${base}/WUI/ueditor-min-1.4.3/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="${base}/WUI/ueditor-min-1.4.3/ueditor.all.min.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="${base}/WUI/ueditor-min-1.4.3/lang/zh-cn/zh-cn.js"></script>
	    <script type="text/javascript" charset="utf-8">
		    <#if init>
				var ue = qiao.ue('ueditor');
		    <#else>
		    	<#nested>
		    </#if>
	    </script>
	</#if>
    
    <#if parseid != ''>
    	<style type="text/css">${parseid} .container:before, ${parseid} .container:after{content : none;}</style>
	    <script type="text/javascript" charset="utf-8" src="${base}/WUI/ueditor-min-1.4.3/ueditor.parse.min.js"></script>
	    <script type="text/javascript" charset="utf-8">uParse('${parseid}', {rootPath: base+'/WUI/ueditor-min-1.4.3/'});</script>
    </#if>
</#macro>

<#-- bdshare -->
<#macro bdshare>
	<hr/>
	<div class="bdsharebuttonbox" style="margin-top:10px;margin-bottom:10px;">
		<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
		<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
		<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
		<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
		<a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a>
		<a href="#" class="bds_more" data-cmd="more"></a>
	</div>
	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</#macro>

<#-- 
录音组件 
	若上传成功后会把上传的文件id放到qrecordId这个里面，
-->
<#macro qrecord style=''>
	<script type="text/javascript" src="${base}/WUI/qrecord/js/swfobject.js"></script>
	<script type="text/javascript" src="${base}/WUI/qrecord/js/recorder.js"></script>
	<script type="text/javascript" src="${base}/WUI/qrecord/js/main.js"></script>
	<link rel="stylesheet" href="${base}/WUI/qrecord/style.css">
	<div class="qcontainer" id="myRecordDiv" <#if style!=''>style="${style}"</#if>>
		<div id="recorder-audio" class="control_panel idle">
			<button class="record_button" onclick="FWRecorder.record('audio', 'audio.wav');" title="Record">
				<img src="${base}/js/_record/images/record.png" alt="Record" />
			</button>
			<button class="stop_recording_button" onclick="FWRecorder.stopRecording('audio');" title="Stop Recording">
				<img src="${base}/js/_record/images/stop.png" alt="Stop Recording" />
			</button>
			<button class="play_button" onclick="FWRecorder.playBack('audio');" title="Play">
				<img src="${base}/js/_record/images/play.png" alt="Play" />
			</button>
			<button class="pause_playing_button" onclick="FWRecorder.pausePlayBack('audio');" title="Pause Playing">
				<img src="${base}/js/_record/images/pause.png" alt="Pause Playing" />
			</button>
			<button class="stop_playing_button" onclick="FWRecorder.stopPlayBack();" title="Stop Playing">
				<img src="${base}/js/_record/images/stop.png" alt="Stop Playing" />
			</button>
			<div class="level"></div>
		</div>
	
		<div class="details">
			<button class="show_level" onclick="FWRecorder.observeLevel();">显示声波</button>
			<button class="hide_level" onclick="FWRecorder.stopObservingLevel();" style="display: none;">隐藏声波</button>
			<button class="show_settings" onclick="microphonePermission()">麦克风权限</button>
			<span id="save_button" style="display:inline-block;"> 
				<span id="flashcontent">
					<p>您的浏览器必须支持Javascript,而且安装了Flash播放器！</p> 
				</span> 
			</span>
			<div id="status">录音状态。。。</div>
			<div>录音时长：<span id="duration"></span></div>
			<div>上传状态：<span id="upload_status"></span></div>
			<input type="hidden" id="qrecordId"/>
		</div>
	
		<form id="uploadForm" name="uploadForm" action="${base}/common/record/upload">
			<input name="authenticity_token" value="xxxxx" type="hidden">
			<input name="upload_file[parent_id]" value="1" type="hidden">
			<input name="format" value="json" type="hidden">
		</form>
	</div>
</#macro>

<#-- 
column 
这个布局相关，因为涉及到四种设备，所以还是手动写比较好
col-xs-12 col-sm-12 col-md-12 col-lg-12
-->