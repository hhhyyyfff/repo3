package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	public static SqlSession getSession() {
		try {
			//加载核心配置文件
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			//创建构建工厂
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			//生成sqlsession
			//sqlsession: 就是执行sql的工具
			SqlSession session = factory.openSession();
			return session;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
