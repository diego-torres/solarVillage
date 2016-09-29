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

package gov.services.permits.web.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import gov.services.permits.PermitResponse;
import gov.services.permits.PermitStatusRequest;
import gov.services.permits.PermitStatusResponse;
import gov.services.permits.electrical.ElectricalPermitRequest;
import gov.services.permits.repo.ElectricalPermitRepository;
import gov.services.permits.repo.PermitRepository;

/**
 * @author dtorresf@redhat.com
 *
 */
@Endpoint
public class ElectricalPermitEndpoint implements PermitSoapService {
	private static final String NS_URI = "http://services.gov/permits/electrical";
	private ElectricalPermitRepository electricalPermitRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#raisePermit(gov.
	 * services.permits.repo.PermitRequest)
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "raiseElectricalPermitRequest")
	public PermitResponse raisePermit(@RequestPayload ElectricalPermitRequest request) {
		return PermitSoapService.super.raisePermit(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#permitStatus(gov.
	 * services.permits.repo.PermitStatusRequest)
	 */
	@Override
	@PayloadRoot(namespace = NS_URI, localPart = "electricalPermitStatusRequest")
	public PermitStatusResponse permitStatus(@RequestPayload PermitStatusRequest request) {
		return PermitSoapService.super.permitStatus(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#getPermitRepository()
	 */
	@Override
	public PermitRepository getPermitRepository() {
		return electricalPermitRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#setPermitRepository(gov.
	 * services.permits.repo.PermitRepository)
	 */
	@Override
	public void setPermitRepository(PermitRepository permitRepository) {
		electricalPermitRepository = (ElectricalPermitRepository) permitRepository;
	}
}
