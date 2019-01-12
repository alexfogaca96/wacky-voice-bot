package br.com.sucrilhos.wacky.sound.converter.command;

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
        ConversionCommand
{
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
    public void execute(
        final File source,
        final File target )
        throws ConversionException
    {
        try {
            copyFileTo( source );
            final AudioInputStream sourceAudioInput = AudioSystem.getAudioInputStream( source );
            final AudioFormat sourceFormat = sourceAudioInput.getFormat();
            final AudioFormat targetFormat = new AudioFormat( AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(),
                16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false );

            final AudioInputStream targetAudioInput = AudioSystem.getAudioInputStream( targetFormat, sourceAudioInput );
            AudioSystem.write( targetAudioInput, javax.sound.sampled.AudioFileFormat.Type.WAVE, target );
        } catch( UnsupportedAudioFileException | IOException exception ) {
            throw new ConversionException( "[OGG - WAV] or [WAV - OGG] conversion has failed.", exception );
        }
    }
}
