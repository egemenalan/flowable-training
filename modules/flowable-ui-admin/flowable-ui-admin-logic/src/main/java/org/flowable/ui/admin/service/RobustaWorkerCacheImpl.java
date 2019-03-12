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

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.flowable.idm.api.RobustaWorker;
import org.flowable.idm.api.RobustaWorkerDefinitionService;
import org.flowable.spring.boot.ldap.FlowableLdapProperties;
import org.flowable.ui.admin.cache.RobustaWorkerCache;
import org.flowable.ui.admin.model.RobustaWorkerInformation;
import org.flowable.ui.common.properties.FlowableCommonAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * Cache containing User objects to prevent too much DB-traffic (users exist separately from the Flowable tables, they need to be fetched afterward one by one to join with those entities).
 * <p>
 * TODO: This could probably be made more efficient with bulk getting. The Google cache impl allows this: override loadAll and use getAll() to fetch multiple entities.
 *
 * @author Egemen ALAN
 * 
 */
@Service
public class RobustaWorkerCacheImpl implements RobustaWorkerCache {

    @Autowired
    protected FlowableCommonAppProperties properties;

    protected FlowableLdapProperties ldapProperties;

//    @Autowired
//    protected RobustaWorkerDefinitionService robustaWorkerDefinitionService;

    @Autowired
    protected RobustaWorkerService robustaWorkerService;

    protected LoadingCache<String, CachedWorker> workerCache;

    @PostConstruct
    protected void initCache() {
        FlowableCommonAppProperties.Cache cache = properties.getCacheWorkers();
        long workerCacheMaxSize = cache.getMaxSize();
        long workerCacheMaxAge = cache.getMaxAge();

//        workerCache = CacheBuilder.newBuilder().maximumSize(workerCacheMaxSize)
//            .expireAfterAccess(workerCacheMaxAge, TimeUnit.SECONDS).recordStats().build(new CacheLoader<String, CachedWorker>() {

//                    public CachedWorker load(final String workerId) throws Exception {
//                        RobustaWorker workerFromDatabase = null;
//                        if (ldapProperties == null || !ldapProperties.isEnabled()) {
//                        	workerFromDatabase = robustaWorkerDefinitionService.createWorkerQuery().workerIdIgnoreCase(workerId.toLowerCase()).singleResult();
//                        } else {
//                        	workerFromDatabase = robustaWorkerDefinitionService.createWorkerQuery().workerId(workerId).singleResult();
//                        }
//
//                        if (workerFromDatabase == null) {
//                            throw new UsernameNotFoundException("Worker " + workerId + " was not found in the database");
//                        }
//
//                        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//                        RobustaWorkerInformation workerInformation = robustaWorkerService.getWorkerInformation(workerFromDatabase.getId());
//                        for (String privilege : workerInformation.getPrivileges()) {
//                            grantedAuthorities.add(new SimpleGrantedAuthority(privilege));
//                        }
//
//                        return new CachedWorker(workerFromDatabase, grantedAuthorities);
//                    }

//                });
    }

    public void putWorker(String workerId, CachedWorker cachedWorker) {
        workerCache.put(workerId, cachedWorker);
    }

    public CachedWorker getWorker(String workerId) {
        return getWorker(workerId, false, false, true); // always check validity by default
    }

    public CachedWorker getWorker(String workerId, boolean throwExceptionOnNotFound, boolean throwExceptionOnInactive, boolean checkValidity) {
        try {
            // The cache is a LoadingCache and will fetch the value itself
        	CachedWorker cachedWorker = workerCache.get(workerId);
            return cachedWorker;

        } catch (ExecutionException e) {
            return null;
        } catch (UncheckedExecutionException uee) {

            // Some magic with the exceptions is needed:
            // the exceptions like UserNameNotFound and Locked cannot
            // bubble up, since Spring security will react on them otherwise
            if (uee.getCause() instanceof RuntimeException) {
                RuntimeException runtimeException = (RuntimeException) uee.getCause();

                if (runtimeException instanceof UsernameNotFoundException) {
                    if (throwExceptionOnNotFound) {
                        throw runtimeException;
                    } else {
                        return null;
                    }
                }

                if (runtimeException instanceof LockedException) {
                    if (throwExceptionOnNotFound) {
                        throw runtimeException;
                    } else {
                        return null;
                    }
                }

            }
            throw uee;
        }
    }

    @Override
    public void invalidate(String workerId) {
        workerCache.invalidate(workerId);
    }

    @Autowired(required = false)
    public void setLdapProperties(FlowableLdapProperties ldapProperties) {
        this.ldapProperties = ldapProperties;
    }
}
