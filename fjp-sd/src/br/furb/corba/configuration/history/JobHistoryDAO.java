package br.furb.corba.configuration.history;

public interface JobHistoryDAO {

	/**
	 * Adiciona um histórico a um job
	 * 
	 * @param jobName - o nome do job a ter o historico adicionado
	 * @param history - o histórico a ser persistido
	 */
	void addHistory(String jobName, JobHistory history);

	/**
	 * Carrega os historicos de um job
	 * 
	 * @param jobName - o nome do job a ser carregado
	 * @return os histórios do job, ou <code>null</code> se não houver nenhum histórico
	 */
	JobHistory[] loadHistorys(String jobName);

}
