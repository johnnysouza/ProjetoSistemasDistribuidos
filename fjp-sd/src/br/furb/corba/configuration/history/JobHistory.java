package br.furb.corba.configuration.history;

import java.io.Serializable;
import java.util.Date;

public class JobHistory implements Serializable {

	private static final long serialVersionUID = 8122184915490702640L;
	private long dateInMillis;
	private String log;
	private HistoryStatus status;

	public JobHistory(String log, HistoryStatus status, long date) {
		this.log = log;
		this.status = status;
		this.dateInMillis = date;
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

}
