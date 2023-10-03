package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        int number = 25;
        boolean b = true;
        float p = 3.14F;
        char c = 'C';
        long l = 2341L;
        byte by = 31;
        short s = 40;
        double d = 3214.332;
        LOG.debug(" Different sings - int : {}, boolean : {}, float : {},"
                + " char : {}, long : {}, byte : {}, short : {}, double : {} ",
                number, b, p, c, l, by, s, d);
    }
}
