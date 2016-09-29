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

import gov.services.permits.Ack;
import gov.services.permits.PermitRequest;
import gov.services.permits.PermitResponse;
import gov.services.permits.PermitStatusRequest;
import gov.services.permits.PermitStatusResponse;
import gov.services.permits.repo.PermitRepository;

/**
 * 
 * @author dtorresf@redhat.com
 *
 */
public interface PermitSoapService {
	/**
	 * 
	 * @return
	 */
	PermitRepository getPermitRepository();

	/**
	 * 
	 * @param permitRepository
	 */
	void setPermitRepository(PermitRepository permitRepository);

	/**
	 * 
	 * @param request
	 * @return
	 */
	default PermitResponse raisePermit(PermitRequest request) {
		PermitResponse response = new PermitResponse();
		String requestId = getPermitRepository().addPermitRequest(request);
		if ("DUPLICATE!".equals(requestId)) {
			response.setAck(Ack.REJECTED);
			response.setRequestId("DUPLICATE!");
			response.setRejectReason(
					"The beneficiary that you are using for this requirement is already processing another permit. "
							+ "Try again in another season.");
		} else {
			response.setAck(Ack.ACCEPTED);
			response.setRequestId(requestId);
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	default PermitStatusResponse permitStatus(PermitStatusRequest request){
		return getPermitRepository().queryStatus(request.getRequestId());
	}
}
