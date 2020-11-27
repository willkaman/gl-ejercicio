package cl.globallogic.ejercicio.controller;

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
public class Phone {
    private String number;
    private String citicode;
    private String countrycode;
}
