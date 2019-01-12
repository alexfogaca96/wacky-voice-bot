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
        final FormattingConversionService f = super.mvcConversionService();
        f.addConverter( new AudioFileFormatEnumConverter() );
        return f;
    }
}
