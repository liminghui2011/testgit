package ${basePrefixPackage}.${modalNameLower}.entity;

/**
 * 分页查询基类
 */
public class CommonReq {
	//当前页
	protected int current = 1;
	
	//页数大小
	protected int pageSize = 10;

	public CommonReq() {
	}

	public CommonReq(int pageSize) {
		this.pageSize = pageSize;
	}

	public CommonReq(int current, int pageSize) {
		this.current = current;
		this.pageSize = pageSize;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
