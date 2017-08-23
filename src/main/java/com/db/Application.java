/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import com.db.convert.service.DBConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author chanu1993@gmail.com
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DBConverterService dBConverterService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("------------------------  satart db convert ------------------------");
        dBConverterService.mainSave();
        System.out.println("------------------------  end db convert ------------------------");
    }
}
