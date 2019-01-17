package br.com.sucrilhos.wacky.sound.converter.command.audio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

public interface AudioFileConversionCommand
    extends
        ConversionCommand<AudioFileFormat,File,File>
{
    default void copyFileTo(
        final File file )
        throws IOException
    {
        final InputStream inputStream = getClass().getResourceAsStream( "/".concat( file.getName() ) );
        final OutputStream outputStream = new FileOutputStream( file );
        IOUtils.copy( inputStream, outputStream );
        outputStream.close();
    }
}
