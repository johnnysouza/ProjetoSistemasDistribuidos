package br.furb.corba.configuration.stubs;


/**
* br/furb/corba/configuration/stubs/arrayJobsHistoryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 19h30min17s BRST
*/

abstract public class arrayJobsHistoryHelper
{
  private static String  _id = "IDL:br/furb/corba/configuration/stubs/arrayJobsHistory:1.0";

  public static void insert (org.omg.CORBA.Any a, br.furb.corba.configuration.stubs.Job_History[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static br.furb.corba.configuration.stubs.Job_History[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = br.furb.corba.configuration.stubs.Job_HistoryHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (br.furb.corba.configuration.stubs.arrayJobsHistoryHelper.id (), "arrayJobsHistory", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static br.furb.corba.configuration.stubs.Job_History[] read (org.omg.CORBA.portable.InputStream istream)
  {
    br.furb.corba.configuration.stubs.Job_History value[] = null;
    int _len0 = istream.read_long ();
    value = new br.furb.corba.configuration.stubs.Job_History[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = br.furb.corba.configuration.stubs.Job_HistoryHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, br.furb.corba.configuration.stubs.Job_History[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      br.furb.corba.configuration.stubs.Job_HistoryHelper.write (ostream, value[_i0]);
  }

}
