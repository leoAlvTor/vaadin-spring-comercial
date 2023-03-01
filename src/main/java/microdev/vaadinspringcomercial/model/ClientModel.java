package microdev.vaadinspringcomercial.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientModel {

    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

    public static List<ClientModel> getDummyData(){
        return Arrays.asList(
                new ClientModel("001", "Leo", "Checa", "2897510", "leo@gmail.com"),
                new ClientModel("002", "Dany", "Cuenca", "2898182", "dany@gmail.com"),
                new ClientModel("003", "Josh", "USA", "S/N", "josh@aol.com"),
                new ClientModel("004", "Mary", "Meximo", "S/N", "mary@aol.com"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClientModel that = (ClientModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
