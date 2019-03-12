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
package org.flowable.ui.admin.model;

import java.util.ArrayList;
import java.util.List;

import org.flowable.ui.common.model.AbstractRepresentation;

/**
 * @author Egemen ALAN
 */
public class RobustaUpdateWorkersRepresentation extends AbstractRepresentation {

    protected String id;
    protected String name;
    protected String ipHostName;
    protected String minBpPriority;
    protected String maxBpPriority;
    protected List<String> workers = new ArrayList<>();

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpHostName() {
		return ipHostName;
	}

	public void setIpHostName(String ipHostName) {
		this.ipHostName = ipHostName;
	}

	public String getMinBpPriority() {
		return minBpPriority;
	}

	public void setMinBpPriority(String minBpPriority) {
		this.minBpPriority = minBpPriority;
	}

	public String getMaxBpPriority() {
		return maxBpPriority;
	}

	public void setMaxBpPriority(String maxBpPriority) {
		this.maxBpPriority = maxBpPriority;
	}

	public List<String> getWorkers() {
		return workers;
	}

	public void setWorkers(List<String> workers) {
		this.workers = workers;
	}

	public RobustaUpdateWorkersRepresentation() {

    }

    
    

}
