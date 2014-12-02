package br.furb.corba.configuration.stubs;


/**
* br/furb/corba/configuration/stubs/Job_Helper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 19h30min17s BRST
*/

abstract public class Job_Helper
{
  private static String  _id = "IDL:br/furb/corba/configuration/stubs/Job_:1.0";

  public static void insert (org.omg.CORBA.Any a, br.furb.corba.configuration.stubs.Job_ that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static br.furb.corba.configuration.stubs.Job_ extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "job_nome",
            _tcOf_members0,
            null);
          _tcOf_members0 = br.furb.corba.configuration.stubs.Job_TypeHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "job_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "repository_Path",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (br.furb.corba.configuration.stubs.Job_Helper.id (), "Job_", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static br.furb.corba.configuration.stubs.Job_ read (org.omg.CORBA.portable.InputStream istream)
  {
    br.furb.corba.configuration.stubs.Job_ value = new br.furb.corba.configuration.stubs.Job_ ();
    value.job_nome = istream.read_string ();
    value.job_type = br.furb.corba.configuration.stubs.Job_TypeHelper.read (istream);
    value.repository_Path = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, br.furb.corba.configuration.stubs.Job_ value)
  {
    ostream.write_string (value.job_nome);
    br.furb.corba.configuration.stubs.Job_TypeHelper.write (ostream, value.job_type);
    ostream.write_string (value.repository_Path);
  }

}
