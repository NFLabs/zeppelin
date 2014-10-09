/* Copyright 2014 NFlabs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';

/** get the current port pf the websocket */
function getPort() {
  var port = Number(location.port);
  // brunch port
  if (port === 3333 || port === 9000) {
    port = 8080;
  }
  return port+1;
}

/**
 * @ngdoc overview
 * @name zeppelinWebApp
 * @description
 * # zeppelinWebApp
 *
 * Main module of the application.
 *
 * @author anthonycorbacho
 */
angular
  .module('zeppelinWebApp', [
    'ngAnimate',
    'ngCookies',
    'ngRoute',
    'ngSanitize',
    'angular-websocket',
    'ui.ace',
    'ui.bootstrap',
    'nvd3',
    'ngTouch',
    'ngClipboard'
  ])
  .config(function ($routeProvider, WebSocketProvider, ngClipProvider) {
    WebSocketProvider
      .prefix('')
      .uri('ws://'+location.hostname+':' + getPort());

    ngClipProvider.setPath('bower_components/zeroclipboard/dist/ZeroClipboard.swf');

    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html'
      })
      .when('/notebook/:noteId', {
        templateUrl: 'views/notebooks.html',
        controller: 'NotebookCtrl'
      })
      .when('/notebook/:noteId/paragraph/:paragraphId?', {
        templateUrl: 'views/notebooks.html',
        controller: 'NotebookCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });



