package br.furb.corba.configuration.history;

public interface JobHistoryDAO {

	/**
	 * Adiciona um hist�rico a um job
	 * 
	 * @param jobName - o nome do job a ter o historico adicionado
	 * @param history - o hist�rico a ser persistido
	 */
	void addHistory(String jobName, JobHistory history);

	/**
	 * Carrega os historicos de um job
	 * 
	 * @param jobName - o nome do job a ser carregado
	 * @return os hist�rios do job, ou <code>null</code> se n�o houver nenhum hist�rico
	 */
	JobHistory[] loadHistorys(String jobName);

}
