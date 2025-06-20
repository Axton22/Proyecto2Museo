package Persistencia;

import Persistencia.Coleccion;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-19T18:15:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Especies.class)
public class Especies_ { 

    public static volatile SingularAttribute<Especies, String> caracteristicas;
    public static volatile SingularAttribute<Especies, Date> fechaExtincion;
    public static volatile SingularAttribute<Especies, BigDecimal> peso;
    public static volatile SingularAttribute<Especies, BigDecimal> tamanio;
    public static volatile SingularAttribute<Especies, String> nombreComun;
    public static volatile SingularAttribute<Especies, String> nombreCientifico;
    public static volatile SingularAttribute<Especies, Integer> id;
    public static volatile SingularAttribute<Especies, Coleccion> idColeccion;
    public static volatile SingularAttribute<Especies, String> epocaVivio;

}