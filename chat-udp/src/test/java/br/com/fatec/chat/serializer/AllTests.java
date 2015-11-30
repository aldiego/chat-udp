/**
 * 
 */
package br.com.fatec.chat.serializer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Diego
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ KeepAliveJSONSerializerTest.class, LeaveJSONSerializerTest.class, ReportJSONSerializerTest.class,
		SayJSONSerializerTest.class, SearchJSONSerializerTest.class, WhisperJSONSerializerTest.class })
public class AllTests {

}
