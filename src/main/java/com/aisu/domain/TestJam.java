package com.aisu.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Log4j
@Data
public class TestJam {
	
	public TestJam() {
		log.info("* * * * * TestJam 객체가 생성되었습니다. * * * * *");
	}

}
