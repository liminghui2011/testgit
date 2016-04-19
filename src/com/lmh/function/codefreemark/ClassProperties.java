package com.lmh.function.codefreemark;

public class ClassProperties{
	//字段名称
	public String name;
	
	//字段类型
	public String type;
	
	//字段默认值
	public String value;
	
	//字段描述
	public String nameDesc;
	
	public ClassProperties() {
		super();
	}
	
	public ClassProperties(String name, String type, String value, String nameDesc) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.nameDesc = nameDesc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNameDesc() {
		return nameDesc;
	}
	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}
}
