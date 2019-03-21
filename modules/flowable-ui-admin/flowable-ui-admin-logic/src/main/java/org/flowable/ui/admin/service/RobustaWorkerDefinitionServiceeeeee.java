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
package org.flowable.ui.admin.service.engine;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.flowable.ui.admin.domain.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for invoking Flowable REST services.
 */
@Service
public class RobustaWorkerDefinitionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobustaWorkerDefinitionService.class);

    @Autowired
    protected FlowableClientService clientUtil;

    public JsonNode listWorkerDefinitions(ServerConfig serverConfig, Map<String, String[]> parameterMap) {
        URIBuilder builder = clientUtil.createUriBuilder("content-service/worker-definitions");

        for (String name : parameterMap.keySet()) {
            builder.addParameter(name, parameterMap.get(name)[0]);
        }
        HttpGet get = new HttpGet(clientUtil.getServerUrl(serverConfig, builder.toString()));
        return clientUtil.executeRequest(get, serverConfig);
    }

    public JsonNode getWorkerDefinition(ServerConfig serverConfig, String workerDefinitionId) {
        HttpGet get = new HttpGet(clientUtil.getServerUrl(serverConfig, "content-service/worker-definitions/" + workerDefinitionId));
        return clientUtil.executeRequest(get, serverConfig);
    }
}
