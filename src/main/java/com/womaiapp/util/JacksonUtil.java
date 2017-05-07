package com.womaiapp.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.womaiapp.Config;
import org.apache.commons.cli.*;

/**
 * Created by wuchaofei on 2017/5/7.
 */
public class JacksonUtil {

    public static Config loadConfig(String path) {
        ObjectMapper objMapper = new ObjectMapper();
        Config config = null;
        try {
            config = objMapper.readValue(new File(path), Config.class);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) throws ParseException {

    }
}
