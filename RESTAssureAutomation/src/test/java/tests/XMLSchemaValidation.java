package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.codehaus.plexus.util.IOUtil;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class XMLSchemaValidation {

	@Test
	public void schemaValidation() throws IOException {
		File file = new File("./SoapRequest/Add.xml");

		if (file.exists())
			System.out.println("  >> File Exists ");

		FileInputStream fileInputSteam = new FileInputStream(file);
		String requestBody = IOUtil.toString(fileInputSteam, "UTF-8");

		baseURI = "http://www.dneonline.com";

		given().contentType("text/xml").
		accept(ContentType.XML).
		body(requestBody).when().
		post("/calculator.asmx").then()
		.statusCode(200).log().all()
		.and().
		body("//*:AddResult.text()", equalTo("5")).and()
		.assertThat()
		.body(matchesXsdInClasspath("Calculator.xsd"));
	}
}
