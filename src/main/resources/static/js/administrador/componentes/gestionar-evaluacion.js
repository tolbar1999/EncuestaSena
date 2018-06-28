
cargarPeriodo("selectDetallePeriodoEvaluacion");
cargarTipoFormacion("selectDetalleTipoFormacionEvaluacion");

validarRegistroEvaluacion();


//Registrar la evaluacion
$("#btnRegistrarEvaluacion").click(function(){

	if($("#formRegistroEvaluacion").valid()){

		var idBoton = $("#btnRegistrarEvaluacion");
		var idLoader = $("#loaderRegistroEvaluacion");

		efectoProcesamiento(idBoton,idLoader,true);


		var idDetallePeriodo = $("#selectDetallePeriodoEvaluacion").val(); 
		var idDetalleTipoFormacion = $("#selectDetalleTipoFormacionEvaluacion").val();


		preInsertar(idDetallePeriodo,idDetalleTipoFormacion).done(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);
			alert("bien");

		}).fail(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);
			alert("mal");
		})
	}
		
});



//Validar el formulario de registro evaluacion
function validarRegistroEvaluacion(){

	$("#formRegistroEvaluacion").validate({
		rules : {
			selectDetallePeriodoEvaluacion : {
				required : true,
				min : 1
			},
			selectDetalleTipoFormacionEvaluacion : {
				required : true,
				min : 1
			}
		},
		messages : {
			selectDetallePeriodoEvaluacion : "Debe escoger un periodo",
			selectDetalleTipoFormacionEvaluacion : "Debe escoger un tipo formacion"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})	
}






//Cargar en un select el periodo
function cargarPeriodo(idSelect){

	consultar("detalle","listarTodosPorIdMaestro?idMaestro=3",true).done(function(data){

		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){

			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.nombre+"</option>");				
		});

	});

}


//Metodo para preinsertar
function preInsertar(idDetallePeriodo,idDetalleTipoFormacion){

	return $.ajax({
		 
		type : "GET",
			 
		url : host+"/evaluacion/preInsertar?idDetallePeriodo="+idDetallePeriodo+"&idDetalleTipoFormacion="+idDetalleTipoFormacion,
			
		async : true,
		
		beforeSend: function(xhr){
				
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type","application/json");			
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
				
			console.log("Request:"+ XMLHttpRequest.toString()+ "estatus"+ textStatus + "error"+ errorThrown);
		}
				
	});
	
}