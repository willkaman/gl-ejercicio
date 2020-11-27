package cl.globallogic.ejercicio.model;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UsuarioRequestBody {
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
