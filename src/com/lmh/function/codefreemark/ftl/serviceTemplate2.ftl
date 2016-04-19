package ${basePrefixPackage}.${modalNameLower}.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.dao.vo.PaginationBean;
import ${basePrefixPackage}.${modalNameLower}.dao.${className}DAO;
import ${basePrefixPackage}.${modalNameLower}.entity.${entityName};
import ${basePrefixPackage}.${modalNameLower}.entity.${entityName}Req;


@Service("${classNameFirstLower}Service")
public class ${className}Service {

	@Resource(name="${classNameFirstLower}DAO")
	private ${className}DAO ${classNameFirstLower}DAO;
	
	/**
	 * 新增${entityNameZh}
	 */
	@Transactional
	public void save(${entityName} ${entityNameFirstLower}) {
		${classNameFirstLower}DAO.save(${entityNameFirstLower});
	}
	
	/**
	 * 删除${entityNameZh}
	 */
	@Transactional
	public void delete(Integer id) {
		${classNameFirstLower}DAO.remove(id);
	}
	
	/*
	* 修改${entityNameZh}
	*/
	@Transactional
	public void update(${entityName} ${entityNameFirstLower}) {
		${classNameFirstLower}DAO.update(${entityNameFirstLower});
	}
	
	/**
	 * 分页获取列表
	 */
	public PaginationBean<${entityName}> list(${entityName}Req ${entityNameFirstLower}Req) {
		return ${classNameFirstLower}DAO.findListByPage(${entityNameFirstLower}Req);
	}
	
	/**
	 * 通过id获取${entityNameZh}数据
	 */
	public ${entityName} findById(Integer id) {
		return ${classNameFirstLower}DAO.get(id);
	}
	
	/**
	 * 批量删除${entityNameZh}
	 */
	public void deleteAll(String[] idArray) {
		for(int i=0; i<idArray.length; i++){
			delete(Integer.parseInt(idArray[i]));
		}
	}
	
}

