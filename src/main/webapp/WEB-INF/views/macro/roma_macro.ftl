
<#-- adminUser 的权限判断
    ifAnyGranted: 要被判断的权限限，多个权限项可用 ,(逗号) 分隔
-->
<#macro securityAuthorize ifAnyGranted>
    <#assign hasPermission = Static["cn.yr.chile.common.helper.SiteConfigHelper"].securityAuthorize(ifAnyGranted)>
    <#if hasPermission>
        <#nested>
    </#if>
</#macro>