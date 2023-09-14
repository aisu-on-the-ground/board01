package com.aisu.domain;

import java.util.Date;

import lombok.Data;

@Data //lombok을 이용한 getter/setter, toString() 자동 생성
public class BoardVO {
	
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	
	
	public BoardVO() {}
	
	public BoardVO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
}