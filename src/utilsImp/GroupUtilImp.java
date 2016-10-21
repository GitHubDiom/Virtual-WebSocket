package utilsImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Group;
import utils.GroupUtil;
import utils.SqlUtil;

public class GroupUtilImp implements GroupUtil {
	//添加自定义分组
	@Override
	public boolean addGroup(Group group) {
		// TODO Auto-generated method stub
		
		String groupName = group.getGroupName();
		String userPhone = group.getUserPhone();
		
		Statement st = null;
		int count = 0;
		Connection con = null;
		String sql = "insert into groups(userPhone,groupName ) value('"+userPhone+"','"+groupName+"')";
		
		try {
			con = SqlUtil.getCon();
			st = con.createStatement();
			count =  st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(count > 0){
			return true;
		}
		

		return false;
	}
	//删除自定义分组
	@Override
	public boolean deleteGroup(Group group) {
		// TODO Auto-generated method stub
		return false;
	}

}
