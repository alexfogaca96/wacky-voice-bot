package br.com.sucrilhos.wacky.sound.converter.command.audio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

public interface AudioFileConversionCommand
    extends
        ConversionCommand<AudioFileFormat,File,File>
{
    /**
     * Converts a {@linkplain File} to a desired {@linkplain AudioFileFormat} by
     * returning a new temporary file whose content is the result of the
     * conversion. The temporary file returned needs to be destroyed explicitly
     * by calling "file.delete()".
     *
     * @param source file whose content is going to be converted.
     * @param targetType to which the source's going to be converted.
     * @return temporary file containing the source's content converted to the
     *         targetType desired.
     * @throws ConversionException if any conversion error occurs.
     */
    @Override
    File convert(
        File source,
        AudioFileFormat targetType )
        throws ConversionException;

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
