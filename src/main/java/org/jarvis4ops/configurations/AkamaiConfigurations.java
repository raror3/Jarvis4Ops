/**
 * 
 */
package org.jarvis4ops.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bkaur2
 *
 */
@Component
@ConfigurationProperties(prefix="org.ops4_0.constants")
public class AkamaiConfigurations {

	private String port;
	private String host;
	private String akamaiCreds;
	private String akamaiStageCs;
	private String akamaiStageCommerceApi;
	private String akamaiStageStore;
	private String akamaiStageAsset2;
	private String akamaiStageAsset1;
	private String akamaiStage;
	private String akamaiCheckCs;
	private String akamaiCheckCommerceApi;
	private String akamaiCheckStore;
	private String akamaiCheckAsset2;
	private String akamaiCheckAsset1;
	private String akamaiCheck;
	private String akamaiCs;
	private String akamaiCommerceApi;
	private String akamaiStore;
	private String akamaiAsset2;
	private String akamaiAsset1;
	private String akamaiWww;
	private String akamaiShopSearch;
	private Integer DataCenterH5;
	private Integer DataCenterH8;
	private Integer DataCenterDR;
	private String akamaiStageShopSearch;
	private String akamaiStageCheckCs;
	private String akamaiStageCheckCommerceApi;
	private String akamaiStageCheckStore;
	private String akamaiStageCheckAsset2;
	private String akamaiStageCheckAsset1;
	private String akamaiStageCheck;

	

	public String getAkamaiCs() {
		return akamaiCs;
	}

	public void setAkamaiCs(String akamaiCs) {
		this.akamaiCs = akamaiCs;
	}

	public String getAkamaiCommerceApi() {
		return akamaiCommerceApi;
	}

	public void setAkamaiCommerceApi(String akamaiCommerceApi) {
		this.akamaiCommerceApi = akamaiCommerceApi;
	}

	public String getAkamaiStore() {
		return akamaiStore;
	}

	public void setAkamaiStore(String akamaiStore) {
		this.akamaiStore = akamaiStore;
	}

	public String getAkamaiAsset2() {
		return akamaiAsset2;
	}

	public void setAkamaiAsset2(String akamaiAsset2) {
		this.akamaiAsset2 = akamaiAsset2;
	}

	public String getAkamaiWww() {
		return akamaiWww;
	}

	public void setAkamaiWww(String akamaiWww) {
		this.akamaiWww = akamaiWww;
	}

	public String getAkamaiStageCs() {
		return akamaiStageCs;
	}

	public void setAkamaiStageCs(String akamaiStageCs) {
		this.akamaiStageCs = akamaiStageCs;
	}

	public String getAkamaiStageCommerceApi() {
		return akamaiStageCommerceApi;
	}

	public void setAkamaiStageCommerceApi(String akamaiStageCommerceApi) {
		this.akamaiStageCommerceApi = akamaiStageCommerceApi;
	}

	public String getAkamaiStageStore() {
		return akamaiStageStore;
	}

	public void setAkamaiStageStore(String akamaiStageStore) {
		this.akamaiStageStore = akamaiStageStore;
	}

	public String getAkamaiStageAsset2() {
		return akamaiStageAsset2;
	}

	public void setAkamaiStageAsset2(String akamaiStageAsset2) {
		this.akamaiStageAsset2 = akamaiStageAsset2;
	}

	public String getAkamaiStageAsset1() {
		return akamaiStageAsset1;
	}

	public void setAkamaiStageAsset1(String akamaiStageAsset1) {
		this.akamaiStageAsset1 = akamaiStageAsset1;
	}

	public String getAkamaiStage() {
		return akamaiStage;
	}

