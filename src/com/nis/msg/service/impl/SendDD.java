package com.nis.msg.service.impl;

import com.nis.msg.service.impl.SendWebCat;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendDD {
	private static final Logger c = Logger.getLogger(SendWebCat.class);

	public String a(String url, String qname, String appid, String userid, String deptid, String message_url,
			String head_bgcolor, String head_title, String body_content, List<String> keyLst, List<String> valueLst) {
		try {
			Service e = new Service();
			Call call = (Call) e.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(new QName("http://tempuri.org/", "SendOAMsg"));
			call.addParameter(new QName("http://tempuri.org/", "appid"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "deptid"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "useid"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "message_url"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "head_bgcolor"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "head_title"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "body_content"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "keyLst"), XMLType.XSD_ANYTYPE, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "valueLst"), XMLType.XSD_ANYTYPE, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_BOOLEAN);
			call.setSOAPActionURI("http://tempuri.org/SendOAMsg");
			Object result = call.invoke(new Object[]{appid, deptid, userid, message_url, head_bgcolor, head_title,
					body_content, keyLst, valueLst});
			System.out.println(result);
			c.info(result + "--------------------------------------------------");
		} catch (Exception arg14) {
			c.error("-------------调用钉钉接口失败", arg14);
			arg14.printStackTrace();
		}

		return null;
	}
}