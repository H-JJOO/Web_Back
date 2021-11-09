package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<BoardVO> list = BoardDAO.selBoardList();

        req.setAttribute("data", list);//담는다

        String path = "/WEB-INF/jsp/list.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, res);//req, res 전달받음
        //res 응답을 맡긴다, req 요청을 보낸다?
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
