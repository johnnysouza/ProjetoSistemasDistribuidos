
package br.furb.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.furb.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CloneRepository_QNAME = new QName("http://webservice.furb.br/", "cloneRepository");
    private final static QName _CloneRepositoryResponse_QNAME = new QName("http://webservice.furb.br/", "cloneRepositoryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.furb.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CloneRepository }
     * 
     */
    public CloneRepository createCloneRepository() {
        return new CloneRepository();
    }

    /**
     * Create an instance of {@link CloneRepositoryResponse }
     * 
     */
    public CloneRepositoryResponse createCloneRepositoryResponse() {
        return new CloneRepositoryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloneRepository }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb.br/", name = "cloneRepository")
    public JAXBElement<CloneRepository> createCloneRepository(CloneRepository value) {
        return new JAXBElement<CloneRepository>(_CloneRepository_QNAME, CloneRepository.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloneRepositoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb.br/", name = "cloneRepositoryResponse")
    public JAXBElement<CloneRepositoryResponse> createCloneRepositoryResponse(CloneRepositoryResponse value) {
        return new JAXBElement<CloneRepositoryResponse>(_CloneRepositoryResponse_QNAME, CloneRepositoryResponse.class, null, value);
    }

}
