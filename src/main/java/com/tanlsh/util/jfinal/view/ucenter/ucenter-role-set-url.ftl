<#include "/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/inc.ftl"/>

<input type="hidden" name="roleid" value="${roleid}"/>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<@bstree t=tree ck=true showurl=true height='500'/>
	</div>
</div>