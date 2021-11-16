package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //JSON 이든 Form 방식이든 get 방식은 getParameter 로 받는다** 반드시!!! (고객이 나한테 준다~, Ajax 제외)
        //일단 받을땐 반드시 String 값으로 받아야함
        String strIboard = req.getParameter("iboard");//문자열 iboard 를 가져와서
        int iboard = Integer.parseInt(strIboard);//정수형으로 형변환
        BoardVO param = new BoardVO();//담을 객체 생성
        param.setIboard(iboard);//객체에 iboard 값 입력

        BoardVO data = BoardDAO.selBoardDetail(param);//data 에 DAO 값 넣기

        int prevIboard = BoardDAO.selPrevIboard(param);
        int nextIboard = BoardDAO.selNextIboard(param);

        req.setAttribute("data", data);//키 벨류 값 줘서 입력
        req.setAttribute("prevIboard", prevIboard);
        req.setAttribute("nextIboard", nextIboard);

        String path = "/WEB-INF/jsp/detail.jsp";
        req.getRequestDispatcher(path).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }
}
