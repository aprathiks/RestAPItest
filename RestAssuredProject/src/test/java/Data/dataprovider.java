package Data;

import org.testng.annotations.DataProvider;

public class dataprovider {
	  @DataProvider (name="dp")
	  public Object[][] dp() {
		  Object[][]obj = {
				  {"Prat","123","3"},
				  {"Abc","456","56"},
				  {"xyz","789","46"}
		  };
		  return obj;
	  }
	  
	  @DataProvider (name="status")
	  public Object[][] stat() {
		  Object[][]obj = {
				  {"available"},
				  {"pending"},
				  {"sold"}
		  };
		  return obj;
	  }

}
