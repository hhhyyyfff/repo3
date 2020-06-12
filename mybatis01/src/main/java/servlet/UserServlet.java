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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String currentPage = req.getParameter("page");//当前页
		//String pageSize = req.getParameter("limit");//每页大小
		
		SqlSession session = MybatisUtil.getSession();
		//执行查询
		UserMapper mapper = session.getMapper(UserMapper.class);//获取接口实例
		List<User> list = mapper.selectAll();
		
		//转换成json数组
		JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", "获取用户数据成功");
		obj.put("count", 1000);
		obj.put("data", array);
		
		//响应给客户端
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(obj);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String ucode = req.getParameter("ucode");
		String uname = req.getParameter("uname");
		//调用mybatis进行修改啊
		SqlSession session = MybatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//修改
		mapper.update(Integer.parseInt(uid), ucode, uname);
		session.commit();//提交事务
		
		//给ajax响应一个消息
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print("ok");
		
	}
}
