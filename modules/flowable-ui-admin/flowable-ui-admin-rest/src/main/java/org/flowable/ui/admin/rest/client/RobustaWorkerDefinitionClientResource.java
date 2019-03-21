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

import org.flowable.ui.admin.domain.EndpointType;
import org.flowable.ui.admin.domain.ServerConfig;
import org.flowable.ui.admin.service.engine.RobustaWorkerDefinitionService;
import org.flowable.ui.admin.service.engine.exception.FlowableServiceException;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Egemen ALAN
 */
@RestController
@RequestMapping("/app")
public class RobustaDefinitionClientResource extends AbstractClientResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobustaDefinitionClientResource.class);

    @Autowired
    protected RobustaWorkerDefinitionService clientService;

    @RequestMapping(value = "/rest/admin/worker-definitions", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getWorkerDefinition(@PathVariable String workerDefinitionId) throws BadRequestException {

        ServerConfig serverConfig = retrieveServerConfig(EndpointType.ROBUSTA);
        try {
            return clientService.getWorkerDefinition(serverConfig, workerDefinitionId);
        } catch (FlowableServiceException e) {
            LOGGER.error("Error getting content item {}", workerDefinitionId, e);
            throw new BadRequestException(e.getMessage());
        }
    }
}
