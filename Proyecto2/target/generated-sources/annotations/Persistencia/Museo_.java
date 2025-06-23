package Persistencia;

import Persistencia.Sala;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-23T16:06:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Museo.class)
public class Museo_ { 

    public static volatile SingularAttribute<Museo, String> nombreDirector;
    public static volatile SingularAttribute<Museo, String> tipo;
    public static volatile SingularAttribute<Museo, String> ubicacion;
    public static volatile SingularAttribute<Museo, Date> fechaFundacion;
    public static volatile SingularAttribute<Museo, String> sitioWeb;
    public static volatile CollectionAttribute<Museo, Sala> salaCollection;
    public static volatile SingularAttribute<Museo, Integer> id;
    public static volatile SingularAttribute<Museo, String> nombre;

}