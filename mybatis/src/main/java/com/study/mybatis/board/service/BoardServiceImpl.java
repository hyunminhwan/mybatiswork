package com.study.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.dao.BoardDao;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.template.Template;
import com.study.mybatis.common.vo.PageInfo;

public class BoardServiceImpl implements BoardService {
	private BoardDao bDao = new BoardDao();
	
	@Override
	public int selectTotalRecord() {
		SqlSession sqlSession = Template.getSqlSession();
		int totalRecord = bDao.selectTotalRecord(sqlSession);
		sqlSession.close();
		return totalRecord;
	}

	
	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Board> list =bDao.selectList(sqlSession,pi);
		sqlSession.close();
		return list;
	}


	@Override
	public ArrayList<Board> searchList(HashMap<String,String> map, PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Board> list =bDao.searchList(sqlSession,map,pi);
		sqlSession.close();
		return list;
	}

	@Override
	public int selectSerchCount(HashMap<String, String> map) {
		SqlSession sqlSession = Template.getSqlSession();
		int searchCount = bDao.selectSerchCount(sqlSession,map);
		sqlSession.close();
		return searchCount;
	}
	  
	

	@Override
	public int increaseCount(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bDao.increaseCount(sqlSession,boardNo);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}


	@Override
	public Board selectBoard(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		Board b = bDao.selectBoard(sqlSession,boardNo);
		sqlSession.close();
		return b;
	}


	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Reply> rlist = bDao.selectReplyList(sqlSession,boardNo);
		sqlSession.close();
		return rlist;
	}

	@Override
	public int insertRply(Reply reply) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bDao.insertRply(sqlSession, reply);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		return result;
	}





	
	
	
	

}