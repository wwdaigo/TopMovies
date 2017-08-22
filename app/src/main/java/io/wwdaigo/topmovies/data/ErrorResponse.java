package io.wwdaigo.topmovies.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daigomatsuoka on 20/08/17.
 */

public final class ErrorResponse {
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("status_message")
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}