package br.com.sucrilhos.wacky.sound.converter.format;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AudioFileFormatEnumConfiguration
    extends
        WebMvcConfigurationSupport
{
    @Override
    public FormattingConversionService mvcConversionService()
    {
        final FormattingConversionService conversionService = super.mvcConversionService();
        conversionService.addConverter( new AudioFileFormatEnumConverter() );
        return conversionService;
    }
}
