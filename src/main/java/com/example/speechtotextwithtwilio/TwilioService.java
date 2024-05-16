package com.example.speechtotextwithtwilio;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {


    @Autowired
    private TwilioConfig twilioConfig;



    public String makeCalls(CallDto callRequestDto){

        Call call = Call.creator(
                new PhoneNumber(callRequestDto.getToNumber()),
                        new PhoneNumber(twilioConfig.getFromNumber()),
                        new com.twilio.type.Twiml("https://handler.twilio.com/twiml/EHcfc05e78edc0240bcda0f8b9785ecabf")).setRecord(true)
                    .setRecordingTrack("outbound")
                    .create();
        return call.getSid();
        }
    }

