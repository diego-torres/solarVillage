/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package gov.services.permits.web;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import gov.services.permits.repo.ElectricalPermitRepository;

/**
 * Registers the SOAP Web service handlers.
 * 
 * @author dtorresf@redhat.com
 *
 */
@EnableWs
@Configuration
@ComponentScan(basePackageClasses = { ElectricalPermitRepository.class })
public class WsConfig extends WsConfigurerAdapter {

	/**
	 * Message Dispatcher Servlet.
	 * 
	 * @param applicationContext
	 * @return
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soap/*");
	}

	/**
	 * 
	 * @param electricalPermitSchema
	 * @return
	 */
	@Bean(name = "electricalPermitService")
	public DefaultWsdl11Definition electricalPermitWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ElectricalPermitServicePort");
		wsdl11Definition.setLocationUri("http://localhost:8080/governmentPermitServices/soap/electricalPermitService");
		wsdl11Definition.setTargetNamespace("http://services.gov/permits/electrical");
		wsdl11Definition.setSchema(electricalPermitSchema());
		return wsdl11Definition;
	}

	/**
	 * 
	 * @param electricalPermitSchema
	 * @return
	 */
	@Bean(name = "structuralPermitService")
	public DefaultWsdl11Definition structuralPermitWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StructuralPermitServicePort");
		wsdl11Definition.setLocationUri("http://localhost:8080/governmentPermitServices/soap/structuralPermitService");
		wsdl11Definition.setTargetNamespace("http://services.gov/permits/structural");
		wsdl11Definition.setSchema(structuralPermitSchema());
		return wsdl11Definition;
	}

	/**
	 * structural permit schema instantiation.
	 * 
	 * @return
	 */
	@Bean
	public XsdSchema electricalPermitSchema() {
		return new SimpleXsdSchema(new ClassPathResource("ElectricalPermit.xsd"));
	}

	/**
	 * structural permit schema instantiation.
	 * 
	 * @return
	 */
	@Bean
	public XsdSchema structuralPermitSchema() {
		return new SimpleXsdSchema(new ClassPathResource("StructuralPermit.xsd"));
	}
}
