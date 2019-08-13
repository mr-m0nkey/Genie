/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author mayowa
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Genie {


    public static void main(String[] args)  {
        SpringApplication.run(Genie.class, args);
    }




}
