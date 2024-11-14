package com.primalsoup.topo.brasov_route_topo;

public class Greeting {
	private String content;

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Greeting [content=" + content + "]";
	}
}
