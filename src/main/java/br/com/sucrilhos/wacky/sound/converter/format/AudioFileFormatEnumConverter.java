package br.com.sucrilhos.wacky.sound.converter.format;

import org.springframework.core.convert.converter.Converter;

class AudioFileFormatEnumConverter
    implements
        Converter<String,AudioFileFormat>
{
    @Override
    public AudioFileFormat convert(
        final String format )
    {
        try {
            return AudioFileFormat.valueOf( format.toUpperCase() );
        } catch( final Exception e ) {
            throw new NullPointerException( "Audio file format given is invalid." );
        }
    }
}
