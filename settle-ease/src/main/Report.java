package main;
import java.util.UUID;


public class Report extends SettleEaseABC {
	
	private UUID id;
	
	

	public Report() {
		this.id = UUID.randomUUID();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Report " + this.getId().toString();
	}

	public UUID getId() {
		return id;
	}

}
