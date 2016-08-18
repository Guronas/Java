package ru.ncedu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Frolov Maksim on 07.05.2016.
 */
public class CheckerImpl implements Checker{
    public CheckerImpl(){}

    @Override
    public Pattern getPLSQLNamesPattern() {
        return Pattern.compile("^[a-zA-Z][a-zA-Z0-9_$]{0,29}$");
    }

    @Override
    public Pattern getHrefURLPattern() {
        return Pattern.compile("^<?[aA]\\s*[hH][rR][eE][fF]\\s*=\\s*([a-zA-Z0-9.-_#]*||\"[a-zA-Z0-9.-_#]*\")\\s*>?$");
    }

    @Override
    public Pattern getEMailPattern() {
        return Pattern.compile("^[a-zA-Z0-9](\\w|\\.|-){0,20}[a-zA-Z0-9]@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]\\.)+(ru|com|net|org)$");
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if ((inputString == null && pattern != null) || (inputString != null && pattern == null)){
        throw new IllegalArgumentException();
        } else if (inputString == null) {
            return true;
        }
        return pattern.matcher(inputString).matches();
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        if (inputString == null || pattern == null)
        {
            throw new IllegalArgumentException();
        }

        List<String> result = new ArrayList<>();
        Matcher m = pattern.matcher(inputString);
        while (m.find()) {
            result.add(m.group(0));
        }
        return result;
    }
}
