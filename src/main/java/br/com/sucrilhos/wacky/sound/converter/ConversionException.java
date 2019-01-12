package br.com.sucrilhos.wacky.sound.converter;

public class ConversionException
    extends
        Exception
{
    private static final long serialVersionUID = 1L;

    public ConversionException()
    {
        super();
    }

    public ConversionException(
        final String message )
    {
        super( message );
    }

    public ConversionException(
        final String message,
        final Throwable cause )
    {
        super( message, cause );
    }

    public ConversionException(
        final Throwable cause )
    {
        super( cause );
    }
}
