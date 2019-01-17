package br.com.sucrilhos.wacky.sound.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.AudioFileFormatConverter;

@Component
public class WackySoundServiceImpl
    implements
        WackSoundService
{
    @Autowired
    private AudioFileFormatConverter audioFileFormatConverter;
}
