<#include "/WEB-INF/classes/com/uikoo9/util/jfinal/view/common/inc.ftl"/>

<form class="form-horizontal" role="form">
	<input type="hidden" name="row.id" value="${(row.id)!}"/>
	<@bsinput title='角色名称' 	name='row.ucenter_role_name' 		value='${(row.ucenter_role_name)!}'/>
	<@bsinput title='角色跳转地址'	name='row.ucenter_role_login_url' 	value='${(row.ucenter_role_login_url)!}'/>
</form>