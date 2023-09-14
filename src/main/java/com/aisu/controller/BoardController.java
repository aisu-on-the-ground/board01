package com.aisu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aisu.domain.BoardVO;
import com.aisu.domain.Criteria;
import com.aisu.domain.PageDTO;
import com.aisu.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	// @AllArgsConstructor로 인해 객체를 생성할 때 자동주입.
	// 만일, 생성자를 만들지 않을 경우에는 @Setter(onMethod_ = {@Autowired})를 이용해서 처리해야 함.
	private BoardService service;
	
	public void gitTestMethod() {
		log.info("과연 충돌을 일으킬까?");
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list" + cri);
		model.addAttribute("list", service.getList(cri));
		
		int total;
		
		total = service.getTotal(cri);
		log.info("total" + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register: " + board);

		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		// 'redirect:' 접두어를 사용하면 SpringMVC가 내부적으로 response.sendRedirect()를 처리해준다.
		return "redirect:/board/list";
	}

	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("asdf") long bno, @ModelAttribute("cri") Criteria cri, Model model) {
//	public void get(@RequestParam("asdf") long bno, Criteria cri, Model model) {
		log.info("/get or /modify 실행");
		model.addAttribute("board", service.get(bno));
//		model.addAttribute("cri", cri);
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
//	public String modify(@RequestParam("bno") long bno, 
//			@RequestParam("title") String title, 
//			@RequestParam("content") String content, 
//			@RequestParam("writer") String writer, 
//			RedirectAttributes rttr) {
//		ㄴ> 근데 이렇게 보내면 service.modify(bno, title, content, writer) 이렇게 보내나?
//		    이러면 결국 사용되는 파라미터는 명확하지만 모든 절차마다 메소드의 매개변수를 4개씩 넣어줘야 하는데..
//		    만약 이게 너무 오바고 배보다 배꼽이 크다면? 
//		    service 단에서 VO 객체를 생성하고 BoardVO board = new BoardVO();
//		    setter로 값을 쫙 할당해 준 뒤 (= board.setBno(bno); board.setTitle(title); 등등)
//		    그 다음 메소드부터는 매개변수로 board 객체 1개를 넣어 진행하는 걸까?
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
//			rttr.addFlashAttribute("pageNum", cri.getPageNum());
//			rttr.addFlashAttribute("amount", cri.getAmount());
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
		// redirect는 지정된 주소로 새로운 HTTP GET 요청을 보내는 것. (= Request객체나 Model객체에 데이터를 담아도 전달되지 않는다는 의미.)
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("remove: " + bno);

		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
}