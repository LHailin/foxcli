package linux.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import linux.Common;

public class Phy {
	private String phyName;
	private String sasAddress;
	private String phyIdentifier;
	private String invalidDwordCount;
	private String lossOfDwordSyncCount;
	private String phyResetProblemCount;
	private String runningDisparityErrorCount;
	
	private String rootPath;
	
	public Phy(String phy_name){
		setPhyName(phy_name);
		setRootPath(Common.pathJoin("/sys/class/sas_phy", phy_name));
	}
	
	public static ArrayList<String> phyList() throws Exception{
		ArrayList<String> phys = new ArrayList<String>();
		phys = Common.listDirAllFiles("/sys/class/sas_phy");
		return phys;
	}
	
	public static Map<String, Map<String, String>>getAllPhysAttr() throws Exception{
		Map<String, Map<String, String>> physAttrMap = new HashMap<String, Map<String,String>>();
		Map<String, String> phyAttrMap = new HashMap<String, String>();
		ArrayList<String> phyNames = Phy.phyList();
		for(String phy: phyNames){
			Phy p = new Phy(phy);
			p.fillAttr();
			phyAttrMap.put("sasAddress", p.sasAddress);
			phyAttrMap.put("invalid_dword_count", p.invalidDwordCount);
			phyAttrMap.put("loss_of_dword_sync_count", p.lossOfDwordSyncCount);
			phyAttrMap.put("phy_identifier", p.phyIdentifier);
			phyAttrMap.put("phy_reset_problem_count", p.phyResetProblemCount);
			phyAttrMap.put("running_disparity_error_count", p.runningDisparityErrorCount);
			physAttrMap.put(phy, phyAttrMap);
		}
		return physAttrMap;
	}
	
	public static String getAllPhysAttrJson() throws Exception{
		Map<String, Map<String, String>> map = Phy.getAllPhysAttr();
		ObjectMapper mapper = new ObjectMapper();
		String  jsonStr = mapper.writeValueAsString(map);
		return jsonStr;
	}
	
	public void fillAttr() throws Exception{
		fillSasAddress();
		fillInvalidDwordCount();
		fillLossOfDwordSyncCount();
		fillPhyIdentifier();
		fillPhyResetProblemCount();
		fillRunningDisparityErrorCount();
	}
	
	public void fillSasAddress() throws Exception{
		sasAddress = Common.readFileByChar(Common.pathJoin(rootPath, "sas_address"));
	}
	
	public void fillPhyIdentifier() throws Exception{
		phyIdentifier = Common.readFileByChar(Common.pathJoin(rootPath, "phy_identifier"));
	}
	
	public void fillInvalidDwordCount() throws Exception{
		phyIdentifier = Common.readFileByChar(Common.pathJoin(rootPath, "invalid_dword_count"));
	}
	
	public void fillLossOfDwordSyncCount() throws Exception{
		phyIdentifier = Common.readFileByChar(Common.pathJoin(rootPath, "loss_of_dword_sync_count"));
	}
	
	public void fillPhyResetProblemCount() throws Exception{
		phyIdentifier = Common.readFileByChar(Common.pathJoin(rootPath, "phy_reset_problem_count"));
	}
	
	public void fillRunningDisparityErrorCount() throws Exception{
		phyIdentifier = Common.readFileByChar(Common.pathJoin(rootPath, "running_disparity_error_count"));
	}
	
	public String getPhyName() {
		return phyName;
	}
	public void setPhyName(String phyName) {
		this.phyName = phyName;
	}
	public String getSasAddress() {
		return sasAddress;
	}
	public void setSasAddress(String sasAddress) {
		this.sasAddress = sasAddress;
	}
	public String getPhyIdentifier() {
		return phyIdentifier;
	}
	public void setPhyIdentifier(String phyIdentifier) {
		this.phyIdentifier = phyIdentifier;
	}
	public String getInvalidDwordCount() {
		return invalidDwordCount;
	}
	public void setInvalidDwordCount(String invalidDwordCount) {
		this.invalidDwordCount = invalidDwordCount;
	}
	public String getLossOfDwordSyncCount() {
		return lossOfDwordSyncCount;
	}
	public void setLossOfDwordSyncCount(String lossOfDwordSyncCount) {
		this.lossOfDwordSyncCount = lossOfDwordSyncCount;
	}
	public String getPhyResetProblemCount() {
		return phyResetProblemCount;
	}
	public void setPhyResetProblemCount(String phyResetProblemCount) {
		this.phyResetProblemCount = phyResetProblemCount;
	}
	public String getRunningDisparityErrorCount() {
		return runningDisparityErrorCount;
	}
	public void setRunningDisparityErrorCount(String runningDisparityErrorCount) {
		this.runningDisparityErrorCount = runningDisparityErrorCount;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
}
