package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	// 메소드 일반
	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;

		// 리스트 출력
		authorList = authorDao.getAuthorList();
		// 리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);

		// 작가등록
		AuthorVo iAuthorVo = new AuthorVo("정윤우", "부산광역시");
		int count = authorDao.authorInsert(iAuthorVo);
		if (count > 0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + count + ")]");
		}

		// 리스트 출력
		authorList = authorDao.getAuthorList();
		// 리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);

		// 작가수정
		AuthorVo uAuthorVo = new AuthorVo(7, "서채영", "서울특별시");
		authorDao.authorUpdate(uAuthorVo);

		// 리스트 출력
		authorList = authorDao.getAuthorList();
		// 리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);

		
		//작가삭제 
		authorDao.authorDelete(7);
		
		// 리스트 출력
		authorList = authorDao.getAuthorList();
		// 리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);

		 /*
		 //작가 1명의 정보 
		 authorDao.getAuthorList(6);
		 */
	}

	// 리스트 출력 메소드
	public static void printList(List<AuthorVo> authorList) {
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo authorVo = authorList.get(i);

			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();

			/*
			 * int authorId = authorList.get(i).getAuthorId(); String authorName =
			 * authorList.get(i).getAuthorName(); String authorDesc =
			 * authorList.get(i).getAuthorDesc();
			 */

			System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
		}
	}

}
