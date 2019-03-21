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
/**
 * Controller for robusta mgmt
 */
flowableApp.controller('RobustaWorkerDefinitionController', ['$rootScope', '$scope', '$translate', '$http', '$timeout','$location', '$modal',
    function ($rootScope, $scope, $translate, $http, $timeout, $location, $modal) {

    $rootScope.navigation = {main: 'robusta-definition', sub: 'worker-definitions'};

        $scope.model = {
            loading: false,
            sorts: [
                {id: 'idAsc', name: $translate.instant('WORKER-DEFINITION.FILTERS.SORT-ID-A')},
                {id: 'idDesc', name: $translate.instant('WORKER-DEFINITION.FILTERS.SORT-ID-Z')},
                {id: 'emailAsc', name: $translate.instant('WORKER-DEFINITION.FILTERS.SORT-EMAIL-A')},
                {id: 'emailDesc', name: $translate.instant('WORKER-DEFINITION.FILTERS.SORT-EMAIL-Z')}
            ],
            waiting: false,
            delayed: false,
            selectedWorkers: {},
            selectedWorkerCount: 0,
            start: 0
        };

        $scope.model.activeSort = $scope.model.sorts[0];

        $scope.clearSelectedWorkers = function() {
            $scope.model.selectedWorkers = {};
            $scope.model.selectedWorkerCount = 0;
        };

        $scope.loadWorkers = function() {
            $scope.clearSelectedWorkers();
            $scope.model.loading = true;
            var params = {
                filter: $scope.model.pendingFilterText,
                company: $scope.model.pendingCompanyText,
                sort: $scope.model.activeSort.id,
                start: $scope.model.start
            };

            $http({method: 'GET', url: FLOWABLE.CONFIG.contextRoot + '/app/rest/admin/robusta-definition', params: params}).
                success(function(data, status, headers, config) {
                    data.moreWorkers = data.start + data.size < data.total;
                    $scope.model.workers = data;
                    $scope.model.loading = false;
                }).
                error(function(data, status, headers, config) {
                    $scope.model.loading = false;
                });
        };


        $scope.refreshDelayed = function() {
            // If already waiting, another wait-cycle will be done
            // after the current wait is over
            if($scope.model.waiting) {
                $scope.model.delayed = true;
            } else {
                $scope.scheduleDelayedRefresh();
            }
        };

        $scope.scheduleDelayedRefresh = function() {
            $scope.model.waiting = true;

            $timeout(function() {
                $scope.model.waiting = false;
                if( $scope.model.delayed) {
                    $scope.model.delayed = false;
                    // Delay again
                    $scope.scheduleDelayedRefresh();
                } else {
                    // Actually do the refresh-call, after resetting start
                    $scope.model.start = 0;
                    $scope.loadWorkers();
                }
            }, 100);
        };

        $scope.showNextWorkers = function() {
            if($scope.model.workers) {
                $scope.model.start = $scope.model.workers.start + $scope.model.workers.size;
                $scope.loadWorkers();
            }
        };

        $scope.showPreviousWorkers = function() {
            if($scope.model.workers) {
                $scope.model.start = Math.max(0, $scope.model.workers.start - $scope.model.workers.size);
                $scope.loadWorkers();
            }
        };

        $scope.activateSort = function(sort) {
            $scope.model.activeSort = sort;
            $scope.model.start = 0;
            $scope.loadWorkers();
        };

        $scope.toggleWorkerSelection = function(worker) {
            if($scope.model.selectedWorkers[worker.id]) {
                delete $scope.model.selectedWorkers[worker.id];
                $scope.model.selectedWorkerCount -= 1;
            }  else {
                $scope.model.selectedWorkers[worker.id] = true;
                $scope.model.selectedWorkerCount +=1;
            }

        };

        $scope.addWorker = function() {
            $scope.model.errorMessage = undefined;
            $scope.model.worker = undefined;
            $scope.model.mode = 'create';
            _internalCreateModal({
                scope: $scope,
                template: 'views/robusta-create-worker-popup.html?version=' + new Date().getTime(),
                show: true
            }, $modal, $scope);
        };


        $scope.editWorkerDetails = function() {

            $scope.model.worker = undefined;
            $scope.model.mode = 'edit';
            var selectedWorkers = $scope.getSelectedWorkers();
            if (selectedWorkers && selectedWorkers.length == 1) {
                $scope.model.worker = selectedWorkers[0];
            }

            $scope.model.errorMessage = undefined;
            _internalCreateModal({
                scope: $scope,
                template: 'views/robusta-create-worker-popup.html?version=' + new Date().getTime(),
                show: true
            }, $modal, $scope);
        };

        $scope.deleteWorkers = function() {
            $scope.model.loading = true;
            $scope.getSelectedWorkers().forEach(function(selectedWorker) {
                $http({method: 'DELETE', url: FLOWABLE.CONFIG.contextRoot + '/app/rest/admin/robusta-definition/' + selectedWorker.id}).
                    success(function (data, status, headers, config) {

                        $rootScope.addAlert('Worker deleted', 'info');
                        $scope.loadWorkers();

                        $scope.model.loading = false;
                    }).
                    error(function (data, status, headers, config) {
                        $scope.model.loading = false;
                        if (data && data.message) {
                            $rootScope.addAlert(data.message, 'error');
                        } else {
                            $rootScope.addAlert('Error while deleting worker', 'error');
                        }
                    });
            });
        };

        $scope.getSelectedWorkers = function() {
            var selected = [];
            for(var i = 0; i<$scope.model.workers.size; i++) {
                var worker = $scope.model.workers.data[i];
                if(worker) {
                    for(var prop in $scope.model.selectedWorkers) {
                        if(worker.id == prop) {
                            selected.push(worker);
                            break;
                        }
                    }
                }
            }

            return selected;
        };

        $scope.loadWorkers();

    }]);


