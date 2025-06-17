package Persistencia;

import Persistencia.Coleccion;
import Persistencia.Museo;
import Persistencia.Precio;
import Persistencia.Tematica;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-16T19:16:24", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> nombreSala;
    public static volatile SingularAttribute<Sala, String> descripcion;
    public static volatile CollectionAttribute<Sala, Precio> precioCollection;
    public static volatile SingularAttribute<Sala, Integer> id;
    public static volatile CollectionAttribute<Sala, Tematica> tematicaCollection;
    public static volatile CollectionAttribute<Sala, Coleccion> coleccionCollection;
    public static volatile SingularAttribute<Sala, Museo> idMuseo;

}