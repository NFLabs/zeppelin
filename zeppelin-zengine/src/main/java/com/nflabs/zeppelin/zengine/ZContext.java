package com.nflabs.zeppelin.zengine;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkContext;

import com.nflabs.zeppelin.driver.TableInfo;

/**
 * Zeppelin Context. This context is passed to Zeppelin UDF's ZQL template
 * @author moon
 *
 */
public class ZContext {
	public String in;
	public String out;
	public String arg;
	private Map<String, Object> params;
	public TableInfo inInfo;
	public TableInfo outInfo;
	public SparkContext sc;
	
	Map<String, ParamInfo> paramInfos = new HashMap<String, ParamInfo>();
	/**
	 * Initialize Zeppelin Context
	 * @param tableIn input table name
	 * @param tableOut output table name
	 * @param arg arguments
	 * @param params parameters to UDF
	 */
	public ZContext(String tableIn, TableInfo inInfo, String tableOut, TableInfo outInfo, String arg, Map<String, Object> params){
		this.in = tableIn;
		this.out = tableOut;
		this.inInfo = inInfo;
		this.outInfo = outInfo;
		this.arg = arg;
		this.params = params;
	}
	
	public Map<String, ParamInfo> getParamInfos(){
		return paramInfos;
	}
	
	/**
	 * Get params;
	 * @return 
	 */
	public Object param(String name){
		return param(name, null);
	}

	/**
	 * Get params;
	 * @param name name of parameter
	 * @param defaultValue defaultValue of the param
	 * @return 
	 */
	public Object param(String name, Object defaultValue){
		if(paramInfos.containsKey(name)==false){
			paramInfos.put(name, new ParamInfo(name, defaultValue));
		}
			
		Object r = params.get(name);
		
		if(r==null){
			return defaultValue;
		} else {
			return r;
		}
	}
	
	/**
	 * Get input table name
	 * @return
	 */
	public String in(){
		return in;
	}
	
	/**
	 * Get output table name
	 * @return
	 */
	public String out(){
		return out;
	}
	
	/**
	 * Get arguments
	 * @return
	 */
	public String arg(){
		return arg;
	}

	public TableInfo getInInfo() {
		return inInfo;
	}

	public void setInInfo(TableInfo inInfo) {
		this.inInfo = inInfo;
	}

	public TableInfo getOutInfo() {
		return outInfo;
	}

	public void setOutInfo(TableInfo outInfo) {
		this.outInfo = outInfo;
	}
	
	public SparkContext sc(){
		return sc;
	}
	
	
}
