package br.com.sucrilhos.wacky.sound.converter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;

@ControllerAdvice( basePackageClasses = ConverterController.class )
public class ConverterExceptionHandler
{
    @ExceptionHandler( ConversionException.class )
    public ResponseEntity<String> handleException(
        final ConversionException exception )
    {
        return ResponseEntity.badRequest().body( "Conversion wasn't successful." + exception.getCause() );
    }
}
