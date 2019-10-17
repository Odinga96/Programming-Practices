package acctMgr.model;

import java.math.BigDecimal;

public class ModelEvent {
	public enum EventKind {
		BalanceUpdate, AgentStatusUpdate, AmountTransferredUpdate
	}
	private EventKind kind;
	private BigDecimal balance;
	private AgentStatus agSt;

	public ModelEvent(EventKind kind, BigDecimal balance, AgentStatus agSt){
		this.balance = balance;
		this.kind = kind;
		this.agSt = agSt;
	}
	public EventKind getKind(){return kind;}
	public BigDecimal getBalance(){
		return balance;
	}
	public AgentStatus getAgStatus(){return agSt;}
}
