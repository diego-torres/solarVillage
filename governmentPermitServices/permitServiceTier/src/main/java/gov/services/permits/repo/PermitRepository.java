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

import gov.services.permits.PermitRequest;
import gov.services.permits.PermitStatus;
import gov.services.permits.PermitStatusResponse;

/**
 * @author dtorresf@redhat.com
 *
 */
public interface PermitRepository {
	public static final String DUPLICATE_CAPTION = "DUPLICATE!";
	static Logger log = LoggerFactory.getLogger(PermitRepository.class);
	static Set<PermitRequest> receivedRequests = new HashSet<PermitRequest>();
	static Map<String, PermitStatusResponse> permitRequests = new HashMap<String, PermitStatusResponse>();

	/**
	 * 
	 * @param request
	 * @return
	 */
	public default <T extends PermitRequest> String addPermitRequest(T request) {
		log.info("Adding permit request(" + request.getClass().getName() + "): " + request);
		String id = UUID.randomUUID().toString();
		if (receivedRequests.add(request)) {
			PermitStatusResponse response = new PermitStatusResponse();
			response.setStatus(PermitStatus.PENDING);
			permitRequests.put(id, response);
			log.debug("generated request value: " + response);
			log.info("Generated permit request with Id: " + id);
		} else {
			id = DUPLICATE_CAPTION;
		}
		return id;
	}

	/**
	 * 
	 * @param requestId
	 * @return
	 */
	public default PermitStatusResponse queryStatus(String requestId) {
		log.info("wondering if this request has been approved: " + requestId);
		if (permitRequests.containsKey(requestId)) {
			PermitStatusResponse prs = permitRequests.get(requestId);
			if (prs.getStatus() == PermitStatus.PENDING) {
				int rand = (int) (Math.random() * 10);
				// 10% probability to be denied
				if (rand == 1)
					prs.setStatus(PermitStatus.DENIED);
				// 30% probability to be approved
				if (rand >= 2 && rand <= 4)
					prs.setStatus(PermitStatus.APPROVED);
				// 60% remaining probability to still pending
			}

		}
		PermitStatusResponse result = permitRequests.getOrDefault(requestId, null);
		log.info("the request " + requestId + " is in status: " + result);
		return result;
	}
}
