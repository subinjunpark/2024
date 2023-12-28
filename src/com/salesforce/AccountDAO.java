package com.salesforce;

import java.util.List;

public interface AccountDAO {
	public List<AccountVO> getBalance();
	public List<AccountVO> getList(String startDate, String endDate);
	public List<AccountVO> getList(String tradingDate);
	public boolean insertTradeInfo(String depositWithdraw, Long money);
	
}
