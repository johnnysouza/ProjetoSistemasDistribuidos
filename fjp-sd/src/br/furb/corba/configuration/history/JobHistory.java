package br.furb.corba.configuration.history;

import java.io.Serializable;

public class JobHistory implements Serializable {

	private static final long serialVersionUID = 8122184915490702640L;
	private String log;
	private HistoryStatus status;

	public JobHistory(String log, HistoryStatus status) {
		this.log = log;
		this.status = status;
	}

	public String getLog() {
		return log;
	}

	public HistoryStatus getStatus() {
		return status;
	}

}
