
var identificacionAprendiz = getParameterByName("identificacion");

var idDetalleEstadoRespondido = 3;


/* === DATOS DEL APRENDIZ === */
consultar("aprendiz","listarPorIdentificacion?identificacion="+identificacionAprendiz,true).done(function(dataAprendiz){
	
	var idAprendiz = dataAprendiz.id;
	
	$("#txtIdAprendiz").val(idAprendiz);
	$("#labelIdentificacionAprendiz").append(dataAprendiz.identificacion);
	$("#labelNombreAprendiz").append(dataAprendiz.nombreCompleto);
	$("#labelFichaAprendiz").append(dataAprendiz.ficha.numero);
	
	
	/* === INSTRUCTORES A EVALUAR === */
	consultar("evaluacion","obtenerInstructoresPorIdAprendiz?idAprendiz="+idAprendiz,true).done(function(dataEvaluacionInstructor){

		dataEvaluacionInstructor.forEach(function(item){
		
			var idInstructor = item.id;
			var nombreInstructor = item.nombreCompleto;
			
			$("#espacioInstructores").append('<div class="custom-control custom-radio inline-block">'+
									  			'<input type="radio" id="'+idInstructor+'" name="radioInstructor" class="custom-control-input" value="'+idInstructor+'" required="required" />'+
									  			'<label class="custom-control-label" for="'+idInstructor+'">'+nombreInstructor+'</label>'+
											'</div> &nbsp;');
		});
		
	}).fail(function(){
		
		devolverInicio();
	});
	
	/* === PREGUNTAS A CONTESTAR === */
	consultar("evaluacion","obtenerPreguntasPorIdAprendiz?idAprendiz="+idAprendiz,true).done(function(dataEvaluacionPregunta){
		
		$("#txtCantidadPreguntas").val(dataEvaluacionPregunta.length);
		var contador = 1;
		
		dataEvaluacionPregunta.forEach(function(item){
			
			$("#espacioPreguntas").append('<div class="row card">'+
												'<input id="txtIdPregunta'+contador+'" type="hidden" value="'+item.id+'" />'+
												'<div class="col-lg-12 mt-3">'+
													'<p>'+contador+') '+item.nombre+'</p>'+
												'</div>'+
												'<div>'+
													'<div class="div-derecha">'+
														'<div class="custom-control custom-radio inline-block">'+
															'<input type="radio" id="respuestaSi'+contador+'" name="respuesta'+contador+'" class="custom-control-input" required="required" value="SI" />'+ 
															'<label class="custom-control-label" for="respuestaSi'+contador+'">SI</label>'+
														'</div> &nbsp;'+
														'<div class="custom-control custom-radio inline-block">'+
															'<input type="radio" id="respuestaNo'+contador+'" name="respuesta'+contador+'" class="custom-control-input" required="required" value="NO" />'+ 
															'<label class="custom-control-label" for="respuestaNo'+contador+'">NO</label>'+
														'</div> &nbsp;'+
														'<button title="Escriba su observacion" type="button" class="btn btn-observacion" onclick="mostrarObservacion(\'divObservacion'+contador+'\');">'+
															'<i class="fa fa-edit"></i>'+
														'</button>'+
													'</div>'+
												'</div>'+
												'<div id="divObservacion'+contador+'" class="mt-1 mb-1 mr-1 display-none">'+
													'<div class="div-derecha">'+
														'<textarea id="observacion'+contador+'" class="form-control observacion"></textarea>'+
													'</div>'+
												'</div>'+
											'</div>');
			
			contador++;
		})
		
	}).fail(function(){
		
		devolverInicio();
	});
	
	
	/* === PERIODO A EVALUAR === */
	consultar("evaluacion","obtenerPeriodoPorIdAprendiz?idAprendiz="+idAprendiz,true).done(function(dataEvaluacionPeriodo){
		
		$("#txtIdPeriodo").val(dataEvaluacionPeriodo.id);
		$("#tituloPeriodo").text(dataEvaluacionPeriodo.nombre);
		
	}).fail(function(){
		
		alert("El aprendiz ya realizó la evaluacion");
		devolverInicio();
	});
	
	
}).fail(function(){
	
	alert("No se ha encontrado al aprendiz");
	devolverInicio();
});





/* TERMINAR EVALUACION */
$("#btnTerminarEncuesta").click(function(e){

	if($("#formEncuesta")[0].checkValidity()){
		
		var idBoton = $("#btnTerminarEncuesta");
		idBoton.attr("disabled","disabled");
		
		
		var idAprendiz = $("#txtIdAprendiz").val();
		var idInstructor = $("input[name='radioInstructor']:checked").val();
		var idPeriodo = $("#txtIdPeriodo").val();
		
		var cantidadPreguntas = $("#txtCantidadPreguntas").val();
		
		var objetos = new Array();
		var contadorObjetos = 0;
		
		for (var i = 1; i <= cantidadPreguntas; i++) {
			
			var idPregunta = $("#txtIdPregunta"+i).val();
			var respuesta = $("input[name='respuesta"+i+"']:checked").val();
			var observaciones = $.trim($("#observacion"+i).val());
			
			var id = {
					"idAprendiz" : idAprendiz,
					"idInstructor" : idInstructor,
					"idPregunta" : idPregunta,
					"idPeriodo" : idPeriodo					
			}
			
			var detalleEstado = {
					
					"id" : idDetalleEstadoRespondido
			}
			
			var evaluacion = {
					
					"id" : id,
					"detalleEstado" : detalleEstado,
					"respuesta" : respuesta,
					"observaciones" : observaciones
			}
			
			objetos[contadorObjetos] = evaluacion;
			
			contadorObjetos++;
		}
		
		actualizarEvaluacion(objetos).done(function(){
			
			idBoton.removeAttr("disabled");
			alert("Se ha evaluado al instructor correctamente");
			location.reload();
			
		}).fail(function(data){
			
			idBoton.removeAttr("disabled");
			alert("Ha ocurrido un error");
		});
		
		
		e.preventDefault();
	}
	
});
 






/* ==== UTILES ==== */

function devolverInicio(){
	
	location.href = "/";
}

function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}

function mostrarObservacion(idDiv){
	
	var idDivDom = $("#"+idDiv);
	
	if(idDivDom.is(":visible")){
		
		idDivDom.hide();
	}else{
		
		idDivDom.show();
	}
}


function actualizarEvaluacion(evaluacion){
	
	return $.ajax({
		 
		type : "PUT",
			 
		data : JSON.stringify(evaluacion),

		url : host+"/evaluacion/actualizarCustom",
			
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

