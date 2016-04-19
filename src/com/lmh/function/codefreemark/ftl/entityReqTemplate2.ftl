package ${basePrefixPackage}.${modalNameLower}.entity;

/**
 * ${entityName}类的查询值对象
 */
public class ${entityName}Req extends CommonReq {
	/**
	 * 名称，此处是默认设置，如不需要自行删除
	 */
	public String name;
	
	/**
	 * 状态，此处是默认设置，如不需要自行删除
	 */
	public String status;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
