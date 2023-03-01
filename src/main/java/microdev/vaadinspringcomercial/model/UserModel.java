package microdev.vaadinspringcomercial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class UserModel {

    @Id
    private String cedula;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String password;

    public UserModel(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserModel userModel = (UserModel) o;
        return getCedula() != null && Objects.equals(getCedula(), userModel.getCedula());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
