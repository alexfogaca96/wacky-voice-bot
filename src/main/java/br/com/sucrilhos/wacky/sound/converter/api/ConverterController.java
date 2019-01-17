package br.com.sucrilhos.wacky.sound.converter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sucrilhos.wacky.sound.converter.ConversionException;
import br.com.sucrilhos.wacky.sound.converter.format.AudioFileFormat;

@RestController
public class ConverterController
{
    @Autowired
    private ConverterService converterService;

    @PostMapping(
        path = "/convert",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
        headers = "Cache-Control: private" )
    public ResponseEntity<ByteArrayResource> convertAudioFileFormat(
        @RequestParam( "target.format" ) final AudioFileFormat targetFormat,
        @RequestPart( "source.file" ) final MultipartFile sourceFile )
        throws ConversionException
    {
        return ResponseEntity.ok( new ByteArrayResource( converterService.convertAudioFileFormat( targetFormat, sourceFile ) ) );
    }
}
