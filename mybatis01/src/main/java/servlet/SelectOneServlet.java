package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.UserMapper;
import entity.User;
import util.MybatisUtil;

@WebServlet("/SelectOneServlet")
public class SelectOneServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		//调用mybatis，去数据库里面根据id查询一个人
		SqlSession session = MybatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//查询
		User user = mapper.selectById(Integer.parseInt(id));
		
		req.setAttribute("user", user);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}

}
