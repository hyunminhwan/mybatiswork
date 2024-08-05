package com.study.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public interface BoardService {
	//게시판 리스트조회
	int selectTotalRecord();
	ArrayList<Board> selectList(PageInfo pi);
	
	//검색
	ArrayList<Board> searchList(HashMap<String,String> map,PageInfo pi);
	int selectSerchCount(HashMap<String,String> map);
	
	
	//상세조회
	int increaseCount(int boardNo);
	Board selectBoard(int boardNo);
	ArrayList<Reply> selectReplyList(int boardNo);
	
	//댓글 등록
	int insertRply(Reply reply);
}
