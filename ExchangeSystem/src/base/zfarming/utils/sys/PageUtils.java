package base.zfarming.utils.sys;

import java.util.List;
import java.util.Map;

public class PageUtils {

	public final static int DefaultPageSize = 10;
	
	private int pageSize;//每页条数
	private long totleSize;//总条数
	private int totalPage;//总页数
	private int pageIndex;//当前页码
	private List<Map<String,Object>> list;//分页数据
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public long getTotleSize() {
		return totleSize;
	}
	public void setTotleSize(long totleSize) {
		this.totleSize = totleSize;
		if(totleSize%pageSize == 0){
			this.totalPage = (int) (totleSize/pageSize);
		} else {
			this.totalPage = (int) (totleSize/pageSize + 1);
		}
	}
	
	
	public PageUtils(){
		this.pageSize = DefaultPageSize;
	}
	public PageUtils(int pageSize){
		this.pageSize = pageSize;
	}
	
	public String toString(){
		return "pageSize:"+pageSize+"  totleSize:"+totleSize+"  totalPage:"+totalPage+"   pageIndex:"+pageIndex;
	}
	
}
