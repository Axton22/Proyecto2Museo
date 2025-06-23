package Persistencia;

import Persistencia.Sala;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-23T16:06:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Entrada.class)
public class Entrada_ { 

    public static volatile SingularAttribute<Entrada, String> nombreVisitante;
    public static volatile SingularAttribute<Entrada, BigDecimal> precio;
    public static volatile SingularAttribute<Entrada, BigDecimal> iva;
    public static volatile SingularAttribute<Entrada, Boolean> autorizada;
    public static volatile SingularAttribute<Entrada, Date> fechaVisita;
    public static volatile SingularAttribute<Entrada, BigDecimal> comision;
    public static volatile SingularAttribute<Entrada, BigDecimal> totalPagar;
    public static volatile SingularAttribute<Entrada, Integer> id;
    public static volatile SingularAttribute<Entrada, Sala> idSala;
    public static volatile SingularAttribute<Entrada, String> nombreTarjeta;

}