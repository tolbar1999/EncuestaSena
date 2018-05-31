//Para la navegacion para que cambie solo el main y no se recargue la pagina

//include ngRoute for all our routing needs
var demoApp = angular.module('demoApp', ['ngRoute']);


//configure our routes
demoApp.config(function($routeProvider) {
	$routeProvider
	// route for the home page
	.when('/', {
		templateUrl : 'gestionar-maestro-detalle',
		controller  : 'mainCtrl'
	})

	.when('/gestionar-ficha', {
		templateUrl : 'gestionar-ficha',
		controller  : 'gestionarFichaCtrl'
	})

	.when('/gestionar-instructor', {
		templateUrl : 'gestionar-instructor',
		controller  : 'gestionarInstructorCtrl'
	})

	.when('/gestionar-pregunta', {
		templateUrl : 'gestionar-pregunta',
		controller  : 'gestionarPreguntaCtrl'
	})

	.when('/gestionar-aprendiz', {
		templateUrl : 'gestionar-aprendiz',
		controller  : 'gestionarAprendizCtrl'
	})

	.when('/gestionar-evaluacion', {
		templateUrl : 'gestionar-evaluacion',
		controller  : 'gestionarEvaluacionCtrl'
	})

	.when('/asignar-ficha-instructor', {
		templateUrl : 'asignar-ficha-instructor',
		controller  : 'asignarFichaInstructorCtrl'
	})

	.otherwise({
		redirectTo: '/'
	}); 

});



demoApp.controller('mainCtrl', function($scope) {



});

demoApp.controller('gestionarFichaCtrl', function($scope) {



});

demoApp.controller('gestionarInstructorCtrl', function($scope) {



});

demoApp.controller('gestionarPreguntaCtrl', function($scope) {



});

demoApp.controller('gestionarAprendizCtrl', function($scope) {



});

demoApp.controller('gestionarEvaluacionCtrl', function($scope) {



});

demoApp.controller('asignarFichaInstructorCtrl', function($scope) {



});




