package br.com.gympass;

import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import org.junit.Test;

public class CorridaStarterTest {

  @Test
  public void testSuccess() throws Exception {
    final String projectDir = System.getProperty("user.dir");
    CorridaStarter.main(new String[]{projectDir + "/target/classes/corrida.txt"});
  }

  @Test
  public void testFileNotFound() throws UnsupportedEncodingException {

    try {
      CorridaStarter.main(null);
      Assert.fail();
    } catch (Exception e) {
      Assert.assertNotNull(e.getMessage());
      Assert.assertEquals(e.getMessage(), "File not found");
    }
  }

  @Test
  public void testFileNotRead()  {

    try {
      CorridaStarter.main(new String[] { "myamazingfile"});
      Assert.fail();
    } catch (Exception e) {
      Assert.assertNotNull(e.getMessage());
      Assert.assertEquals(e.getMessage(), "Could not be possible to parse the file");
    }
  }

}
