package com.data.openapi.settings;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DbSettings {

    private String username;
    private String password;
    private String url;
    private String driverClassName;
    
}
