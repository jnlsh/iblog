<#include "/WEB-INF/view/inc/inc.ftl"/>

<@html>
	<div class="container">
		<div class="row">
			<div class="col-xs-10 col-sm-9 col-md-9 col-lg-9">
				<#list blogs as blog>
					<blockquote style="border-left-color:#5bc0de;">
					  <p><a href="${base}/blog/detail/${blog.blog_article_code}" target="_blank">${blog.blog_article_title}</a></p>
					  <footer>阅读<strong>${blog.blog_article_read_times}</strong>次，创作于<strong>${blog.cdate}</strong><#if user??>，<a href="${base}/blog/edit/${blog.id}">编辑</a></#if></footer>
					</blockquote>
				</#list>
			</div>
			<div class="col-xs-2 col-sm-3 col-md-3 col-lg-3">
				<div class="list-group" style="position:fixed;top:70px;margin-right:20px;">
					<#if blogTypeId??>
						<a href="${base}/blog/list" class="list-group-item">所有文章</a>
					<#else>
						<a href="${base}/blog/list" class="list-group-item active">所有文章</a>
					</#if>
					<#list blogTypes as type>
						<a href="${base}/blog/list/${type.id}" class="list-group-item <#if blogTypeId?? && blogTypeId == type.id>active</#if>">${type.blog_type_name}</a>
					</#list>
				</div>
			</div>
		</div>
	</div>
	<@backtotop/>
</@html>