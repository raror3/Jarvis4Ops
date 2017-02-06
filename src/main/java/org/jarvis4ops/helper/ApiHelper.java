package org.jarvis4ops.helper;

import java.util.Base64;

import org.jarvis4ops.configurations.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiHelper {
	private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private Environment environment;
	
	/**
	 * This method invokes API provided as part of parameter.
	 * @param apiEndPoint String
	 * @throws RestClientException
	 */
	public void invoKeApiService(String apiEndPoint) throws RestClientException {
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl() + apiEndPoint;
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info(apiEndPoint + " API has been invoked via Scheduler with response: " + response);
	}
	
	public MultiValueMap<String, String> setApiAuthHeader(MultiValueMap<String, String> headerMap) {

		String plainCreds = environment.getProperty("security.user.name") + ":" + environment.getProperty("security.user.password");
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		headerMap.add("Authorization", "Basic "+base64Creds);
	    
		return headerMap;
	}
}
