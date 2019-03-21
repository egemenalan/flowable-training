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
package org.flowable.ui.admin.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.flowable.idm.api.RobustaWorker;
import org.flowable.ui.admin.model.RobustaCreateWorkerRepresentation;
import org.flowable.ui.admin.model.RobustaUpdateWorkersRepresentation;
import org.flowable.ui.admin.service.RobustaWorkerService;
import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.flowable.ui.common.model.RobustaWorkerRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Egemen ALAN
 */
@RestController
@RequestMapping("/app")
public class RobustaDefinitionsClientResource extends AbstractClientResource {


    @Autowired
    protected RobustaWorkerService workerService;

    @RequestMapping(value = "/rest/admin/worker-definitions", method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) String groupId) {

        int startValue = start != null ? start.intValue() : 0;

        List<RobustaWorker> workers = workerService.getWorkers(filter, sort, start);
        ResultListDataRepresentation result = new ResultListDataRepresentation();
        result.setTotal(workerService.getWorkerCount(filter, sort, startValue, groupId));
        result.setStart(startValue);
        result.setSize(workers.size());
        result.setData(convertToWorkerRepresentations(workers));
        return result;
    }

    protected List<RobustaWorkerRepresentation> convertToWorkerRepresentations(List<RobustaWorker> workers) {
        List<RobustaWorkerRepresentation> result = new ArrayList<>(workers.size());
        for (RobustaWorker worker : workers) {
            result.add(new RobustaWorkerRepresentation(worker));
        }
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/rest/admin/worker-definitions{workerId}", method = RequestMethod.PUT)
    public void updateWorkerDetails(@PathVariable String id, @RequestBody RobustaUpdateWorkersRepresentation updateWorkersRepresentation) {
    	workerService.updateWorkerDetails(id, updateWorkersRepresentation.getName(),
    			updateWorkersRepresentation.getIpHostName(),
    			updateWorkersRepresentation.getMinBpPriority(),
    			updateWorkersRepresentation.getMaxBpPriority());
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/rest/admin/worker-definitions{workerId}", method = RequestMethod.DELETE)
    public void deleteWorker(@PathVariable String workerId) {
    	workerService.deleteWorker(workerId);
    }

    @RequestMapping(value = "/rest/admin/worker-definitions", method = RequestMethod.POST)
    public RobustaWorkerRepresentation createNewWorker(@RequestBody RobustaCreateWorkerRepresentation workerRepresentation) {
        return new RobustaWorkerRepresentation(workerService.createNewWorker(workerRepresentation.getId(),
        		workerRepresentation.getName(),
        		workerRepresentation.getIpHostName(),
        		workerRepresentation.getMinBpPriority(),
        		workerRepresentation.getMaxBpPriority()));
    }

}