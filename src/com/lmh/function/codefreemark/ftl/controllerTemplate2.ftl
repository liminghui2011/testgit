package ${basePrefixPackage}.${modalNameLower}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lutongnet.base.message.SuccessActionResult;
import com.lutongnet.base.message.ErrorActionResult;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.annotation.Log;
import com.lutongnet.system.annotation.MarkRequest;
import com.lutongnet.system.util.HttpUtils;

import ${basePrefixPackage}.${modalNameLower}.entity.${entityName};
import ${basePrefixPackage}.${modalNameLower}.entity.${entityName}Req;
import ${basePrefixPackage}.${modalNameLower}.service.${className}Service;

/** 
 * 类名称：${className}Controller
 * 创建人：somebody
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value="/${classNameFirstLower}")
public class ${className}Controller {
	
	@Resource(name="${classNameFirstLower}Service")
	private ${className}Service ${classNameFirstLower}Service;
	
	/**
	 * 查询信息列表
	 */
	@RequestMapping(value="/list")
	@MarkRequest
	@Log ( "查询${entityNameZh}" )
	public String list( @ModelAttribute ("${entityNameFirstLower}Req") ${entityName}Req ${entityNameFirstLower}Req , Model model ){
		PaginationBean<${entityName}> pb = ${classNameFirstLower}Service.list(${entityNameFirstLower}Req);
		model.addAttribute ( "pb" , pb );
		return "${jspFolderPath}/list";
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping ( value = "/preAdd" , method = RequestMethod.GET )
	public String preAdd( Model model ){
		${entityName} ${entityNameFirstLower} = new ${entityName}();
		model.addAttribute("${entityNameFirstLower}", ${entityNameFirstLower});
		return "${jspFolderPath}/add";
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping ( value = "/add" , method = RequestMethod.POST )
	@Log ( "新增${entityNameZh}" )
	public String add( @ModelAttribute ( "${entityNameFirstLower}" ) ${entityName} ${entityNameFirstLower} , 
		RedirectAttributes ra , HttpServletRequest request ) {
		${classNameFirstLower}Service.save(${entityNameFirstLower});
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" , "${classNameFirstLower}/list.do" );
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/preUpdate")
	public String preUpdate( Integer id, Model model){
		${entityName} ${entityNameFirstLower} = ${classNameFirstLower}Service.findById(id);	//根据ID读取
		model.addAttribute("${entityNameFirstLower}", ${entityNameFirstLower});
		return "${jspFolderPath}/update";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/update")
	@Log ( "修改${entityNameZh}" )
	public String update(${entityName} ${entityNameFirstLower} , 
		RedirectAttributes ra , HttpServletRequest request) {
		${classNameFirstLower}Service.update(${entityNameFirstLower});
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" , "${classNameFirstLower}/list.do" );
	}	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/remove")
	@Log ( "删除${entityNameZh}" )
	public String delete( @RequestParam Integer id , RedirectAttributes ra , HttpServletRequest request ){
		${classNameFirstLower}Service.delete( id );
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI", "${classNameFirstLower}/list.do" );
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/removeBatch")
	@Log ( "批量删除${entityNameZh}" )
	public String deleteBatch(String ids , RedirectAttributes ra , HttpServletRequest request) {
		if(ids == null || ids.length() == 0){
			ra.addFlashAttribute ( "actionResult" , new ErrorActionResult ( ) );
			return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI", "${classNameFirstLower}/list.do" );
		}
		String[] idArray = ids.split(",");
		${classNameFirstLower}Service.deleteAll(idArray);
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI", "${classNameFirstLower}/list.do" );
	}
}