/**
 * Controller for the create worker dialog
 */
flowableApp.controller('RobustaCreateWorkerPopupController', ['$rootScope', '$scope', '$http',
    function ($rootScope, $scope, $http) {


        if ($scope.model.worker === null || $scope.model.worker === undefined) {
            $scope.model.worker = {};
        }

        $scope.createNewWorker = function () {
            if (!$scope.model.worker.id) {
                return;
            }

            var model = $scope.model;
            model.loading = true;

            var data = {
                id: model.worker.id,
                name: model.worker.name,
                ipHostName: model.worker.ipHostName,
                minBpPriority: model.worker.minBpPriority,
                maxBpPriority: model.worker.maxBpPriority,
            };

            $http({method: 'POST', url: FLOWABLE.CONFIG.contextRoot + '/app/rest/admin/robusta-definition', data: data}).
                success(function (data, status, headers, config) {

                    $rootScope.addAlert('New worker created', 'info');
                    $scope.loadWorkers();

                    $scope.model.loading = false;
                    $scope.$hide();
                }).
                error(function (data, status, headers, config) {
                    $scope.model.loading = false;
                    if (data && data.message) {
                        $rootScope.addAlert(data.message, 'error');
                    } else {
                        $rootScope.addAlert('Error while updating worker status', 'error');
                    }

                    if (status == 403) {
                        $scope.model.errorMessage = "Forbidden";
                    } else if (status == 409) {
                        $scope.model.errorMessage = "A worker with that email address already exists";
                    } else {
                        $scope.$hide();
                    }
                });
        };

        $scope.editWorkerDetails = function() {
            if (!$scope.model.worker.id) {
                return;
            }

            var model = $scope.model;
            model.loading = true;

            var data = {
            		id: model.worker.id,
                    name: model.worker.name,
                    ipHostName: model.worker.ipHostName,
                    minBpPriority: model.worker.minBpPriority,
                    maxBpPriority: model.worker.maxBpPriority,
            };

            $http({method: 'PUT', url: FLOWABLE.CONFIG.contextRoot + '/app/rest/admin/robusta-definition/' + $scope.model.worker.id, data: data}).
                success(function (data, status, headers, config) {

                    $scope.loadWorkers();

                    $scope.model.loading = false;
                    $scope.$hide();
                }).
                error(function (data, status, headers, config) {
                    $scope.model.loading = false;
                    if (data && data.message) {
                        $rootScope.addAlert(data.message, 'error');
                    } else {
                        $rootScope.addAlert('Error while updating worker status', 'error');
                    }

                    if (status == 403) {
                        $scope.model.errorMessage = "Forbidden";
                    } else if (status == 409) {
                        $scope.model.errorMessage = "A worker with that email address already exists";
                    } else {
                        $scope.$hide();
                    }
                });
        };

        $scope.cancel = function () {
            if (!$scope.model.loading) {
                $scope.$hide();
            }
        };

    }]);

    $scope.setStatus = function(newStatus) {
      $scope.model.updateWorkers.status = newStatus;
    };

    $scope.setType = function(newType) {
      $scope.model.updateWorkers.type = newType;
    };

    $scope.cancel = function () {
      if(!$scope.model.loading) {
        $scope.$hide();
      }
    };

}]);

