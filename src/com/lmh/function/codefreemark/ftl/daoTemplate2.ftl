package ${basePrefixPackage}.${modalNameLower}.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.vo.PaginationBean;
import ${basePrefixPackage}.${modalNameLower}.entity.${entityName};
import ${basePrefixPackage}.${modalNameLower}.entity.${entityName}Req;

/**
 * ${entityName}DAO相关处理的实现类
 * 创建人：somebody
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Repository("${classNameFirstLower}DAO")
public class ${className}DAO extends EntityDaoSupport<${entityName}> {
	
	/**
	 * 分页查询列表
	 */
	public PaginationBean<${entityName}> findListByPage(${entityName}Req ${entityNameFirstLower}Req){
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		return findByPaging(sb.toString(), params, ${entityNameFirstLower}Req.getCurrent(), ${entityNameFirstLower}Req.getPageSize());
	}
}
	