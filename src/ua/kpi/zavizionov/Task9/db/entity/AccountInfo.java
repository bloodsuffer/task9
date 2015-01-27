package ua.kpi.zavizionov.Task9.db.entity;

public class AccountInfo extends Entity {
	
	private Account account;
	private int duration;
	private float interestRate;
	private int periodicity;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public int getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

}
