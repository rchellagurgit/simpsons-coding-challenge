package com.mycoding.springboot.scc.model;

import java.util.List;

public class AjaxResponseBody {

	String msg;
	List<SimpsonsCharacter> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<SimpsonsCharacter> getResult() {
		return result;
	}

	public void setResult(List<SimpsonsCharacter> result) {
		this.result = result;
	}

}
