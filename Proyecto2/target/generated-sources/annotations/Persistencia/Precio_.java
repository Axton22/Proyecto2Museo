package Persistencia;

import Persistencia.Sala;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-23T00:23:02", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Precio.class)
public class Precio_ { 

    public static volatile SingularAttribute<Precio, Integer> id;
    public static volatile SingularAttribute<Precio, Sala> idSala;
    public static volatile SingularAttribute<Precio, BigDecimal> precioLunesSabado;
    public static volatile SingularAttribute<Precio, BigDecimal> precioDomingo;

}