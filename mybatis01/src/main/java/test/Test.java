package test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import dao.UserMapper;
import entity.User;
import util.MybatisUtil;

public class Test {

	public static void main(String[] args) throws IOException {
	
		SqlSession session = MybatisUtil.getSession();
		//执行查询
		//List<User> list = session.selectList("hello.selectuser");
		
		UserMapper mapper = session.getMapper(UserMapper.class);//获取接口实例
		List<User> list = mapper.selectAll();

        System.out.println(JSON.toJSONString(list,true));
	}
}
