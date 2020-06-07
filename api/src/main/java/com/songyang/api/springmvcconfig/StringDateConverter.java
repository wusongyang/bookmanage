package com.songyang.api.springmvcconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class StringDateConverter implements Converter<String, Date> {
    Logger logger = LoggerFactory.getLogger(StringDateConverter.class);
    @Override
    public Date convert(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            logger.error("",e);
        }
        return null;
    }
}