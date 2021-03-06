
package br.furb.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GitCloneWebService", targetNamespace = "http://webservice.furb.br/", wsdlLocation = "http://localhost:9090/clone?wsdl")
public class GitCloneWebService
    extends Service
{

    private final static URL GITCLONEWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException GITCLONEWEBSERVICE_EXCEPTION;
    private final static QName GITCLONEWEBSERVICE_QNAME = new QName("http://webservice.furb.br/", "GitCloneWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9090/clone?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GITCLONEWEBSERVICE_WSDL_LOCATION = url;
        GITCLONEWEBSERVICE_EXCEPTION = e;
    }

    public GitCloneWebService() {
        super(__getWsdlLocation(), GITCLONEWEBSERVICE_QNAME);
    }

    public GitCloneWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), GITCLONEWEBSERVICE_QNAME, features);
    }

    public GitCloneWebService(URL wsdlLocation) {
        super(wsdlLocation, GITCLONEWEBSERVICE_QNAME);
    }

    public GitCloneWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GITCLONEWEBSERVICE_QNAME, features);
    }

    public GitCloneWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GitCloneWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GitCloneWeb
     */
    @WebEndpoint(name = "GitCloneWebPort")
    public GitCloneWeb getGitCloneWebPort() {
        return super.getPort(new QName("http://webservice.furb.br/", "GitCloneWebPort"), GitCloneWeb.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GitCloneWeb
     */
    @WebEndpoint(name = "GitCloneWebPort")
    public GitCloneWeb getGitCloneWebPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.furb.br/", "GitCloneWebPort"), GitCloneWeb.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GITCLONEWEBSERVICE_EXCEPTION!= null) {
            throw GITCLONEWEBSERVICE_EXCEPTION;
        }
        return GITCLONEWEBSERVICE_WSDL_LOCATION;
    }

}
