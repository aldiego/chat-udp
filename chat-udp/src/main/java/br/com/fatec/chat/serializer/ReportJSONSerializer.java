/**
 * 
 */
package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.Report;

/**
 * @author Diego
 *
 */
public class ReportJSONSerializer implements Serializer<Report> {

	ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fatec.chat.serializer.Serializer#deserialize(byte[])
	 */
	public Report deserialize(String byteArray) {
		try {
			Report report = objectMapper.readValue(byteArray, Report.class);

			return report;

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.fatec.chat.serializer.Serializer#serialize(br.com.fatec.chat.model
	 * .Mensagem)
	 */
	public String serialize(Report report) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, report);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

}
