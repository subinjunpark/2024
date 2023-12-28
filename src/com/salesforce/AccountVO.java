package com.salesforce;

public class AccountVO {
	private Long seqId;
	private Long deposit;
	private Long withdraw;
	private String trDate;
	private Long balance;
	
	public AccountVO(Long seqId, Long deposit, Long withdraw, String trDate, Long balance) {
		super();
		this.seqId = seqId;
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.trDate = trDate;
		this.balance = balance;
	}
	public AccountVO(Long balance) {
		super();
		this.balance = balance;
	}
	
	public Long getSeqId() {
		return seqId;
	}
	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	public Long getDeposit() {
		return deposit;
	}
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Long getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(Long withdraw) {
		this.withdraw = withdraw;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountVO [seqId=" + seqId + ", deposit=" + deposit + ", withdraw=" + withdraw + ", trDate=" + trDate
				+ ", balance=" + balance + "]";
	}
	
	
}
