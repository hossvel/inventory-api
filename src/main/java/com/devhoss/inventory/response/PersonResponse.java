package com.devhoss.inventory.response;


import com.devhoss.inventory.model.PersonEntity;
import lombok.Data;

import java.util.List;

@Data
public class PersonResponse {

    private List<PersonEntity> person;
}
