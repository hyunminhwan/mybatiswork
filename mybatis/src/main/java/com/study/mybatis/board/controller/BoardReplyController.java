package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String text = request.getParameter("text");
		String userid = request.getParameter("userId");
		int bno = Integer.parseInt(request.getParameter("bno"));

		Reply reply = new Reply();
		reply.setReplyContent(text);
		reply.setRefBno(bno);
		reply.setReplyWriter(userid);

		int result = new BoardServiceImpl().insertRply(reply);

		if (result > 0) {

			Board b = new BoardServiceImpl().selectBoard(bno);
			ArrayList<Reply> rlist = new BoardServiceImpl().selectReplyList(bno);
			 request.setAttribute("b", b); 
			request.setAttribute("rlist", rlist);
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);

		} else {

			request.setAttribute("errorMsg", "댓글 등록 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);

		}
	}

}
