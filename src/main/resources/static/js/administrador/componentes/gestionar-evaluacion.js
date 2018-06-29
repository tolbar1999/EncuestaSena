
listarTodosEvaluacion();

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
			
			listarTodosEvaluacion();
			limpiarFormulario("formRegistroEvaluacion");
			efectoProcesamiento(idBoton,idLoader,false);
			tostadaRegistro();

		}).fail(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);	
			tostadaError();
		})
	}
		
});


//Listar todos los datos de la evaluacion
function listarTodosEvaluacion(){
	
	listarTodosEvaluacionGenerico("listarTodos");
}


//Listar todos los datos de la evaluacion generico
function listarTodosEvaluacionGenerico(metodo){

	var idCuerpoTabla = $("#cuerpoTablaEvaluacion");
	var idLoaderTabla = $("#loaderTablaEvaluacion");	
	var idAlertaTabla = $("#alertaTablaEvaluacion");

	limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);

	loaderTabla(idLoaderTabla,true);


	consultar("evaluacion",metodo,true).done(function(data){
		
		var cantidadDatos = data.length;
		var contador = 1;

		data.forEach(function(item){

			var datoNumero = $("<td></td>").text(contador);
			var datoAprendiz = $("<td></td>").text(item.aprendiz.nombreCompleto);
			var datoInstructor = $("<td></td>").text(item.instructor.nombreCompleto);
			var datoPregunta = $("<td></td>").text(item.pregunta.nombre);
			var datoPeriodo = $("<td></td>").text(item.detallePeriodo.nombre);
			var datoEstado = $("<td></td>").text(item.detalleEstado.nombre);
			var datoRespuesta = $("<td></td>").text(item.respuesta);
			var datoObservaciones = $("<td></td>").text(item.observaciones);
			var datoFecha = $("<td></td>").text(devolverFecha(item.fecha));

			var datoOpciones = "<td>"+
			'<button id="btnModificarAprendiz'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarAprendiz"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
			"</td>";



			var fila = $("<tr></tr>").append(datoNumero,datoAprendiz,datoInstructor,datoPregunta,datoPeriodo,datoEstado,datoRespuesta,datoObservaciones,datoFecha);

			idCuerpoTabla.append(fila);

			
//			asignarEventoClickAprendiz(item.id,item.identificacion,item.nombreCompleto,item.ficha.id,item.detalleEstado.id,contador);

			contador++;                        	
		})
		
		loaderTabla(idLoaderTabla,false);
		verificarDatosTabla(idAlertaTabla,cantidadDatos);

	}).fail(function(){	
		loaderTabla(idLoaderTabla,false);
		agregarAlertaTabla(idAlertaTabla,"error");
	})
}



















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

function devolverFecha(fecha){
	
	return new Date(fecha).toLocaleString();
}