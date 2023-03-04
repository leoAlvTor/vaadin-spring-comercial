package microdev.vaadinspringcomercial.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    private String codPrincipal;
    private String codAuxiliar;
    private String nombre;
    private BigDecimal valorUnitario;
    private int cantidadInterna;
    private BigDecimal valorPaquete;
    private BigDecimal descuento;
    @ManyToOne
    private TaxModel impuesto;
    @ManyToOne
    private ProviderModel providerModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductModel that = (ProductModel) o;
        return getCodPrincipal() != null && Objects.equals(getCodPrincipal(), that.getCodPrincipal());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
