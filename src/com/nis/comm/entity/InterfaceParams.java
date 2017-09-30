package com.nis.comm.entity;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class InterfaceParams implements Serializable {
	private String interfaceName;
	private String channel;
	private String data;

	public String getInterfaceName() {
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toString() {
		return "InAdapterModel [interfaceName=" + this.interfaceName + ", channel=" + this.channel + ", data="
				+ this.data + "]";
	}
}