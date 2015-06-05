<#include "/WEB-INF/view/inc/inc.ftl"/>

<@html>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="cruddiv"></div>
		</div>
	</div>
	<@js>$(function(){qiao.crud.init();});</@js>
</@html>