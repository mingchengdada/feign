package com.mc.kafkascript.cmvideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Result.
 */
@Data
@NoArgsConstructor
public class Result {
    private String resultCode;
    private String resultMessage;



    /**
     * Instantiates a new Result.
     *
     * @param resultCode the result code
     * @param resultDes  the result des
     */
    public Result(String resultCode, String resultDes) {
        this.resultCode = resultCode;
        this.resultMessage = resultDes;
    }

}
