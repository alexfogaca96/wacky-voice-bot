package br.com.sucrilhos.wacky.sound.converter.api;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

public interface ConverterService
{
    byte[] convertAudioFileFormat(
        AudioFileFormat targetFormat,
        MultipartFile sourceFile )
        throws ConversionException;

    default File multipartFileToFile(
        final MultipartFile multipartFile )
        throws IllegalStateException,
            IOException
    {
        final File file = new File( multipartFile.getOriginalFilename() );
        multipartFile.transferTo( file );
        return file;
    }
}
