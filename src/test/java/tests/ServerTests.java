package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Server;

public class ServerTests {

    @Test
    public void testSetSetting(){
        int expected = 8090;
        Assertions.assertEquals(expected,Server.setSetting("C:\\Users\\lshap\\IdeaProjects\\server\\src\\test\\java\\tests\\settings.txt"));
    }

}
