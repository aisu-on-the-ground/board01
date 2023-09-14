package com.aisu.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aisu.domain.BoardVO;
import com.aisu.domain.Criteria;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("글글글");
		board.setContent("내내내");
		board.setWriter("작작작");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호(bno) : " + board.getBno());
	}

	@Test
	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testModify() {
		BoardVO board = service.get(1L);
		if (board == null) {
			return;
		}
		board.setTitle("제목을 수정합니다.");
		
		log.info("수정 결과는? : " + service.modify(board));
	}
	
	@Test
	public void testRemove() {
		log.info("삭제된 데이터는? : " + service.remove(2L));
	}
}
