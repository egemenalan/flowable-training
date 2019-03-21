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
'use strict';

/* Controllers */

flowableAdminApp.controller('RobustaWorkerDefinitionsController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$translate', '$q', '$modal', 'gridConstants',
    function ($rootScope, $scope, $http, $timeout, $location, $translate, $q, $modal, gridConstants) {

	$rootScope.navigation = {main: 'robusta-definition', sub: 'worker-definitions'};
        
		$scope.filter = {};
		$scope.formWorkerDefinitionsData = {};

		// Array to contain selected properties (yes - we only can select one, but ng-grid isn't smart enough)
	    $scope.selectedWorkerDefinitions = [];

	    var filterConfig = {
	    	url: '/app/rest/admin/robusta-worker-definitions',
	    	method: 'GET',
	    	success: function(data, status, headers, config) {
	    		$scope.formWorkerDefinitionsData = data;
            },
            error: function(data, status, headers, config) {
                if (data && data.message) {
                    // Extract error-message
                    $rootScope.addAlert(data.message, 'error');
                } else {
                    // Use default error-message
                    $rootScope.addAlert($translate.instant('ALERT.GENERAL.HTTP-ERROR'), 'error');
                }
            },

            sortObjects: [
                {name: 'WORKER-DEFINITION.SORT.ID', id: 'id'},
                {name: 'WORKER-DEFINITION.SORT.NAME', id: 'name'}
            ]
	    };

      $scope.executeWhenReady(function() {
          $scope.filter.refresh();
      });

    }]);

}]);