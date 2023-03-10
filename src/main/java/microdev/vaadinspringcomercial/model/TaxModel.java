package microdev.vaadinspringcomercial.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String detalle;
    private Integer codigo;
    @OneToMany(mappedBy = "impuesto")
    @ToString.Exclude
    private List<ProductModel> productModelList;


}
