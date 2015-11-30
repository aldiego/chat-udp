package br.com.fatec.chat.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

public class ValidationUtil {

	public static boolean isValidInetAddress(final String address) {
		if (StringUtils.isBlank(address)) {
			return false;
		}
		if (InetAddressValidator.getInstance().isValid(address)) {
			return true;
		}
		return false;
	}
}