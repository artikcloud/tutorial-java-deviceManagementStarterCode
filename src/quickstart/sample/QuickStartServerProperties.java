/**
 * This sample demonstrates read/write calls to your device server properties.
 * 
 * Please check readme for additional setup steps required before running this code.
 */


package quickstart.sample;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import cloud.artik.api.DevicesManagementApi;
import cloud.artik.client.ApiCallback;
import cloud.artik.client.ApiClient;
import cloud.artik.client.ApiException;
import cloud.artik.model.MetadataEnvelope;
import quickstart.devicemanagment.ClientHelper;
import quickstart.devicemanagment.Config;
import quickstart.devicemanagment.MyServerProperty;


public class QuickStartServerProperties {
	
	public static void main(String args[]) throws ApiException {
		
		//init client and required api
		ApiClient apiClient = null;
		ClientHelper.initClient(apiClient);
		DevicesManagementApi devicesManagementApi = new DevicesManagementApi();
		Boolean includeTimestamp = false;
		
		//sample calls read/update server properties()
		MetadataEnvelope result1_BeforeUpdate = devicesManagementApi.getProperties(Config.DEVICE_ID, includeTimestamp);
		MetadataEnvelope result1_OnUpdate = devicesManagementApi.updateServerProperties(Config.DEVICE_ID, randomServerProperty());
		MetadataEnvelope result1_AfterUpdate = devicesManagementApi.getProperties(Config.DEVICE_ID, includeTimestamp);
		
		//callback for async 
		ApiCallback<MetadataEnvelope> callback = new ApiCallback<MetadataEnvelope>(){

			@Override
			public void onFailure(ApiException error, int arg1, Map<String, List<String>> arg2) {
				System.out.println("Async: error===>>>>" + error);
			}

			@Override
			public void onSuccess(MetadataEnvelope response, int arg1, Map<String, List<String>> arg2) {
				System.out.println("Async: Async callback===>>>>" + response);
			}

			@Override
			public void onDownloadProgress(long arg0, long arg1, boolean arg2) {

			}

			@Override
			public void onUploadProgress(long arg0, long arg1, boolean arg2) {

			}

		};
		
		//sample async update
		devicesManagementApi.updateServerPropertiesAsync(Config.DEVICE_ID, randomServerProperty(), callback);
		devicesManagementApi.getPropertiesAsync(Config.DEVICE_ID, includeTimestamp, callback);
	
		
		//print results for call 1
		print("1: getProperties()", result1_BeforeUpdate);
		print("1: updateServerProperties()", result1_OnUpdate);
		print("1: resultAfterUpate()", result1_AfterUpdate);
		
	}

	private static void print(String description, MetadataEnvelope result) {
		System.out.println("Called " + description +  "===>");
		System.out.println("Result " + result);
	}
	
	private static MyServerProperty randomServerProperty() {
		
		return new MyServerProperty(
				UUID.randomUUID().toString(), false, (int) Math.round(Math.random()*100));
	}
	
}
