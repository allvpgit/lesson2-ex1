package com.seleniumtest.constants.constants;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({
        "classpath:credentials.properties"
})

public interface CredentialsData extends Config {

    CredentialsData CREDENTIALS_DATA = ConfigFactory.create(CredentialsData.class);

    String ADMIN_LOGIN();
    String ADMIN_PASSWORD();

}
