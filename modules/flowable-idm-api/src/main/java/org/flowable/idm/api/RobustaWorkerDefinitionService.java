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


/**
 * Service to manage {@link RobustaWorker}s.
 * 
 * @author Egemen ALAN
 */
public interface RobustaWorkerDefinitionService {

    /**
     * Creates a new worker. The worker is transient and must be saved using {@link #saveWorker(RobustaWorker))}.
     * 
     * @param uworkerId
     *            id for the new worker, cannot be null.
     */
    RobustaWorker newWorker(String workerId);

    /**
     * Saves the worker. If the worker already existed, the worker is updated except user password.
     * Use {@link #updateUserPassword(User)} to update existing user password.
     * 
     * @param worker
     *            worker to save, cannot be null.
     * @throws RuntimeException
     *             when a worker with the same name already exists.
     */
    void saveWorker(RobustaWorker worker);

    
    /**
     * Creates a {@link WorkerQuery} that allows to programmatically query the workers.
     */
    RobustaWorkerQuery createWorkerQuery();

    /**
     * Returns a new {@link org.flowable.common.engine.api.query.NativeQuery} for tasks.
     */
    RobustaWorkerNativeQuery createNativeWorkerQuery();

    /**
     * @param workerId
     *            id of worker to delete, cannot be null. When an id is passed for a non-existent worker, this operation is ignored.
     */
    void deleteWorker(String workerId);

        /**
     * Passes the authenticated user id for this particular thread. All service method (from any service) invocations done by the same thread will have access to this authenticatedUserId.
     */
    void setAuthenticatedWorkerId(String authenticatedWorkerId);


    /**
     * Creates a new token. The token is transient and must be saved using {@link #saveToken(Token)}.
     * 
     * @param id
     *            id for the new token, cannot be null.
     */
    Token newToken(String id);

    /**
     * Saves the token. If the token already existed, the token is updated.
     * 
     * @param token
     *            token to save, cannot be null.
     */
    void saveToken(Token token);

    /**
     * @param tokenId
     *            id of token to delete, cannot be null. When an id is passed for an non-existent token, this operation is ignored.
     */
    void deleteToken(String tokenId);

    /**
     * Creates a {@link TokenQuery} that allows to programmatically query the tokens.
     */
    TokenQuery createTokenQuery();

    /**
     * Returns a new {@link org.flowable.common.engine.api.query.NativeQuery} for tokens.
     */
    NativeTokenQuery createNativeTokenQuery();

    /** Generic extensibility key-value pairs associated with a worker */
    void setWorkerInfo(String workerId, String key, String value);

    /** Generic extensibility key-value pairs associated with a worker */
    String getWorkerInfo(String workerId, String key);

    /** Generic extensibility keys associated with a worker */
    List<String> getWorkerInfoKeys(String workerId);

    /**
     * Delete an entry of the generic extensibility key-value pairs associated with a worker
     */
    void deleteWorkerInfo(String workerId, String key);

}
