package br.furb.corba.configuration.stubs;

/**
* br/furb/corba/configuration/stubs/JobTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Domingo, 30 de Novembro de 2014 20h33min58s BRST
*/

public final class JobTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public br.furb.corba.configuration.stubs.JobType value = null;

  public JobTypeHolder ()
  {
  }

  public JobTypeHolder (br.furb.corba.configuration.stubs.JobType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.furb.corba.configuration.stubs.JobTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.furb.corba.configuration.stubs.JobTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.furb.corba.configuration.stubs.JobTypeHelper.type ();
  }

}