package br.furb.corba.configuration.stubs;


/**
* br/furb/corba/configuration/stubs/Job_.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 17h28min38s BRST
*/

public final class Job_ implements org.omg.CORBA.portable.IDLEntity
{
  public String job_nome = null;
  public br.furb.corba.configuration.stubs.Job_Type job_type = null;
  public String repository_Path = null;

  public Job_ ()
  {
  } // ctor

  public Job_ (String _job_nome, br.furb.corba.configuration.stubs.Job_Type _job_type, String _repository_Path)
  {
    job_nome = _job_nome;
    job_type = _job_type;
    repository_Path = _repository_Path;
  } // ctor

} // class Job_
