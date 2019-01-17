package br.com.sucrilhos.wacky.sound.converter.format;

import java.io.File;

public enum AudioFileFormat
{
    OGG,
    WAV;

    public static AudioFileFormat getFileFormat(
        final File file )
    {
        final int lastIndexOf = file.getName().lastIndexOf( "." );
        if( lastIndexOf == - 1 ) {
            throw new IllegalArgumentException( "File doesn't have an extension." );
        }
        return AudioFileFormat.valueOf( file.getName().substring( lastIndexOf + 1 ).toUpperCase() );
    }
}
