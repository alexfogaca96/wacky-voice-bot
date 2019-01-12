package br.com.sucrilhos.wacky.sound.api;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.AudioFormatConverter;

@Component
public class WackySoundServiceImpl
    implements
        WackSoundService
{
    private static final File SOURCE = new File( "src/main/resources/Example.ogg" );
    private static final File TARGET = new File( "src/main/resources/Converted.wav" );

    @Autowired
    private AudioFormatConverter<File,File> audioFormatConverter;
}
