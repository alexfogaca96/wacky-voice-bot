package br.com.sucrilhos.wacky.sound.converter.command.audio;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;

public interface ConversionCommand<V, S, T>
{
    boolean isSupported(
        V sourceType,
        V targetType );

    T execute(
        S source,
        V targetType )
        throws ConversionException;
}
