package com.uikoo9.util.plugin.tree;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.uikoo9.util.core.data.QStringUtil;

/**
 * tree
 * @author qiaowenbin
 */
public class QTree {
	private int id;
	private String url;
	private String text;
	private boolean checked = false;
	private List<QTree> children;

	public QTree() {
		super();
	}
	public QTree(int id, String url, String text, String tableName, QTreeCheck check) {
		super();
		this.id = id;
		this.url = url;
		this.text = text;
		if(check != null) this.checked = check.isCheck(id);	
		this.children = findChildrenByTableAndCheck(id, tableName, check);
	}
	private List<QTree> findChildrenByTableAndCheck(int id, String tableName, QTreeCheck check){
		List<QTree> list = new ArrayList<QTree>();
		
		if(QStringUtil.isEmpty(tableName)) tableName = "t_ucenter_menu";
		List<Record> menus = Db.find("select * from " + tableName + " where ucenter_menu_parent_id=? order by ucenter_menu_sn", id);
		for(Record menu : menus){
			list.add(new QTree(menu.getInt("id"), menu.getStr("ucenter_menu_url"), menu.getStr("ucenter_menu_title"), tableName, check));
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
	public List<QTree> getChildren() {
		return children;
	}
	public void setChildren(List<QTree> children) {
		this.children = children;
	}
}
