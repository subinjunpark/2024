package com.salesforce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AccountApp {
	public static void main(String[] args) {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		AccountDAO dao = new AccountDaoImpl();
		List<AccountVO> list = new ArrayList<>();

		while (run) {
			try {
				System.out.println("--------------------------------------------------");
				System.out.println("1.예금 | 2.출금 | 3.잔액조회 | 4.거래일 조회 | 5.기간별 조회");
				System.out.println("--------------------------------------------------");
				System.out.print("선택 : ");
				int menuNo = Integer.parseInt(sc.nextLine());
				switch (menuNo) {
				case 1:
					String str = "deposit";
					Long money;
					System.out.print("예금액 > ");
					money = Long.valueOf(sc.nextLine());
					dao.insertTradeInfo(str, money);
					break;
				case 2:
					str = "withdraw";
					System.out.print("출금액 > ");
					money = Long.valueOf(sc.nextLine());
					dao.insertTradeInfo(str, money);
					break;
				case 3:
					System.out.print("잔액 > ");
					list = dao.getBalance();
					for(AccountVO accountVO : list) {
						System.out.println(accountVO.getBalance());
					}
					break;
				case 4:
					String tradingDate;
					System.out.print("조회일(YYYYMMDD) > ");
					tradingDate = sc.nextLine();
					list = dao.getList(tradingDate);
					for(AccountVO accountVO : list) {
						System.out.println(accountVO);
					}
					break;
				case 5:
					String startDate;
					String endDate;
					
					System.out.println("조회일(YYYYMMDD - YYYYMMDD) > ");
					System.out.print("startDate (YYYYMMDD) > ");
					startDate = sc.nextLine();
					System.out.print("endDate (YYYYMMDD) > ");
					endDate = sc.nextLine();
					list = dao.getList(startDate, endDate);
					for(AccountVO accountVO : list) {
						System.out.println(accountVO.toString());
					}
					break;
				default:
					System.out.println("잘못누르셨습니다.");
					break;
				}
			} catch (java.util.InputMismatchException | java.lang.NumberFormatException e) {
				System.out.println("잘못누르셨습니다.");
			}
		}
		sc.close();
	}
}
