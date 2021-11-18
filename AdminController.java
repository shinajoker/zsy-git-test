package com.cdxy.controller;

import com.cdxy.dao.AdminDao;
import com.cdxy.dao.impl.AdminDaoImpl;
import com.cdxy.pojo.Admin;
import com.cdxy.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends BaseController{

    public void listView(HttpServletRequest request,
                         HttpServletResponse response)throws Exception{
        PageUtil<Admin> pageUtil = new PageUtil<>();
        String nowPage = request.getParameter("nowPage");
        if (nowPage != null && !"".equals(nowPage))
           pageUtil.setNowPage(Integer.valueOf(nowPage));

        AdminDao adminDao = new AdminDaoImpl();
        long tatol = adminDao.tatol();
        List<Admin> admins = adminDao.findAll(pageUtil.getNowPage(), pageUtil.getPageSize());
        System.out.println(admins);
        pageUtil.setContent(admins);
        pageUtil.setTotalNums(tatol);

        request.setAttribute("pageUtil",pageUtil);
        request.getRequestDispatcher("WEB-INF/pages/admin/admin-list.jsp").forward(request,response);
    }
}
