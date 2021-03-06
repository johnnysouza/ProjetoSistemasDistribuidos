package br.furb.corba.configuration.stubs;


/**
* br/furb/corba/configuration/stubs/_job_managerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JobManager.idl
* Ter�a-feira, 2 de Dezembro de 2014 19h30min17s BRST
*/

public class _job_managerStub extends org.omg.CORBA.portable.ObjectImpl implements br.furb.corba.configuration.stubs.job_manager
{

  public void save (br.furb.corba.configuration.stubs.Job_ job)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("save", true);
                br.furb.corba.configuration.stubs.Job_Helper.write ($out, job);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                save (job        );
            } finally {
                _releaseReply ($in);
            }
  } // save

  public boolean exist (String job_nome, org.omg.CORBA.BooleanHolder ret)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("exist", true);
                $out.write_string (job_nome);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                ret.value = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return exist (job_nome, ret        );
            } finally {
                _releaseReply ($in);
            }
  } // exist

  public void delete (String job_nome)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("delete", true);
                $out.write_string (job_nome);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                delete (job_nome        );
            } finally {
                _releaseReply ($in);
            }
  } // delete

  public boolean load (String path, br.furb.corba.configuration.stubs.Job_Holder ret)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("load", true);
                $out.write_string (path);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                ret.value = br.furb.corba.configuration.stubs.Job_Helper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return load (path, ret        );
            } finally {
                _releaseReply ($in);
            }
  } // load

  public boolean load_all (br.furb.corba.configuration.stubs.arrayJobsHolder jobs)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("load_all", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                jobs.value = br.furb.corba.configuration.stubs.arrayJobsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return load_all (jobs        );
            } finally {
                _releaseReply ($in);
            }
  } // load_all

  public void add_history (String job_nome, br.furb.corba.configuration.stubs.Job_History history)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_history", true);
                $out.write_string (job_nome);
                br.furb.corba.configuration.stubs.Job_HistoryHelper.write ($out, history);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                add_history (job_nome, history        );
            } finally {
                _releaseReply ($in);
            }
  } // add_history

  public boolean loadHistorys (String job_nome, br.furb.corba.configuration.stubs.arrayJobsHistoryHolder historys)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("loadHistorys", true);
                $out.write_string (job_nome);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                historys.value = br.furb.corba.configuration.stubs.arrayJobsHistoryHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return loadHistorys (job_nome, historys        );
            } finally {
                _releaseReply ($in);
            }
  } // loadHistorys

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/furb/corba/configuration/stubs/job_manager:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _job_managerStub
