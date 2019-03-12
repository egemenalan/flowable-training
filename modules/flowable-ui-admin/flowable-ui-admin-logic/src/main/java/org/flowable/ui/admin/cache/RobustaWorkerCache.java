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
package org.flowable.ui.admin.cache;

import java.util.Collection;

import org.flowable.idm.api.RobustaWorker;
import org.springframework.security.core.GrantedAuthority;

/**
 * A cache of {@link RobustaWorker} objects.
 * 
 * @author Egemen ALAN
 */
public interface RobustaWorkerCache {

    CachedWorker getWorker(String workerId);

    CachedWorker getWorker(String workerId, boolean throwExceptionOnNotFound, boolean throwExceptionOnInactive, boolean checkValidity);

    void putWorker(String workerId, CachedWorker cachedWorker);

    void invalidate(String workerId);

    public static class CachedWorker {

        private Collection<GrantedAuthority> grantedAuthorities;

        private RobustaWorker robustaWorker;

        private long lastDatabaseCheck;

        public CachedWorker(RobustaWorker robustaWorker, Collection<GrantedAuthority> grantedAuthorities) {
            this.robustaWorker = robustaWorker;
            this.grantedAuthorities = grantedAuthorities;
            this.lastDatabaseCheck = System.currentTimeMillis();
        }

        public RobustaWorker getWorker() {
            return robustaWorker;
        }

        public Collection<GrantedAuthority> getGrantedAuthorities() {
            return grantedAuthorities;
        }

        public long getLastDatabaseCheck() {
            return lastDatabaseCheck;
        }

        public void setLastDatabaseCheck(long lastDatabaseCheck) {
            this.lastDatabaseCheck = lastDatabaseCheck;
        }

    }

}
