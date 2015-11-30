/**
 * 
 */
package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.fatec.chat.model.Report;

/**
 * @author Diego
 *
 */
public class ReportJSONSerializerTest {

	@Test
	public void testReportCompleteSucess() {
		Report reportExpected = new Report("Erro ao processar");

		String reportSerialized = new ReportJSONSerializer().serialize(reportExpected);
		Report reportActual = new ReportJSONSerializer().deserialize(reportSerialized);

		assertEquals(reportExpected, reportActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new ReportJSONSerializer().deserialize(""));

	}

	@Test
	public void testDeserializeSuccess() {
		String report = "{\"action\":\"report\",\"message\": \"JSON parse error\"}";

		Report reportActual = new ReportJSONSerializer().deserialize(report);

		assertEquals(new Report("JSON parse error"), reportActual);

	}

}
