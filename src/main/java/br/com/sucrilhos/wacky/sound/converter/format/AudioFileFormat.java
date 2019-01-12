package br.com.sucrilhos.wacky.sound.converter.format;

import java.io.File;

public enum AudioFileFormat
{
    OGG,
    WAV;

    public static AudioFileFormat getFileExtension(
        final File file )
    {
        final String name = file.getName();
        final int lastIndexOf = name.lastIndexOf( "." );
        if( lastIndexOf == - 1 ) {
            throw new IllegalArgumentException( "File doesn't have an extension." );
        }
        final String extension = name.substring( lastIndexOf + 1 );
        return AudioFileFormat.valueOf( extension.toUpperCase() );
    }
}
