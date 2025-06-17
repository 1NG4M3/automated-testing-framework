package gusev.utils;

import gusev.models.RegUser;
import org.apache.commons.lang3.RandomStringUtils;

public class TestUserGenerator {

    public static RegUser generateUniqueUser() {
        String login = "user_" + RandomStringUtils.randomAlphanumeric(8);
        String password = RandomStringUtils.randomAlphanumeric(12);
        return new RegUser(login, password);
    }
}