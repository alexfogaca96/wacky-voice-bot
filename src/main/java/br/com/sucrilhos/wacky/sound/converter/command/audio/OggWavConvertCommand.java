package br.com.sucrilhos.wacky.sound.converter.command.audio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Component;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@Component
public class OggWavConvertCommand
    implements
        AudioFileConversionCommand
{
    private static final String TEMPORARY_FILES_PATH = "src/main/java/oggwav/";
    private static final String TEMPORARY_FILE_PATH = TEMPORARY_FILES_PATH.concat( "temporary" );

    @Override
    public boolean isSupported(
        final AudioFileFormat source,
        final AudioFileFormat target )
    {
        return source.equals( AudioFileFormat.OGG ) &&
            target.equals( AudioFileFormat.WAV ) ||
            source.equals( AudioFileFormat.WAV ) &&
                target.equals( AudioFileFormat.OGG );
    }

    @Override
    public File execute(
        final File source,
        final AudioFileFormat targetType )
        throws ConversionException
    {
        try {
            copyFileTo( source );
            final AudioInputStream sourceAudioInput = AudioSystem.getAudioInputStream( source );
            final AudioFormat sourceFormat = sourceAudioInput.getFormat();
            final AudioFormat targetFormat = new AudioFormat( AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(),
                16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false );

            final AudioInputStream targetAudioInput = AudioSystem.getAudioInputStream( targetFormat, sourceAudioInput );
            final File target = getNewAudioFile( targetType );
            AudioSystem.write( targetAudioInput, javax.sound.sampled.AudioFileFormat.Type.WAVE, target );
            return target;
        } catch( UnsupportedAudioFileException | IOException exception ) {
            throw new ConversionException( "[OGG - WAV] or [WAV - OGG] conversion has failed.", exception );
        }
    }

    private static File getNewAudioFile(
        final AudioFileFormat fileFormat )
        throws IOException
    {
        final Path path = Paths.get( TEMPORARY_FILE_PATH.concat( fileFormat.name().toLowerCase() ) );
        if( Files.exists( path ) ) {
            Files.delete( path );
        }
        return new File( path.toString() );
    }
}
