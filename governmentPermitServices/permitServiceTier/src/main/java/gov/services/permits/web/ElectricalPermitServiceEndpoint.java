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

import gov.services.permits.electrical.Ack;
import gov.services.permits.electrical.ElectricalPermitStatusRequest;
import gov.services.permits.electrical.ElectricalPermitStatusResponse;
import gov.services.permits.electrical.RaiseElectricalPermitRequest;
import gov.services.permits.electrical.RaiseElectricalPermitResponse;
import gov.services.permits.electrical.RescindElectricalPermitRequest;
import gov.services.permits.electrical.RescindElectricalPermitResponse;
import gov.services.permits.repo.ElectricalPermitRepository;

/**
 * @author dtorresf@redhat.com
 *
 */
@Endpoint
public class ElectricalPermitServiceEndpoint {
	private static final String NS_URI = "http://services.gov/permits/electrical";

	private ElectricalPermitRepository repository;

	/**
	 * Required Repository for the web service to work
	 * 
	 * @param repository
	 */
	@Autowired
	public ElectricalPermitServiceEndpoint(ElectricalPermitRepository repository) {
		this.repository = repository;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "RaiseElectricalPermitRequest")
	@ResponsePayload
	public RaiseElectricalPermitResponse raiseElectricalPermit(@RequestPayload RaiseElectricalPermitRequest request) {
		RaiseElectricalPermitResponse response = new RaiseElectricalPermitResponse();
		String permitId = repository.addElectricalPermitRequest(request);
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
	@PayloadRoot(namespace = NS_URI, localPart = "ElectricalPermitStatusRequest")
	@ResponsePayload
	public ElectricalPermitStatusResponse electricalPermitStatus(
			@RequestPayload ElectricalPermitStatusRequest request) {
		ElectricalPermitStatusResponse response = new ElectricalPermitStatusResponse();
		response.setStatus(repository.electricalPermitStatus(request.getRequestId()));
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NS_URI, localPart = "RescindElectricalPermitRequest")
	@ResponsePayload
	public RescindElectricalPermitResponse rescindElectricalPermit(
			@RequestPayload RescindElectricalPermitRequest request) {
		RescindElectricalPermitResponse response = new RescindElectricalPermitResponse();
		response.setResult(repository.rescindElectricalPermit(request.getRequestId()));
		return response;
	}

}
