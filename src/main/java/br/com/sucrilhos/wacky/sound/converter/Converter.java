package br.com.sucrilhos.wacky.sound.converter;

public interface Converter<V, S, T>
{
    T convert(
        S source,
        V targetType )
        throws ConversionException;
}
