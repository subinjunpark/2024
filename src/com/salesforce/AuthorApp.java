package com.salesforce;

import java.util.ArrayList;
import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		int count = 0;
		AuthorDao dao = new AuthorDaoImpl();
		List<AuthorVO> list = new ArrayList<AuthorVO>();

		// 새로운 데이터 입력
//		AuthorVO vo = new AuthorVO(1, "이태신", "캐릭터");
//		count = dao.insert(vo);
//		System.out.print("입력 데이터 건수 : " + count);
	  
	  // 기존 데이터 수정
//		AuthorVO vo2 = new AuthorVO(30, "이태신", "모티브 : 장태완");
//		count = dao.update(vo2);
//		System.out.print("수정 데이터 건수 : " + count);

		// 기존 데이터 삭제
		Long author_id = new Long(26);
		count = dao.delete(author_id);
		System.out.print("삭제 데이터 건수 : " + count);

		// 전체 데이터 조회
		list = dao.getList();

		for (AuthorVO vo3 : list) {
			System.out.println(vo3);
		}
		System.out.print("전체 데이터 건수 : " + list.size());

	}

}