package com.sti.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sti.bootcamp.dao.config.DaoSpringConfig;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories({"com.sti.bootcamp.dao.repository"})
@EntityScan({"com.sti.bootcamp"})
@Import({DaoSpringConfig.class})
public class App 
{
	public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
