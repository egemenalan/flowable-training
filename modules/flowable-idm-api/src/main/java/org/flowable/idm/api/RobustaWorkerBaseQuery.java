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

import java.util.List;

import org.flowable.common.engine.api.query.Query;

/**
 * Allows programmatic querying of {@link RobustaWorker}
 *
 * @author Egemen ALAN
 * 
 */
public interface RobustaWorkerBaseQuery<T extends RobustaWorkerBaseQuery<T, U>, U extends RobustaWorker> extends Query<T, U> {

    /**
     * Only select {@link RobustaWorker}s with the given id/
     */
    T workerId(String id);

    /**
     * Only select {@link RobustaWorker}s with the given ids/
     */
    T workerIds(List<String> ids);

    /**
     * Only select {@link RobustaWorker}s with the given id (ignoring case) /
     */
    T workerIdIgnoreCase(String id);

    /**
     * Only select {@link RobustaWorker}s with the given name.
     */
    T workerName(String name);

    /**
     * Only select {@link RobustaWorker}s where the  name matches the given parameter. The syntax is that of SQL, eg. %name%.
     */
    T workerNameLike(String nameLike);

    /**
     * Only select {@link RobustaWorker}s where the name matches the given parameter (ignoring case). The syntax is that of SQL, eg. %name%.
     */
    T workerNameLikeIgnoreCase(String nameLikeIgnoreCase);

    /**
     * Only select {@link RobustaWorker}s with the given ipHostName.
     */
    T workerIpHostName(String ipHostName);

    /**
     * Only select {@link RobustaWorker}s where the ip HostName matches the given parameter. The syntax is that of SQL, eg. %name%.
     */
    T workerIpHostNameLike(String ipHostNameLike);

    /**
     * Only select {@link RobustaWorker}s where the ip HostName matches the given parameter (ignoring case). The syntax is that of SQL, eg. %name%.
     */
    T workerIpHostNameLikeIgnoreCase(String ipHostNameLikeIgnoreCase);

    /**
     * Only select {@link RobustaWorker}s where the min Bp Priority matches the given parameters. The syntax is that of SQL, eg. %name%.
     */
    T workerMinBpPriorityLike(String minBpPriorityLike);

    /**
     * Only select {@link RobustaWorker}s where the min Bp Priority matches the given parameters (ignoring case). The syntax is that of SQL, eg. %name%.
     * 
     */
    T workerMinBpPriorityLikeIgnoreCase(String minBpPriorityLikeIgnoreCase);
    
    /**
     * Only select {@link RobustaWorker}s where the min Bp Priority matches the given parameters. The syntax is that of SQL, eg. %name%.
     */
    T workerMaxBpPriorityLike(String maxBpPriorityLike);

    /**
     * Only select {@link RobustaWorker}s where the max Bp Priority matches the given parameters (ignoring case). The syntax is that of SQL, eg. %name%.
     * 
     */
    T workerMaxBpPriorityLikeIgnoreCase(String maxBpPriorityLikeIgnoreCase);

    // sorting ////////////////////////////////////////////////////////

    /**
     * Order by worker id (needs to be followed by {@link #asc()} or {@link #desc()}).
     */
    T orderByWorkerId();

    /**
     * Order by worker name (needs to be followed by {@link #asc()} or {@link #desc()}).
     */
    T orderByWorkerName();

    /**
     * Order by worker last name (needs to be followed by {@link #asc()} or {@link #desc()}).
     */
    T orderByWorkerIpHostName();

    /**
     * Order by worker min Bp Priority (needs to be followed by {@link #asc()} or {@link #desc()}).
     */
    T orderByWorkerMinBpPriority();
    

    /**
     * Order by worker max Bp Priority (needs to be followed by {@link #asc()} or {@link #desc()}).
     */
    T orderByWorkerMaxBpPriority();
}
