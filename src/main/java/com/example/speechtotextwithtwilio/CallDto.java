package com.example.speechtotextwithtwilio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CallDto {

        String toNumber;
        public CallDto(@JsonProperty("toNumber") String toNumber) {
            this.toNumber = toNumber;
        }
    }


