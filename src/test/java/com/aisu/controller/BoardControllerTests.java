package com.aisu.controller;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.aisu.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
//1. Test for Controller (웹 개발시, 톰캣같은 WAS를 실행하지 않고도 스프링과 웹 URL을 테스트하기 위함.)
//2. WebAppConfiguration 어노테이션을 통해 ServletContext를 이용하기 위함.
@WebAppConfiguration
public class BoardControllerTests {

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	// 말 그대로 '가짜'mvc를 의미. 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것처럼 만들어 Controller를 실행해 볼 수
	// 있음.
	private MockMvc mockMvc;

	// 모든 테스트 전에 매번 선행실행되는 메소드.
	@org.junit.Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		log.info("* * *  @Before 수행  * * *");
	}

	@Test
	public void testList() throws Exception {
		// MockMvcRequestBuilders.get -> '가짜mvc를 이용해 get 방식으로 /board/list를 호출한 뒤
		// 그 결과인 getList()의 반환값을 얻어와(.getModelAndView()) 어떤 데이터들이 담겨있는지 확인.
		log.info(">>> " + mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testListPaging() throws Exception {
		log.info(">>> " + mockMvc.perform(MockMvcRequestBuilders.get("/board/list").param("pageNum", "1").param("amount", "10")).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title", "Controller Test").param("content", "perform(MockMvcRequestBuilders.post(url문자열))").param("writer", "MockMvc")).andReturn().getModelAndView().getViewName();

		log.info(">>> " + resultPage);
	}

	@Test
	public void testGet() throws Exception {
//		log.info(">>> " + mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("asdf", "28")).andReturn().getModelAndView().getModelMap());
		log.info(addParamGet("/board/get", "29", "ㅁㄷ", "ㅍ", "ㅇ"));
	}

	@Test
	public void testModify() throws Exception {
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", "34")
//				.param("title", "모디파이")
//				.param("content", "수정ㄱㄱ")
//				.param("writer", "나야나")).andReturn().getModelAndView().getViewName();

//		log.info(">>> " + resultPage);
		log.info(addParamPost("/board/modify", "34", "ㅁㄷ", "ㅍ", "ㅇ"));
	}

	@Test
	public void testRemove() throws Exception {
		log.info(addParamPost("/board/remove", "31", "지울거임", "지워용", "지워바"));
	}

	@Test
	public void testCriModify() throws Exception {
		Criteria cri = new Criteria();
		cri.setType("만약 이런 문자열이 들어간다면?");
		String[] typeText = cri.getTypeArr();

		for (int i = 0; i < typeText.length; i++) {
			log.info("String >>> " + typeText[i]);
		}

		cri.setType(null);
		typeText = cri.getTypeArr();
		for (int i = 0; i < typeText.length; i++) {
			log.info("null >>> " + typeText[i]);
		}
	}

	// 파라미터를 간편히 입력할 수 있게 mockMvc의 동작을 묶어놓은 함수 - Post
	public String addParamPost(String url, String bno, String title, String content, String writer) throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post(url).param("bno", bno).param("title", title).param("content", content).param("writer", writer)).andReturn().getModelAndView().getViewName();

		return resultPage;
	}

	// 파라미터를 간편히 입력할 수 있게 mockMvc의 동작을 묶어놓은 함수 - Post
	public ModelMap addParamGet(String url, String bno, String title, String content, String writer) throws Exception {
		ModelMap tempMap;
		tempMap = mockMvc.perform(MockMvcRequestBuilders.get(url).param("asdf", bno) // asdf
				.param("title", title).param("content", content).param("writer", writer)).andReturn().getModelAndView().getModelMap();

		return tempMap;
	}
}
