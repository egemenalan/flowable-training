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

package org.flowable.idm.api;

import java.util.HashMap;
import java.util.Map;

import org.flowable.common.engine.api.query.QueryProperty;

/**
 * Contains the possible properties that can be used by the {@link RobustaWorkerQuery}.
 * 
 * @author Egemen ALAN
 */
public class RobustaWorkerQueryProperty implements QueryProperty {

    private static final long serialVersionUID = 1L;

    private static final Map<String, RobustaWorkerQueryProperty> properties = new HashMap<>();

    public static final RobustaWorkerQueryProperty WORKER_ID = new RobustaWorkerQueryProperty("RES.ID_");
    public static final RobustaWorkerQueryProperty NAME = new RobustaWorkerQueryProperty("RES.NAME_");
    public static final RobustaWorkerQueryProperty MIN_BP_PRIORITY = new RobustaWorkerQueryProperty("RES.MIN_");
    public static final RobustaWorkerQueryProperty MAX_BP_PRIORITY = new RobustaWorkerQueryProperty("RES.LAST_");
    public static final RobustaWorkerQueryProperty IP_HOST_NAME = new RobustaWorkerQueryProperty("RES.IP_HOST_NAME_");

    private String name;

    public RobustaWorkerQueryProperty(String name) {
        this.name = name;
        properties.put(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    public static RobustaWorkerQueryProperty findByName(String propertyName) {
        return properties.get(propertyName);
    }

}
