package com.aisu.mapper;

import java.util.List;
import java.util.Map;

import com.aisu.domain.BoardVO;
import com.aisu.domain.Criteria;

public interface BoardMapper {

//	@Select("select * from t_board where bno > 0")
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria cri);

	public int getTotalCount(Criteria cri);

	public void insert(BoardVO board);

	public long insertSelectKey(BoardVO board);

	public BoardVO read(long bno);

	public int delete(long bno);

	public int update(BoardVO board);

//	public List<BoardVO> getSearchList(String keyword);
	public List<BoardVO> getSearchList(Map<String, Object> map);
}