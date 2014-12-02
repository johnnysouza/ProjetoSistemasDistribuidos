package br.furb.corba.configuration.stubs;


/**
* br/furb/corba/configuration/stubs/job_managerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 19h30min17s BRST
*/

public abstract class job_managerPOA extends org.omg.PortableServer.Servant
 implements br.furb.corba.configuration.stubs.job_managerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("save", new java.lang.Integer (0));
    _methods.put ("exist", new java.lang.Integer (1));
    _methods.put ("delete", new java.lang.Integer (2));
    _methods.put ("load", new java.lang.Integer (3));
    _methods.put ("load_all", new java.lang.Integer (4));
    _methods.put ("add_history", new java.lang.Integer (5));
    _methods.put ("loadHistorys", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // br/furb/corba/configuration/stubs/job_manager/save
       {
         br.furb.corba.configuration.stubs.Job_ job = br.furb.corba.configuration.stubs.Job_Helper.read (in);
         this.save (job);
         out = $rh.createReply();
         break;
       }

       case 1:  // br/furb/corba/configuration/stubs/job_manager/exist
       {
         String job_nome = in.read_string ();
         org.omg.CORBA.BooleanHolder ret = new org.omg.CORBA.BooleanHolder ();
         boolean $result = false;
         $result = this.exist (job_nome, ret);
         out = $rh.createReply();
         out.write_boolean ($result);
         out.write_boolean (ret.value);
         break;
       }

       case 2:  // br/furb/corba/configuration/stubs/job_manager/delete
       {
         String job_nome = in.read_string ();
         this.delete (job_nome);
         out = $rh.createReply();
         break;
       }

       case 3:  // br/furb/corba/configuration/stubs/job_manager/load
       {
         String path = in.read_string ();
         br.furb.corba.configuration.stubs.Job_Holder ret = new br.furb.corba.configuration.stubs.Job_Holder ();
         boolean $result = false;
         $result = this.load (path, ret);
         out = $rh.createReply();
         out.write_boolean ($result);
         br.furb.corba.configuration.stubs.Job_Helper.write (out, ret.value);
         break;
       }

       case 4:  // br/furb/corba/configuration/stubs/job_manager/load_all
       {
         br.furb.corba.configuration.stubs.arrayJobsHolder jobs = new br.furb.corba.configuration.stubs.arrayJobsHolder ();
         boolean $result = false;
         $result = this.load_all (jobs);
         out = $rh.createReply();
         out.write_boolean ($result);
         br.furb.corba.configuration.stubs.arrayJobsHelper.write (out, jobs.value);
         break;
       }

       case 5:  // br/furb/corba/configuration/stubs/job_manager/add_history
       {
         String job_nome = in.read_string ();
         br.furb.corba.configuration.stubs.Job_History history = br.furb.corba.configuration.stubs.Job_HistoryHelper.read (in);
         this.add_history (job_nome, history);
         out = $rh.createReply();
         break;
       }

       case 6:  // br/furb/corba/configuration/stubs/job_manager/loadHistorys
       {
         String job_nome = in.read_string ();
         br.furb.corba.configuration.stubs.arrayJobsHistoryHolder historys = new br.furb.corba.configuration.stubs.arrayJobsHistoryHolder ();
         boolean $result = false;
         $result = this.loadHistorys (job_nome, historys);
         out = $rh.createReply();
         out.write_boolean ($result);
         br.furb.corba.configuration.stubs.arrayJobsHistoryHelper.write (out, historys.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/furb/corba/configuration/stubs/job_manager:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public job_manager _this() 
  {
    return job_managerHelper.narrow(
    super._this_object());
  }

  public job_manager _this(org.omg.CORBA.ORB orb) 
  {
    return job_managerHelper.narrow(
    super._this_object(orb));
  }


} // class job_managerPOA
