package br.com.sucrilhos.wacky.sound.converter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.command.audio.AudioFileConversionCommand;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@Component
public class AudioFileFormatConverter
    implements
        Converter<AudioFileFormat,File,byte[]>
{
    @Autowired
    private List<AudioFileConversionCommand> conversionCommands;

    @Override
    public byte[] convert(
        final File source,
        final AudioFileFormat targetFormat )
        throws ConversionException
    {
        for( final AudioFileConversionCommand conversioncommand : conversionCommands ) {
            final AudioFileFormat sourceFormat = AudioFileFormat.getFileFormat( source );
            if( conversioncommand.isSupported( sourceFormat, targetFormat ) ) {
                final File convertedFile = conversioncommand.convert( source, targetFormat );
                final byte[] convertedFileBytes = readAllBytesFromFile( convertedFile );
                convertedFile.delete();
                return convertedFileBytes;
            }
        }
        throw new ConversionException( "There's a conflict on our supported formats and converters." );
    }

    public static byte[] readAllBytesFromFile(
        final File file )
        throws ConversionException
    {
        try {
            return Files.readAllBytes( file.toPath() );
        } catch( final IOException e ) {
            throw new ConversionException( "Couldn't read bytes from converted file.", e );
        }
    }
}
