package com.javaex.ex03;

public class AuthorVo {
	//메소드 파라미터를 Vo사용//
	
	//필드
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	//생성자
	public AuthorVo() {
	
	}

	public AuthorVo(String authorName, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	//메소드게세
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getAuthorDesc() {
		return authorDesc;
	}
	
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	//메소드일반
	@Override
	public String toString() {
		return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	

}
