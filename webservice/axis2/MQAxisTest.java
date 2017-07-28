package com.fwd.eprecious.util.mq;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class MQAxisTest {

	public static void main(String[] args) throws AxisFault {
		String wsdlUrl = "http://192.168.24.102:8300/mqserviceAgentWS/mqserviceAgentWS?wsdl";

		String nameSpaceUri = "http://www.ing.com.hk/";

		RPCServiceClient serviceClient = new RPCServiceClient();
		org.apache.axis2.client.Options options = serviceClient.getOptions();
		EndpointReference targetEPR = new EndpointReference(wsdlUrl);
		options.setTo(targetEPR);
		QName opGetWeather = new QName(nameSpaceUri, "SubmitGetReply");//命名空间、接口方法
		Object[] opGetWeatherArgs = new Object[] { "", "", "", "", "" };//请求参数
		Class[] returnTypes = new Class[] { Boolean.class,String.class,String.class,String.class };//返回参数
		Object[] response =serviceClient.invokeBlocking(opGetWeather, opGetWeatherArgs, returnTypes);
		Boolean result = (Boolean) response[0];//返回结果
		System.out.println(result);
		System.out.println(response[1]);
		System.out.println(response[2]);
		System.out.println(response[3]);
	}

}
