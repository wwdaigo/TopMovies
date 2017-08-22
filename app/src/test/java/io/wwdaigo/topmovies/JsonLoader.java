package io.wwdaigo.topmovies;

import com.google.common.io.ByteStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by daigomatsuoka on 22/08/17.
 */

public class JsonLoader {

    public String loadJson(String filename) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        return new String(ByteStreams.toByteArray(in ));
    }
}