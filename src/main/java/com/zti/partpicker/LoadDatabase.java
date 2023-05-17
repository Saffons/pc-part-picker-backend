package com.zti.partpicker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

}