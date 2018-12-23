package com.burachenko.munichhotel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public abstract class IdentifiableDto implements Serializable {

    private Long id;
}
