package br.com.sucrilhos.wacky.sound.converter.command.audio;

import java.io.File;
import java.io.IOException;

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
    public File convert(
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
            return getAudioInATemporaryFile( targetType, targetAudioInput );
        } catch( UnsupportedAudioFileException | IOException exception ) {
            throw new ConversionException( "[OGG - WAV] or [WAV - OGG] conversion has failed.", exception );
        }
    }

    private static File getAudioInATemporaryFile(
        final AudioFileFormat targetType,
        final AudioInputStream targetAudioInput )
        throws IOException
    {
        final File target = File.createTempFile( TEMPORARY_FILES_PATH, "temp" );
        AudioSystem.write( targetAudioInput, javax.sound.sampled.AudioFileFormat.Type.WAVE, target );
        return target;
    }
}
