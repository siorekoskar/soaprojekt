package proj;

import entity.Catalog;
import entity.Forest;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class CatalogBean {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    public List<Catalog> getCatalogs() {
        return remoteCatalogue.getCatalogs();
    }

    public List<Forest> getForests() {
        return remoteCatalogue.getForests();
    }
}
