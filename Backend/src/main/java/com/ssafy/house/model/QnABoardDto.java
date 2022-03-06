package com.ssafy.house.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnABoardDto : QnA 게시글정보", description = "QnA 게시글의 상세 정보를 나타낸다.")
public class QnABoardDto {
	@ApiModelProperty(value = "글번호")
	private int no;
	@ApiModelProperty(value = "작성자 아이디")
	private String writer;
	@ApiModelProperty(value = "글제목")
	private String subject;
	@ApiModelProperty(value = "글내용")
	private String content;
//	@ApiModelProperty(value = "조회수")
//	private int hit;
	@ApiModelProperty(value = "작성일")	
	private String regtime;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	
}
