package br.com.sucrilhos.wacky.sound.converter.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sucrilhos.wacky.sound.converter.AudioFileFormatConverter;
import br.com.sucrilhos.wacky.sound.converter.ConversionException;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@Service
public class ConverterServiceImpl
    implements
        ConverterService
{
    @Autowired
    private AudioFileFormatConverter audioFileFormatConverter;

    @Override
    public byte[] convertAudioFileFormat(
        final AudioFileFormat targetFormat,
        final MultipartFile sourceFile )
        throws ConversionException
    {
        try {
            final File source = multipartFileToFile( sourceFile );
            final File target = new File( "temporary.".concat( targetFormat.name().toLowerCase() ) );
            audioFileFormatConverter.convert( source, target );
            return Files.readAllBytes( target.toPath() );
        } catch( IllegalStateException | IOException e ) {
            throw new RuntimeException();
        }
    }
}
