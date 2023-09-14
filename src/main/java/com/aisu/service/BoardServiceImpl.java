package com.aisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisu.domain.BoardVO;
import com.aisu.domain.Criteria;
import com.aisu.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public BoardVO get(long bno) {
		log.info("get....");
		return mapper.read(bno);
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList....");
//		return mapper.getList();
//	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList....");
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal....");
		return mapper.getTotalCount(cri);
	}

	@Override
	public void register(BoardVO board) {
		log.info("register...." + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....");
		return (mapper.update(board) == 1);
	}

	@Override
	public boolean remove(long bno) {
		log.info("remove....");
		return (mapper.delete(bno) == 1);
	}

}
