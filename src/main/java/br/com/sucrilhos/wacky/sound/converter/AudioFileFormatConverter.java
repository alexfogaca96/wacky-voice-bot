package br.com.sucrilhos.wacky.sound.converter;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.command.ConversionCommand;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@Component
public class AudioFileFormatConverter
    implements
        AudioFormatConverter<File,File>
{
    @Autowired
    private List<ConversionCommand> conversionCommands;

    @Override
    public void convert(
        final File source,
        final File target )
        throws ConversionException
    {
        for( final ConversionCommand conversioncommand : conversionCommands ) {
            final AudioFileFormat sourceExtension = AudioFileFormat.getFileExtension( source );
            final AudioFileFormat targetExtension = AudioFileFormat.getFileExtension( target );
            if( conversioncommand.isSupported( sourceExtension, targetExtension ) ) {
                conversioncommand.execute( source, target );
                return;
            }
        }
    }
}
