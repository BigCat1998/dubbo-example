package com.me.demo.api.vo;

import com.me.demo.api.service.ValidationService;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ValidationParameter implements Serializable {
    private static final long serialVersionUID = 7158911668568000392L;

    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    @NotNull(groups = ValidationService.Save.class)
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;

    @Min(18)
    @Max(100)
    private int age;

    @Past
    private Date loginDate;

    @Future
    private Date expiryDate;
}