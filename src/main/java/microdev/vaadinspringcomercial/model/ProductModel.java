package microdev.vaadinspringcomercial.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

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
    @OneToOne(mappedBy = "productModel")
    private TaxModel impuesto;
    @ManyToOne
    private ProviderModel providerModel;

}
