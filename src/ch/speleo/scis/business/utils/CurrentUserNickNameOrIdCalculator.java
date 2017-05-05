package ch.speleo.scis.business.utils;

import org.apache.commons.lang3.StringUtils;
import org.openxava.calculators.ICalculator;
import org.openxava.util.*;

public class CurrentUserNickNameOrIdCalculator implements ICalculator {

	private static final long serialVersionUID = -6656051521147852137L;

	public Object calculate() throws Exception {
		UserInfo userinfo = Users.getCurrentUserInfo();
		String username = StringUtils.isNotBlank(userinfo.getNickName()) ? userinfo.getNickName() : userinfo.getId();
		return username;
	}
	
}
