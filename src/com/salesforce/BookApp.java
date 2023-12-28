//package com.salesforce;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookApp {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		int count = 0;
//		BookDao vo = new BookDaoImpl();
//		List<BookVO> list = new ArrayList<BookVO>();
//
//		BookVO bo = new BookVO("규칙없음", "RHK", "20200911", 7);
//		count = vo.insert(bo);
//		System.out.print("입력 데이터 건수 : " + count);
//
//	}
//
//}

package com.salesforce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		BookDao dao = new BookDaoImpl();
		List<BookVo> list = new ArrayList<BookVo>();
		list = dao.getList();
		for(BookVo vo : list) {
      System.out.println(vo);
    }
		
//		BookVo vo = new BookVo(0, "테스트책", "출판사", "20231227", 30);
//		if(dao.insert(vo)) {
//			System.out.println("저장 완료");
//		}else {
//			System.out.println("저장 실패");
//		}
		
//		BookVo vo2 = new BookVo(24, "테스트책", "출판사", "20231227", 30);
//		if(dao.update(vo2)) {
//			System.out.println("수정 완료");
//		}else {
//			System.out.println("수정 실패");
//		}
		
//		if (dao.delete(new Long(24))) {
//			System.out.println("삭제 완료");
//		} else {
//			System.out.println("삭제 실패");
//		}
		
		// 검색키워드를 입력받아서
		Scanner sc = new Scanner(System.in);
		System.out.println("검색어 >>");
		String keyword = sc.nextLine();
		
		// 검색 결과를 출력
		List<BookVo> resultList = dao.findKeyword(keyword);
		for(BookVo vo : resultList) {
      System.out.println(vo);
    }

	}

}