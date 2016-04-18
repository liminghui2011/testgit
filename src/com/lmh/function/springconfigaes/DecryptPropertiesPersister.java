package com.lmh.function.springconfigaes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.StringUtils;

public class DecryptPropertiesPersister extends DefaultPropertiesPersister
{
    @Override
    protected void doLoad(Properties props, Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            String line = in.readLine();
            if (line == null) {
                return;
            }
            line = StringUtils.trimLeadingWhitespace(line);
            if (line.length() == 0) {
                continue;
            }
            char firstChar = line.charAt(0);
            if (firstChar != '#' && firstChar != '!') {
                while (endsWithContinuationMarker(line)) {
                    String nextLine = in.readLine();
                    line = line.substring(0, line.length() - 1);
                    if (nextLine != null) {
                        line += StringUtils.trimLeadingWhitespace(nextLine);
                    }
                }
                int separatorIndex = line.indexOf("=");
                if (separatorIndex == -1) {
                    separatorIndex = line.indexOf(":");
                }
                String key = (separatorIndex != -1 ? line.substring(0, separatorIndex) : line);
                String value = (separatorIndex != -1) ? line.substring(separatorIndex + 1) : "";
                key = StringUtils.trimTrailingWhitespace(key);

                value = StringUtils.trimLeadingWhitespace(value);
                
                value = decrypt("lutongnet", value);
                props.put(unescape(key), unescape(value));
            }
        }
    }
    
    private String decrypt(String key, String str) {
        if(str == null || "".equals(str.trim())) {
            return "";
        }
        
        //后缀为{AES128}的字符串为需要解密的资源
        if(str.endsWith("{AES128}")) {
            str = Aes128Utils.decrypt(str, key);
        } 
        return str;
    }
}