package com.lmh.function.codefreemark;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用步骤：
 * 1. 将template包拷贝到某个项目的源代码路径下
 * 2. 将ftl包拷贝的资源文件夹路径下，一般都是config文件夹下
 * 3. 修改此类中的一些配置属性，然后运行此类即可生成对应的文件了。
 * 4. 这里生成仅仅是包含了一些普通的属性，如果需要复杂的属性如集合、数组，请在生成后再手动自行添加，
 *    使用hibernate时，在实体类文件中请自行加上属性注解配置。
 * @Title: CodeCreate.java 
 * @Package template 
 * @Description: 
 * @author liminghui   
 * @date 2016年3月9日 上午9:38:09 
 * @version V1.0
 */
public class CodeCreate {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		String basePackagePath = "com.lutongnet";
		String modalName = "test";
		map.put("basePrefixPackage", basePackagePath);	//项目包结构的前缀路径
		map.put("modalNameLower", modalName);			//模块名称
		map.put("jspFolderPath", "test/role");			//jsp文件相对项目中配置了suffix前缀后的前缀路径，比如：sufix设置为views，则路径为/views/test/role/list.jsp
		map.put("entityNameZh", "角色");					//实体类中文名，尽量简短意义明确
		
		map.put("className", "Role");					//类名前缀，生成的controller、dao和service都是以此类名作为开始
		map.put("classNameLower", "role");				//全部为小写字母的类名
		map.put("classNameFirstLower", "role");			//首字母为小写字母的类名
		
		String entityName = "RoleInfo";
		map.put("entityName", entityName);					//实体类的类名，此处的名称与上面的类名很大情况下会是同一个，这里为了适应出现不同的情况，进行分离设置
		map.put("entityNameLower", "roleinfo");				//全部为小写字母的实体类的类名
		map.put("entityNameFirstLower", "roleInfo");		//首字母为小写字母的实体类的类名

		//map.put("tabletop", "T_");					//表前缀	
		map.put("nowDate", new Date());					//创建日期
		
		//定义实体类都有那些属性
		List<ClassProperties> list = new ArrayList<ClassProperties>();
		list.add(new ClassProperties("name", "String", "liminghui", "姓名"));
		list.add(new ClassProperties("age", "Integer", "99", "年龄"));
		list.add(new ClassProperties("birthday", "DateTime", "2016-03-07", "出生日期"));
		map.put("fieldList", list);
		
		String ftlPath = "";					//ftl模板文件所在路径，默认在类路径classes文件夹下的ftl目录下
		
		//实际生成文件的绝对文件夹前缀路径，这里设置默认为：当前项目所在的目录+设置的包路径+模块名(项目所在路径里最好不要有特殊的字符)
		String tempPath = System.getProperty("user.dir")+"/src/"+ basePackagePath+File.separator+modalName+File.separator;
		System.out.println(tempPath);
		
		try {
			//如果需要修改生成文件所在的文件夹名称，则修改下面tempPath+后面的名称即可，同时还需要修改ftl模板中相应的一些引用
			Freemarker.printFile("controllerTemplate2.ftl", map, tempPath+"controller", "Controller.java", ftlPath);
			Freemarker.printFile("serviceTemplate2.ftl", map, tempPath+"service", "Service.java", ftlPath);
			Freemarker.printFile("daoTemplate2.ftl", map, tempPath+"dao", "DAO.java", ftlPath);
			Freemarker.printFile("commonReqTemplate2.ftl", map, tempPath+"entity", "CommonReq.java", ftlPath);
			Freemarker.printFile("entityTemplate2.ftl", map, tempPath+"entity", entityName+".java", ftlPath);
			Freemarker.printFile("entityReqTemplate2.ftl", map, tempPath+"entity", entityName+"Req.java", ftlPath);
			Freemarker.printFile("jsp_list_Template2.ftl", map, tempPath+"jsp", "list.jsp", ftlPath);
			Freemarker.printFile("jsp_add_Template2.ftl", map, tempPath+"jsp", "add.jsp", ftlPath);
			Freemarker.printFile("jsp_update_Template2.ftl", map, tempPath+"jsp", "update.jsp", ftlPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


