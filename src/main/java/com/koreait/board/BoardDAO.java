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
            //? 에 값 채워야함
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            return ps.executeUpdate();//영향을 미친 레코드 수
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;

    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList();//정보담을 리스트 생성

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

    public static BoardVO selBoardDetail(BoardVO param) {//선언부는 왠만하면 바뀌지 않는게 좋다(범용성 좋게끔)
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM t_board " +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {
                vo = new BoardVO();
                vo.setIboard(param.getIboard());
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                vo.setCtnt(rs.getString("ctnt"));
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int updBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board " +
                    " SET title = ?, " +
                    " ctnt = ?, " +
                    " writer = ?" +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            ps.setInt(4, param.getIboard());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;


    }

}
