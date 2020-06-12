package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserMapper {
	
	List<User> selectAll();
	
	User selectById(int id);
	
	//修改
	void update(@Param("uid") int uid, @Param("ucode") String ucode, @Param("uname") String uname);
}
