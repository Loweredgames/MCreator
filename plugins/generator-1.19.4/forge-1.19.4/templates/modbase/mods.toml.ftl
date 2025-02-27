modLoader="javafml"
loaderVersion="[45,)"
license="${settings.getLicense()}"

[[mods]]
modId="${settings.getModID()}"
version="${settings.getCleanVersion()}"
displayName="${settings.getModName()}"
<#if settings.getUpdateURL()?has_content>
updateJSONURL="${settings.getUpdateURL()}"
</#if>
<#if settings.getWebsiteURL()?has_content>
displayURL="${settings.getWebsiteURL()}"
</#if>
<#if settings.getModPicture()?has_content>
logoFile="logo.png"
</#if>
<#if settings.getCredits()?has_content>
credits="${settings.getCredits()}"
</#if>
<#if settings.getAuthor()?has_content>
authors="${settings.getAuthor()}"
</#if>
<#if settings.getDescription()?has_content>
description="${settings.getDescription()}"
</#if>

[[dependencies.${settings.getModID()}]]
    modId="minecraft"
    mandatory=true
    versionRange="[${generator.getGeneratorMinecraftVersion()}]"
    ordering="NONE"
    side="BOTH"

<#if !settings.isDisableForgeVersionCheck()>
[[dependencies.${settings.getModID()}]]
    modId="forge"
    mandatory=true
    versionRange="[${generator.getGeneratorBuildFileVersion()}]"
    ordering="NONE"
    side="BOTH"
</#if>

<#list settings.getRequiredMods() as e>
[[dependencies.${settings.getModID()}]]
    modId="${e}"
    mandatory=true
    versionRange="[0,)"
    ordering="NONE"
    side="BOTH"
</#list>

<#list settings.getDependencies() as e>
[[dependencies.${settings.getModID()}]]
    modId="${e}"
    mandatory=false
    versionRange="[0,)"
    ordering="NONE"
    side="BOTH"
</#list>

<#list settings.getDependants() as e>
[[dependencies.${settings.getModID()}]]
    modId="${e}"
    mandatory=false
    versionRange="[0,)"
    ordering="NONE"
    side="BOTH"
</#list>