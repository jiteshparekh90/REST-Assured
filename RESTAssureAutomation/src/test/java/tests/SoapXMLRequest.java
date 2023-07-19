package tests;

import org.codehaus.plexus.util.IOUtil;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapXMLRequest {

	@Test
	public void validateSoapXML() throws IOException {
		
		File file = new File("./SoapRequest/Add.xml");
		
		if(file.exists())
			System.out.println("  >> File Exists ");
		
		FileInputStream fileInputSteam = new FileInputStream(file);
		String requestBody = IOUtil.toString(fileInputSteam, "UTF-8");
		
		baseURI = "http://www.dneonline.com";
		
		given().
		contentType("text/xml").
		accept(ContentType.XML).
		body(requestBody).
		when().post("/calculator.asmx").
		then().
		statusCode(200).log().all().and()
		.body("//*:AddResult.text()", equalTo("5"));
	}
}
