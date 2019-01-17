package br.com.sucrilhos.wacky.sound.converter.format;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class AudioFileFormatTest
{
    @Mock
    private File file;

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowIllegalArgumentExceptionWhenFileDoesntHaveExtension()
    {
        Mockito.when( file.getName() ).thenReturn( "fileName" );
        AudioFileFormat.getFileFormat( file );
    }

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowIllegalArgumentExceptionWhenExtensionDoesntCorrespondToAnAudioFileFormat()
    {
        Mockito.when( file.getName() ).thenReturn( "fileName.extensaodementira" );
        AudioFileFormat.getFileFormat( file );
    }

    @Test
    public void shouldReturnCorrespondentAudioFileFormatWhenFileHasAcceptableExtension()
    {
        final String extension = "ogg";
        Mockito.when( file.getName() ).thenReturn( "fileName.".concat( extension ) );
        final AudioFileFormat audioFileFormat = AudioFileFormat.getFileFormat( file );
        Assert.assertEquals( audioFileFormat.name(), extension.toUpperCase() );
    }
}
