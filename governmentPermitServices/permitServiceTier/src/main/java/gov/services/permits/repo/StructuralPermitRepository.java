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
package gov.services.permits.repo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import gov.services.permits.structural.PermitStatus;
import gov.services.permits.structural.RaiseStructuralPermitRequest;

/**
 * 
 * @author dtorresf@redhat.com
 *
 */
@Repository
public class StructuralPermitRepository {
	private static final String DUPLICATE_CAPTION = "DUPLICATE!";
	private static Logger log = LoggerFactory.getLogger(StructuralPermitRepository.class);
	private static Set<RaiseStructuralPermitRequest> receivedRequests = new HashSet<>();
	private static Map<String, PermitStatus> permitRequests = new HashMap<>();

	/**
	 * Add an structural permit request if another request with the same values
	 * does not exists.
	 * 
	 * @param request
	 *            The request object with the permit details.
	 * @return A request Id or "DUPLICATE!" if another permit with the same
	 *         values already exists.
	 */
	public String addStructuralPermitRequest(RaiseStructuralPermitRequest request) {
		log.info("Adding permit request(" + request.getClass().getName() + "): " + request);
		String id = UUID.randomUUID().toString();
		if (receivedRequests.add(request)) {
			permitRequests.put(id, PermitStatus.PENDING);
			log.info("Generated permit request with Id: " + id);
		} else {
			id = DUPLICATE_CAPTION;
		}
		return id;
	}
	
	/**
	 * Handles the status of the request using a random status generator. 60% of
	 * the time it will be in "PENDING" status, with chances of getting a DENIED
	 * or APPROVED status. if the request have been RESCINDED or has never been
	 * created, the resolved status will be UNKNOWN
	 * 
	 * @param permitRequestId
	 * @return
	 */
	public PermitStatus structuralPermitStatus(String permitRequestId){
		log.info("wondering the status of this structural permit request: " + permitRequestId);
		if (permitRequests.containsKey(permitRequestId)) {
			PermitStatus status = permitRequests.get(permitRequestId);
			if (status == PermitStatus.PENDING) {
				int rand = (int) (Math.random() * 10);
				// 10% probability to be denied
				if (rand == 1)
					permitRequests.replace(permitRequestId, PermitStatus.DENIED);
				// 30% probability to be approved
				if (rand >= 2 && rand <= 4)
					permitRequests.replace(permitRequestId, PermitStatus.APPROVED);
				// 60% remaining probability to still pending
			}

		}
		PermitStatus result = permitRequests.getOrDefault(permitRequestId, PermitStatus.UNKNOWN);
		log.info("the structural permit request " + permitRequestId + " is in status: " + result);
		return result;
	}
	
	/**
	 * Rescind a permit by its Id
	 * 
	 * @param permitId
	 * @return OK when succeed, UNKNOWN permitId when fail.
	 */
	public String rescindStructuralPermit(String permitId){
		log.info("Rescind structural permit request with id: " + permitId);
		if (permitRequests.containsKey(permitId)) {
			permitRequests.remove(permitId);
			return "OK";
		} else {
			return "UNKNOWN permitId: " + permitId;
		}
	}
}
