package com.tanlsh.util.jfinal.ucenter.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.tanlsh.util.core.annotation.QTable;
import com.tanlsh.util.core.data.QStringUtil;

/**
 * 用户
 * @author uikoo9
 */
@QTable("t_ucenter_user")
@SuppressWarnings("serial")
public class UcenterUserModel extends Model<UcenterUserModel>{

	public static final UcenterUserModel dao = new UcenterUserModel();
	
	/**
	 * find all default
	 * @return
	 */
	public List<UcenterUserModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all with order
	 * @param order
	 * @return
	 */
	public List<UcenterUserModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_ucenter_user ");
		if(QStringUtil.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
	/**
	 * 获取用户对应的角色
	 * @return
	 */
	public UcenterRoleModel role(){
		return UcenterRoleModel.dao.findFirst("select ur.* from t_ucenter_role ur, t_ucenter_role_r_user rl where ur.id=rl.ucenter_role_id and rl.ucenter_user_id=?", get("id"));
	}
}
