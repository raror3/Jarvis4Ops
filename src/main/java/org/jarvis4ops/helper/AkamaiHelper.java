package org.jarvis4ops.helper;

import org.jarvis4ops.bean.GtmResources;
import org.jarvis4ops.bean.GtmResponse;
import org.jarvis4ops.bean.Properties;
import org.jarvis4ops.bean.TrafficTargets;
import org.jarvis4ops.configurations.AkamaiConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class AkamaiHelper {
	
	@Autowired
	private AkamaiConfigurations configObj;
	
	public GtmResponse setGtmResponse(ResponseEntity<Properties> response1) {
		GtmResponse gtm = new GtmResponse();
		gtm.setName(response1.getBody().getName());
		final java.util.List<TrafficTargets> trafficList = response1.getBody().getTrafficTargets();
		for (final TrafficTargets trafficTargets : trafficList) {
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterH5()) {
				gtm.setProd(trafficTargets.getWeight());
			}
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterH8()) {
				gtm.setBurst(trafficTargets.getWeight());
			}
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterDR()) {
				gtm.setDr(trafficTargets.getWeight());
			}

		}
		gtm.setLastModified(response1.getBody().getLastModified());

		return gtm;
	}
	
	public GtmResponse setGtmSwitchResponse(ResponseEntity<GtmResources> response1) {
		GtmResponse gtm = new GtmResponse();
		gtm.setName(response1.getBody().getResource().getName());
		gtm.setLastModified(response1.getBody().getResource().getLastModified());
		final java.util.List<TrafficTargets> trafficList = response1.getBody().getResource().getTrafficTargets();
		for (final TrafficTargets trafficTargets : trafficList) {
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterH5()) {
				gtm.setProd(trafficTargets.getWeight());
			}
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterH8()) {
				gtm.setBurst(trafficTargets.getWeight());
			}
			if (trafficTargets.getDatacenterId() == configObj.getDataCenterDR()) {
				gtm.setDr(trafficTargets.getWeight());
			}

		}

		return gtm;
	}
	
	/**
	 * @param entity
	 * @param restTemplate
	 * @return
	 */
	public GtmResponse invokeApi(HttpEntity<String> entity, RestTemplate restTemplate, String url, HttpMethod httpMethod) {
		ResponseEntity<Properties> apiResponse = restTemplate.exchange(url, httpMethod, entity, Properties.class);
		Gson gson = new Gson();
		System.out.println("Response JSON for gtm: " + gson.toJson(apiResponse));
		GtmResponse gtm = setGtmResponse(apiResponse);
		return gtm;
		
	}
	
	/**
	 * @param entity
	 * @param restTemplate
	 * @return
	 */
	public GtmResponse invokeApiForGtmChange(HttpEntity<String> entity, RestTemplate restTemplate, String url, HttpMethod httpMethod) {
		ResponseEntity<GtmResources> apiResponse = restTemplate.exchange(url, httpMethod, entity, GtmResources.class);
		GtmResponse gtm = setGtmSwitchResponse(apiResponse);
		return gtm;
		
	}
	


}
