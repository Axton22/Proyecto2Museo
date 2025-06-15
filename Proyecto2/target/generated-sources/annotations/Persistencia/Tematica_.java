package Persistencia;

import Persistencia.Sala;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-14T18:41:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tematica.class)
public class Tematica_ { 

    public static volatile SingularAttribute<Tematica, String> caracteristicas;
    public static volatile SingularAttribute<Tematica, String> nombreTematica;
    public static volatile SingularAttribute<Tematica, String> epocaTematica;
    public static volatile SingularAttribute<Tematica, Integer> id;
    public static volatile SingularAttribute<Tematica, Sala> idSala;

}