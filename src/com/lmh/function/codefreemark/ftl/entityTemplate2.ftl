package ${basePrefixPackage}.${modalNameLower}.entity;

import org.joda.time.DateTime;

/**
 * ${entityNameZh}实体类
 */
public class ${entityName} {
	
<#list fieldList as item>
	<#if item.type = 'Integer'>
	/**
	 * ${item.nameDesc}
	 */
	private Integer ${item.name} = ${(item.value??)?string(item.value,'0')};
	
	<#elseif item.type == 'DateTime'>
	/**
	 * ${item.nameDesc}
	 */
	private DateTime ${item.name};
	
	<#else>
	/**
	 * ${item.nameDesc}
	 */
	private String ${item.name} = ${(item.value??)?string('"'+item.value+'"','""')};
	
	</#if>
</#list>
<#list fieldList as item>
	<#if item.type == 'Integer'>
	public Integer get${item.name?cap_first}() {
		return ${item.name};
	}

	public void set${item.name?cap_first}(Integer ${item.name}) {
		this.${item.name} = ${item.name};
	}
	
	<#elseif item.type == 'DateTime'>
	public DateTime get${item.name?cap_first}() {
		return ${item.name};
	}

	public void set${item.name?cap_first}(DateTime ${item.name}) {
		this.${item.name} = ${item.name};
	}
	
	<#else>
	public String get${item.name?cap_first}() {
		return ${item.name};
	}

	public void set${item.name?cap_first}(String ${item.name}) {
		this.${item.name} = ${item.name};
	}
	
	</#if>
</#list>
}
