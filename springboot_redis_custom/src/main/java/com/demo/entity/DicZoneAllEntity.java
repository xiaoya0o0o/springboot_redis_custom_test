package com.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Setter
//@Getter
@Data
public class DicZoneAllEntity implements Serializable {

    private String cityId;
    private String cityName;
    private String countyId;
    private String countyName;

}
