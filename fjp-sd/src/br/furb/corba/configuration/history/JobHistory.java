package br.furb.corba.configuration.history;

import java.io.Serializable;

public class JobHistory implements Serializable {

	private static final long serialVersionUID = 8122184915490702640L;
	private long dateInMillis;
	private String log;
	private HistoryStatus status;

	public JobHistory(String log, HistoryStatus status, long dateInMillis) {
		this.log = log;
		this.status = status;
		this.dateInMillis = dateInMillis;
	}

	public String getLog() {
		return log;
	}

	public HistoryStatus getStatus() {
		return status;
	}
	
	public long getDateInMillis() {
		return dateInMillis;
	}

	@Override
	public String toString() {
		return "JobHistory [dateInMillis=" + dateInMillis + ", log=" + log + ", status=" + status + "]";
	}
}
