<#include "/WEB-INF/classes/com/uikoo9/util/jfinal/view/common/inc.ftl"/>

<form class="form-horizontal" role="form">
	<input type="hidden" name="row.id" value="${(row.id)!}"/>
	<#if (row.id)??>
		<@bsinput title='用户名' 	 name='row.ucenter_user_name' 	value='${(row.ucenter_user_name)!}' dis=true/>
	<#else>
		<@bsinput title='用户名' 	 name='row.ucenter_user_name' 	value='${(row.ucenter_user_name)!}' />
		<@bsinput title='用户密码' name='row.ucenter_user_key' 	value='${(row.ucenter_user_key)!}'/>
	</#if>
	<@bsinput title='用户类型' input=false>
		<@bsradios name='row.ucenter_user_type' ck='${(row.ucenter_user_type)!"010102"}' list=usertypes/>
	</@bsinput>
</form>