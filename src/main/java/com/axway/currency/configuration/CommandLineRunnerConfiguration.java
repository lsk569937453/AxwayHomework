package com.axway.currency.configuration;

import com.axway.currency.service.CommandLineService;
import com.axway.currency.vojo.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfiguration implements CommandLineRunner {

    public static final Logger logger= LoggerFactory.getLogger(CommandLineRunnerConfiguration.class);
    @Autowired
    CommandLineService commandLineService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void run(String... args) {
        try {
            //resolve the request
            BaseResponse baseResponse = commandLineService.parseAndRequest(args[0]);
            System.out.println(objectMapper.writeValueAsString(baseResponse));
        }catch (Exception e){
            logger.error("",e);
        }
    }
}
