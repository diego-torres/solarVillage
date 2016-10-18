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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import gov.services.permits.repo.ElectricalPermitRepository;
import gov.services.permits.repo.StructuralPermitRepository;
import gov.services.permits.structural.Ack;
import gov.services.permits.structural.RaiseStructuralPermitRequest;
import gov.services.permits.structural.RaiseStructuralPermitResponse;
import gov.services.permits.structural.RescindStructuralPermitRequest;
import gov.services.permits.structural.RescindStructuralPermitResponse;
import gov.services.permits.structural.StructuralPermitStatusRequest;
import gov.services.permits.structural.StructuralPermitStatusResponse;

/**
 * @author dtorresf@redhat.com
 *
 */
@Endpoint
public class StructuralPermitServiceEndpoint {
	private static final String NS_URI = "http://services.gov/permits/structural";
	private StructuralPermitRepository repository;

	/**
	 * Autowire the repository bean for data source.
	 * 
	 * @param repository
	 */
	@Autowired
	public StructuralPermitServiceEndpoint(StructuralPermitRepository repository) {
		this.repository = repository;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "RaiseStructuralPermitRequest")
	@ResponsePayload
	public RaiseStructuralPermitResponse raiseStructuralPermit(@RequestPayload RaiseStructuralPermitRequest request) {
		RaiseStructuralPermitResponse response = new RaiseStructuralPermitResponse();
		String permitId = repository.addStructuralPermitRequest(request);
		if (ElectricalPermitRepository.DUPLICATE_CAPTION.equals(permitId)) {
			response.setAck(Ack.REJECTED);
			response.setRejectReason(ElectricalPermitRepository.DUPLICATE_CAPTION);
		} else {
			response.setAck(Ack.ACCEPTED);
			response.setRequestId(permitId);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "StructuralPermitStatusRequest")
	@ResponsePayload
	public StructuralPermitStatusResponse structuralPermitStatus(
			@RequestPayload StructuralPermitStatusRequest request) {
		StructuralPermitStatusResponse response = new StructuralPermitStatusResponse();
		response.setStatus(repository.structuralPermitStatus(request.getRequestId()));
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "RescindStructuralPermitRequest")
	@ResponsePayload
	public RescindStructuralPermitResponse rescindStructuralPermit(
			@RequestPayload RescindStructuralPermitRequest request) {
		RescindStructuralPermitResponse response = new RescindStructuralPermitResponse();
		response.setResult(repository.rescindStructuralPermit(request.getRequestId()));
		return response;
	}
}
