package bll;

import java.util.List;

public class VerificationMsgEtBoolean {
	private List<String> listeMsgErreurs;
	private boolean test;
	
	public VerificationMsgEtBoolean(boolean t, List<String> ls) {
		this.listeMsgErreurs = ls;
		this.test=t;
	}

	public List<String> getListeMsgErreurs() {
		return listeMsgErreurs;
	}

	public boolean isTest() {
		return test;
	}

}
