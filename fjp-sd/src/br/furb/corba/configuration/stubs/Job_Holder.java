package br.furb.corba.configuration.stubs;

/**
* br/furb/corba/configuration/stubs/Job_Holder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 19h30min17s BRST
*/

public final class Job_Holder implements org.omg.CORBA.portable.Streamable
{
  public br.furb.corba.configuration.stubs.Job_ value = null;

  public Job_Holder ()
  {
  }

  public Job_Holder (br.furb.corba.configuration.stubs.Job_ initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.furb.corba.configuration.stubs.Job_Helper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.furb.corba.configuration.stubs.Job_Helper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.furb.corba.configuration.stubs.Job_Helper.type ();
  }

}
