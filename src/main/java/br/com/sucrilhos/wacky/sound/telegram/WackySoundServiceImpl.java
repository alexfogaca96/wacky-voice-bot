package br.com.sucrilhos.wacky.sound.telegram;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.AudioFormatConverter;

@Component
public class WackySoundServiceImpl
    implements
        WackSoundService
{
    @Autowired
    private AudioFormatConverter<File,File> audioFormatConverter;
}
