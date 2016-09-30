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

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import gov.services.permits.PermitResponse;
import gov.services.permits.PermitStatusRequest;
import gov.services.permits.PermitStatusResponse;
import gov.services.permits.repo.PermitRepository;
import gov.services.permits.repo.StructuralPermitRepository;
import gov.services.permits.structural.StructuralPermitRequest;

/**
 * @author dtorresf@redhat.com
 *
 */
public class StructuralPermitEndpoint implements PermitSoapService {
	private static final String NS_URI = "http://services.gov/permits/structural";
	private StructuralPermitRepository structuralPermitRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#getPermitRepository()
	 */
	@Override
	public PermitRepository getPermitRepository() {
		return structuralPermitRepository;
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
		structuralPermitRepository = (StructuralPermitRepository) permitRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#raisePermit(gov.services.
	 * permits.PermitRequest)
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "raiseStructuralPermitRequest")
	public PermitResponse raisePermit(@RequestPayload StructuralPermitRequest request) {
		return PermitSoapService.super.raisePermit(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.services.permits.web.soap.PermitSoapService#permitStatus(gov.services
	 * .permits.PermitStatusRequest)
	 */
	@Override
	@PayloadRoot(namespace = NS_URI, localPart = "structuralPermitStatusRequest")
	public PermitStatusResponse permitStatus(PermitStatusRequest request) {
		return PermitSoapService.super.permitStatus(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.services.permits.web.soap.PermitSoapService#rescindPermit(gov.
	 * services.permits.PermitStatusRequest)
	 */
	@Override
	@PayloadRoot(namespace = NS_URI, localPart = "structuralPermitRescindRequest")
	public PermitResponse rescindPermit(@RequestPayload PermitStatusRequest request) {
		return PermitSoapService.super.rescindPermit(request);
	}

}
