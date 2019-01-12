package br.com.sucrilhos.wacky.sound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackageClasses = SucrilhosApplication.class )
public class SucrilhosApplication
{
    public static void main(
        final String[] args )
    {
        SpringApplication.run( SucrilhosApplication.class, args );
    }
}
