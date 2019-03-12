/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.ui.admin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.flowable.idm.api.RobustaWorker;
import org.flowable.idm.api.RobustaWorkerQuery;
import org.flowable.ui.admin.model.RobustaWorkerInformation;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.common.service.exception.ConflictingRequestException;
import org.flowable.ui.common.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Egemen ALAN
 */
@Service
@Transactional
public class RobustaWorkerServiceImpl extends RobustaAbstractWorkerService implements RobustaWorkerService {

	private static final int MAX_WORKER_SIZE = 100;

//	public List<RobustaWorker> getWorkers(String filter, String sort, Integer start) {
//		Integer startValue = start != null ? start.intValue() : 0;
//		Integer size = MAX_WORKER_SIZE; // TODO: pass actual size
//		return createWorkerQuery(filter, sort).listPage(startValue,
//				(size != null && size > 0) ? size : MAX_WORKER_SIZE);
//	}

//	public long getWorkerCount(String filter, String sort, Integer start, String groupId) {
//		return createWorkerQuery(filter, sort).count();
//	}

//	protected RobustaWorkerQuery createWorkerQuery(String filter, String sort) {
//		RobustaWorkerQuery robustaWorkerQuery = robustaWorkerDefinitionService.createWorkerQuery();
//		if (StringUtils.isNotEmpty(filter)) {
//			robustaWorkerQuery.workerNameLikeIgnoreCase("%" + filter + "%");
//		}
//
//		if (StringUtils.isNotEmpty(sort)) {
//			if ("idDesc".equals(sort)) {
//				robustaWorkerQuery.orderByWorkerId().desc();
//			} else if ("idAsc".equals(sort)) {
//				robustaWorkerQuery.orderByWorkerId().asc();
//			} else if ("emailAsc".equals(sort)) {
//				robustaWorkerQuery.orderByWorkerName().asc();
//			} else if ("emailDesc".equals(sort)) {
//				robustaWorkerQuery.orderByWorkerName().desc();
//			}
//
//		}
//		return robustaWorkerQuery;
//	}

//	public void updateWorkerDetails(String workerId, String name, String ipHostName, String minBpPriority,
//			String maxBpPriority) {
//		RobustaWorker robustaWorker = robustaWorkerDefinitionService.createWorkerQuery().workerId(workerId)
//				.singleResult();
//		if (robustaWorker != null) {
//			robustaWorker.setName(name);
//			robustaWorker.setIpHostName(ipHostName);
//			robustaWorker.setMinBpPriority(minBpPriority);
//			robustaWorkerDefinitionService.saveWorker(robustaWorker);
//		}
//	}

//TO-DO Egemen burayi KOntrol et
//    public void deleteWorker(String workerId) {
//        List<Privilege> privileges = robustaWorkerDefinitionService.createPrivilegeQuery().userId(userId).list();
//        for (Privilege privilege : privileges) {
//        	robustaWorkerDefinitionService.deleteUserPrivilegeMapping(privilege.getId(), userId);
//        }
//
//        List<Group> groups = robustaWorkerDefinitionService.createGroupQuery().groupMember(userId).list();
//        if (groups != null && groups.size() > 0) {
//            for (Group group : groups) {
//            	robustaWorkerDefinitionService.deleteMembership(userId, group.getId());
//            }
//        }
//        robustaWorkerDefinitionService.deleteUser(userId);
//    }

//	public RobustaWorker createNewWorker(String workerId, String name, String ipHostName, String minBpPriority,
//			String maxBpPriority) {
//		if (StringUtils.isBlank(workerId) || StringUtils.isBlank(name)) {
//			throw new BadRequestException("Id, and name are required");
//		}
//
//		if (ipHostName != null
//				&& robustaWorkerDefinitionService.createWorkerQuery().workerIpHostName(ipHostName).count() > 0) {
//			throw new ConflictingRequestException("Worker already registered",
//					"ACCOUNT.SIGNUP.ERROR.ALREADY-REGISTERED");
//		}
//
//		RobustaWorker robustaWorker = robustaWorkerDefinitionService.newWorker(workerId);
//		robustaWorker.setName(name);
//		robustaWorker.setIpHostName(ipHostName);
//		robustaWorker.setMaxBpPriority(maxBpPriority);
//		robustaWorkerDefinitionService.saveWorker(robustaWorker);
//
//		return robustaWorker;
//	}
//
//	@Override
//	public RobustaWorkerInformation getWorkerInformation(String workerId) {
//		RobustaWorker robustaWorker = robustaWorkerDefinitionService.createWorkerQuery().workerId(workerId)
//				.singleResult();
//		if (robustaWorker == null) {
//			throw new NotFoundException();
//		}
//
//		return new RobustaWorkerInformation(robustaWorker);
//	}

	@Override
	public long getWorkerCount(String filter, String sort, Integer start, String groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteWorker(String workerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RobustaWorker> getWorkers(String filter, String sort, Integer start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorkerDetails(String id, String name, String ipHostName, String minBpPriority,
			String maxBpPriority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RobustaWorker createNewWorker(String id, String name, String ipHostName, String minBpPriority,
			String maxBpPriority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RobustaWorkerInformation getWorkerInformation(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
