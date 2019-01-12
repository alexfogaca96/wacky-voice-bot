package br.com.sucrilhos.wacky.sound.converter;

public interface AudioFormatConverter<S, T>
{
    void convert(
        S source,
        T target )
        throws ConversionException;
}
