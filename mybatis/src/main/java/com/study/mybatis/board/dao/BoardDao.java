package com.study.mybatis.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public class BoardDao {

	public int selectTotalRecord(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectTotalRecord");
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		//마이바티스에서 페이징처리를 위해 RowBounds라는 클래스 제공
		//offset : 몇개의 게시글(레코드)을 건너뛰고 조회할건지에 대한 값
		/*
		 * ex) numPerPage : 5 		 offset(건너뛸숫자)  limit(조회할숫자)
		 * ex) nowPage : 1 => 1~5 		0				5
		 * 	   nowPage : 2 => 6~10		5				5
		 * 	   nowPage : 3 => 11~15		10				5
		 */
		int limit=pi.getNumPerPage();
		int offset = (pi.getNowPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",null,rowBounds);
	}
	
	
	public int selectSerchCount(SqlSession sqlSession, HashMap<String, String> map) {
		return sqlSession.selectOne("boardMapper.selectSerchCount",map);
	}
	
	public ArrayList<Board> searchList(SqlSession sqlSession,HashMap<String,String> map, PageInfo pi){
		int limit=pi.getNumPerPage();
		int offset = (pi.getNowPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.searchList",map,rowBounds);
		
	}

	public int increaseCount(SqlSession sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount",boardNo);
	}

	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard",boardNo) ;
	}

	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList",boardNo);
	}

	public int insertRply(SqlSession sqlSession,Reply reply) {
		return sqlSession.insert("boardMapper.insertRply",reply);
	}


	
	
	
	
	
	
	

}