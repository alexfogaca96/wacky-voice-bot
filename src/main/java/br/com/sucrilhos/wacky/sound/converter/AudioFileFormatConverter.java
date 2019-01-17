package br.com.sucrilhos.wacky.sound.converter;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.command.audio.AudioFileConversionCommand;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@Component
public class AudioFileFormatConverter
    implements
        Converter<AudioFileFormat,File,File>
{
    @Autowired
    private List<AudioFileConversionCommand> conversionCommands;

    @Override
    public File convert(
        final File source,
        final AudioFileFormat targetFormat )
        throws ConversionException
    {
        for( final AudioFileConversionCommand conversioncommand : conversionCommands ) {
            final AudioFileFormat sourceFormat = AudioFileFormat.getFileFormat( source );
            if( conversioncommand.isSupported( sourceFormat, targetFormat ) ) {
                return conversioncommand.execute( source, targetFormat );
            }
        }
        throw new ConversionException( "There's a conflict on our supported formats and converters." );
    }
}
