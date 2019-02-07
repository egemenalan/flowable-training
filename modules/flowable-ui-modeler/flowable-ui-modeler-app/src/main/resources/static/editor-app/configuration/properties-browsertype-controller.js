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
/*Robusta Custom
 * Egemen ALAN
 */

angular.module('flowableModeler').controller('RobustaBrowserTypeCtrl', [ '$scope', function($scope) {

    if ($scope.property.value == undefined && $scope.property.value == null)
    {
    	$scope.property.value = 'CHROME';
    }
        
    $scope.robustaBrowserTypeChanged = function() {
    	$scope.updatePropertyInModel($scope.property);
    };
}]);

angular.module('flowableModeler').controller('RobustaBrowserSwitchTypeCtrl', [ '$scope', function($scope) {

    if ($scope.property.value == undefined && $scope.property.value == null)
    {
    	$scope.property.value = 'FRAME';
    }
        
    $scope.robustaBrowserSwitchTypeChanged = function() {
    	$scope.updatePropertyInModel($scope.property);
    };
}]);

angular.module('flowableModeler').controller('RobustaBrowserAlertTypeCtrl', [ '$scope', function($scope) {

    if ($scope.property.value == undefined && $scope.property.value == null)
    {
    	$scope.property.value = 'ACCEPT';
    }
        
    $scope.robustaBrowserAlertTypeChanged = function() {
    	$scope.updatePropertyInModel($scope.property);
    };
}]);