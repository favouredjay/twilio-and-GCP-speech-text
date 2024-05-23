package com.example.speechtotextwithtwilio;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileInputStream;
@Slf4j
@Service
public class TranscribeService {


    public String transcribeFileWithAutomaticPunctuation(MultipartFile fileName) throws Exception {
        byte[] content = fileName.getBytes();

        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new FileInputStream("path to google credentials"));

        SpeechSettings speechSettings =
                SpeechSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                        .build();

        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {

            RecognitionConfig recConfig =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(8000)
                            .setAudioChannelCount(2)
                            .setEnableSeparateRecognitionPerChannel(true)
                            .setEnableAutomaticPunctuation(true)
                            .build();
            RecognitionAudio recognitionAudio =
                    RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();
            RecognizeResponse recognizeResponse = speechClient.recognize(recConfig, recognitionAudio);
            SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            log.info("Transcript : " + alternative.getTranscript());
            return alternative.getTranscript();
        }
    }
}
