package microdev.vaadinspringcomercial.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.GregorianCalendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProdTem {

    @Id
    private String codigo;

    private String codigoPrincipal;
    private int cantidadInterna;
    private String categoria;
    private GregorianCalendar fecha;
    private String impuesto;
    private String nombre;
    private String nombrePrincipal;
    private double precioCaja;
    private double precioCaja12;
    private double precioUnitario;
    private double precioUnitario12;
    private double precioVentaCaja;
    private double precioVentaUnidad;
    private String representacion;
    private double stockPaquete;
    private double stockUnitario;
    @Column(name = "PROVEEDOR_RUC")
    private String proveedorRuc;
    @Column(name = "proveedorruc")
    private String proveedorruc;


}