	public void setAkamaiStage(String akamaiStage) {
		this.akamaiStage = akamaiStage;
	}

	

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}


	/**
	 * @return the akamaiCreds
	 */
	public String getAkamaiCreds() {
		return akamaiCreds;
	}

	/**
	 * @param akamaiCreds the akamaiCreds to set
	 */
	public void setAkamaiCreds(String akamaiCreds) {
		this.akamaiCreds = akamaiCreds;
	}

	/**
	 * @return the akamaiShopSearch
	 */
	public String getAkamaiShopSearch() {
		return akamaiShopSearch;
	}

	/**
	 * @param akamaiShopSearch the akamaiShopSearch to set
	 */
	public void setAkamaiShopSearch(String akamaiShopSearch) {
		this.akamaiShopSearch = akamaiShopSearch;
	}

	
	/**
	 * @return the akamaiCheckCs
	 */
	public String getAkamaiCheckCs() {
		return akamaiCheckCs;
	}

	/**
	 * @param akamaiCheckCs the akamaiCheckCs to set
	 */
	public void setAkamaiCheckCs(String akamaiCheckCs) {
		this.akamaiCheckCs = akamaiCheckCs;
	}

	/**
	 * @return the akamaiCheckCommerceApi
	 */
	public String getAkamaiCheckCommerceApi() {
		return akamaiCheckCommerceApi;
	}

	/**
	 * @param akamaiCheckCommerceApi the akamaiCheckCommerceApi to set
	 */
	public void setAkamaiCheckCommerceApi(String akamaiCheckCommerceApi) {
		this.akamaiCheckCommerceApi = akamaiCheckCommerceApi;
	}

	/**
	 * @return the akamaiCheckStore
	 */
	public String getAkamaiCheckStore() {
		return akamaiCheckStore;
	}

	/**
	 * @param akamaiCheckStore the akamaiCheckStore to set
	 */
	public void setAkamaiCheckStore(String akamaiCheckStore) {
		this.akamaiCheckStore = akamaiCheckStore;
	}

	/**
	 * @return the akamaiCheckAsset2
	 */
	public String getAkamaiCheckAsset2() {
		return akamaiCheckAsset2;
	}

	/**
	 * @param akamaiCheckAsset2 the akamaiCheckAsset2 to set
	 */
	public void setAkamaiCheckAsset2(String akamaiCheckAsset2) {
		this.akamaiCheckAsset2 = akamaiCheckAsset2;
	}

	/**
	 * @return the akamaiCheckAsset1
	 */
	public String getAkamaiCheckAsset1() {
		return akamaiCheckAsset1;
	}

	/**
	 * @param akamaiCheckAsset1 the akamaiCheckAsset1 to set
	 */
	public void setAkamaiCheckAsset1(String akamaiCheckAsset1) {
		this.akamaiCheckAsset1 = akamaiCheckAsset1;
	}

	/**
	 * @return the akamaiCheck
	 */
	public String getAkamaiCheck() {
		return akamaiCheck;
	}

	/**
	 * @param akamaiCheck the akamaiCheck to set
	 */
	public void setAkamaiCheck(String akamaiCheck) {
		this.akamaiCheck = akamaiCheck;
	}

	
	/**
	 * @return the dataCenterH5
	 */
	public Integer getDataCenterH5() {
		return DataCenterH5;
	}

	/**
	 * @param dataCenterH5 the dataCenterH5 to set
	 */
	public void setDataCenterH5(Integer dataCenterH5) {
		DataCenterH5 = dataCenterH5;
	}

	/**
	 * @return the dataCentersH8
	 */
	public Integer getDataCenterH8() {
		return DataCenterH8;
	}

	/**
	 * @param dataCentersH8 the dataCentersH8 to set
	 */
	public void setDataCenterH8(Integer dataCenterH8) {
		DataCenterH8 = dataCenterH8;
	}

	/**
	 * @return the akamaiAsset1
	 */
	public String getAkamaiAsset1() {
		return akamaiAsset1;
	}

	/**
	 * @param akamaiAsset1 the akamaiAsset1 to set
	 */
	public void setAkamaiAsset1(String akamaiAsset1) {
		this.akamaiAsset1 = akamaiAsset1;
	}

	/**
	 * @return the akamaiStageShopSearch
	 */
	public String getAkamaiStageShopSearch() {
		return akamaiStageShopSearch;
	}

	/**
	 * @param akamaiStageShopSearch the akamaiStageShopSearch to set
	 */
	public void setAkamaiStageShopSearch(String akamaiStageShopSearch) {
		this.akamaiStageShopSearch = akamaiStageShopSearch;
	}

	/**
	 * @return the dataCenterDR
	 */
	public Integer getDataCenterDR() {
		return DataCenterDR;
	}

	/**
	 * @param dataCenterDR the dataCenterDR to set
	 */
	public void setDataCenterDR(Integer dataCenterDR) {
		DataCenterDR = dataCenterDR;
	}

	/**
	 * @return the akamaiStageCheckCs
	 */
	public String getAkamaiStageCheckCs() {
		return akamaiStageCheckCs;
	}

	/**
	 * @param akamaiStageCheckCs the akamaiStageCheckCs to set
	 */
	public void setAkamaiStageCheckCs(String akamaiStageCheckCs) {
		this.akamaiStageCheckCs = akamaiStageCheckCs;
	}

	/**
	 * @return the akamaiStageCheckCommerceApi
	 */
	public String getAkamaiStageCheckCommerceApi() {
		return akamaiStageCheckCommerceApi;
	}

	/**
	 * @param akamaiStageCheckCommerceApi the akamaiStageCheckCommerceApi to set
	 */
	public void setAkamaiStageCheckCommerceApi(String akamaiStageCheckCommerceApi) {
		this.akamaiStageCheckCommerceApi = akamaiStageCheckCommerceApi;
	}

	/**
	 * @return the akamaiStageCheckStore
	 */
	public String getAkamaiStageCheckStore() {
		return akamaiStageCheckStore;
	}

	/**
	 * @param akamaiStageCheckStore the akamaiStageCheckStore to set
	 */
	public void setAkamaiStageCheckStore(String akamaiStageCheckStore) {
		this.akamaiStageCheckStore = akamaiStageCheckStore;
	}

	/**
	 * @return the akamaiStageCheckAsset2
	 */
	public String getAkamaiStageCheckAsset2() {
		return akamaiStageCheckAsset2;
	}

	/**
	 * @param akamaiStageCheckAsset2 the akamaiStageCheckAsset2 to set
	 */
	public void setAkamaiStageCheckAsset2(String akamaiStageCheckAsset2) {
		this.akamaiStageCheckAsset2 = akamaiStageCheckAsset2;
	}

	/**
	 * @return the akamaiStageCheckAsset1
	 */
	public String getAkamaiStageCheckAsset1() {
		return akamaiStageCheckAsset1;
	}

	/**
	 * @param akamaiStageCheckAsset1 the akamaiStageCheckAsset1 to set
	 */
	public void setAkamaiStageCheckAsset1(String akamaiStageCheckAsset1) {
		this.akamaiStageCheckAsset1 = akamaiStageCheckAsset1;
	}

	/**
	 * @return the akamaiStageCheck
	 */
	public String getAkamaiStageCheck() {
		return akamaiStageCheck;
	}

	/**
	 * @param akamaiStageCheck the akamaiStageCheck to set
	 */
	public void setAkamaiStageCheck(String akamaiStageCheck) {
		this.akamaiStageCheck = akamaiStageCheck;
	}


}
