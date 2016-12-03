package quickstart.devicemanagment;

/**
 * This is a custom class you create that will be passed to 
 * deviceManagementApi.updateServerProperties(String did, Class customClass)
 * 
 * This class will be JSON serialized when the request is made. 
 *
 */
public class MyServerProperty {
	
	//custom member type/variables that match the server properties you have
	//added after enabling device management properties for your device type
	
	String name;
	Boolean enabled;
	Integer random;

	public MyServerProperty(String name, Boolean enabled, Integer number) {
		
		this.name = name;
		this.enabled = enabled;
		this.random = number;
		
	}
	
}
