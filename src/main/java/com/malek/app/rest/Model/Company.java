package com.malek.app.rest.Model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// test lombok
@Getter
@Setter
@EqualsAndHashCode
@ToString
// instead of naming all of the above annotications
// we can just upt @Data
public class Company <T> {

    private T name;
    private String Country;
    private String city;
}
