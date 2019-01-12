package br.com.sucrilhos.wacky.sound.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WackySoundController
{
    @Autowired
    private WackSoundService wackSoundService;

    // TODO: endpoint para receber mensagem (json) e delegar ao WackSoundService
}
