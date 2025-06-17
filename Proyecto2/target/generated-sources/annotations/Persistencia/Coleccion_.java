package Persistencia;

import Persistencia.Especies;
import Persistencia.Sala;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-16T19:58:29", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Coleccion.class)
public class Coleccion_ { 

    public static volatile SingularAttribute<Coleccion, String> siglo;
    public static volatile SingularAttribute<Coleccion, Integer> id;
    public static volatile SingularAttribute<Coleccion, String> descripcionColeccion;
    public static volatile SingularAttribute<Coleccion, Sala> idSala;
    public static volatile SingularAttribute<Coleccion, String> nombreColeccion;
    public static volatile CollectionAttribute<Coleccion, Especies> especiesCollection;

}