package br.com.fatec.chat.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidationUtilTest {

	@Test
	public void testIpLocalHostValido() {
		assertTrue(ValidationUtil.isValidInetAddress("127.0.0.1"));
	}
	
	@Test
	public void testIpInvalido() {
		assertFalse(ValidationUtil.isValidInetAddress("0.0.1"));
	}

}
