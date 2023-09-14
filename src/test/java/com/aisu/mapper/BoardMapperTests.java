package com.aisu.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aisu.domain.BoardVO;
import com.aisu.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;

	@Test
	public void testGetList() {
		List<BoardVO> list = new ArrayList<>();
		list = mapper.getList();

//		mapper.getList().forEach(board -> log.info(board));
	}

	@Test
	public void testInsert() {
		BoardVO board = new BoardVO("new제목", "new내용", "new작가");
//		BoardVO board = new BoardVO();
//		board.setTitle("제목");
//		board.setContent("내용");
//		board.setWriter("작가");

		mapper.insert(board);

		log.info("내가 쓴 새 글의 정보는 : " + board);
	}

	@Test
	public void testInsertSelectKey() {
		int count;
		BoardVO board = new BoardVO();
		board.setTitle("제목");
		board.setContent("내용");
		board.setWriter("작가");
		log.info("내가 등록할 새 글의 정보는 : " + board);

		count = (int)mapper.insertSelectKey(board);
		
		log.info("내가 쓴 새 글의 정보는 : " + board);
		log.info("생성된 글의 개수는 : " + count);
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(13L);
		log.info("조회한 글의 정보는 : " + board);
	}
	
	@Test
	public void testDelete() {
		int count;
		count = mapper.delete(26L);
		log.info(count);
	}
	
	@Test
	public void testUpdate() {
		int count;
		BoardVO board = new BoardVO("수정한 제목", "수정한 내용", "수정한 작가");
		board.setBno(25L);
		
		count = mapper.update(board);
		log.info(count);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria(3, 10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
	}
	
	//자체 테스트
	@Test
	public void getSearchList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "title"); //title, content, writer
		map.put("keyword", "스5"); //사용자가 검색한 단어
		
		List<BoardVO> list = mapper.getSearchList(map);
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("뉴진스");
		cri.setType("TWC");
		cri.setPageNum(2);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
}
