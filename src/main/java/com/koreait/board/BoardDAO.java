package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer) " +
                    " VALUES (?, ?, ?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;

    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList<>();//정보담을 리스트 생성

        Connection con = null;//연결 초기화
        PreparedStatement ps = null;//쿼리문 초기화
        ResultSet rs = null;//쿼리문 담을 곳 초기화
        String sql = " SELECT iboard, title, writer, rdt " +
                    " FROM t_board " +
                    " ORDER BY iboard DESC ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }
}
