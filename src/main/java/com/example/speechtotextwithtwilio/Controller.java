package com.example.speechtotextwithtwilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class Controller {


    @Autowired
    private TwilioService twilioService;

    @Autowired
    private TranscribeService transcriptionService;

    @PostMapping("/call")
    public ResponseEntity<String> makeOutboundCall(@RequestBody CallDto callRequestDto) {
        String callSid = twilioService.makeCalls(callRequestDto);
        return ResponseEntity.ok("Call Initiated" + callSid);
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribeFile(@RequestPart("file") MultipartFile file) throws Exception {

        String transcript = transcriptionService.transcribeFileWithAutomaticPunctuation(file);
        return ResponseEntity.ok(transcript);

    }
}

