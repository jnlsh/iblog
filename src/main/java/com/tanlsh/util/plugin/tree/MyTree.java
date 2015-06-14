package com.tanlsh.util.plugin.tree;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tanlsh.util.core.data.StringUtil;

/**
 * tree
 * @author 
 */
public class MyTree {
	private int id;
	private String url;
	private String text;
	private boolean checked = false;
	private List<MyTree> children;

	public MyTree() {
		super();
	}
	public MyTree(int id, String url, String text, String tableName, MyTreeCheck check) {
		super();
		this.id = id;
		this.url = url;
		this.text = text;
		if(check != null) this.checked = check.isCheck(id);	
		this.children = findChildrenByTableAndCheck(id, tableName, check);
	}
	private List<MyTree> findChildrenByTableAndCheck(int id, String tableName, MyTreeCheck check){
		List<MyTree> list = new ArrayList<MyTree>();
		
		if(StringUtil.isEmpty(tableName)) tableName = "t_ucenter_menu";
		List<Record> menus = Db.find("select * from " + tableName + " where ucenter_menu_parent_id=? order by ucenter_menu_sn", id);
		for(Record menu : menus){
			list.add(new MyTree(menu.getInt("id"), menu.getStr("ucenter_menu_url"), menu.getStr("ucenter_menu_title"), tableName, check));
		}
		
		return list;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<MyTree> getChildren() {
		return children;
	}
	public void setChildren(List<MyTree> children) {
		this.children = children;
	}
}
