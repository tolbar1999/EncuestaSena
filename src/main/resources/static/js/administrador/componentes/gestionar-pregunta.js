

listarTodosPregunta();
cargarTipoFormacion("selectDetalleTipoFormacionPregunta");
cargarTipoFormacion("selectDetalleTipoFormacionPreguntaFiltro");
cargarTipoFormacion("selectDetalleTipoFormacionPreguntaMod");
cargarEstado("selectDetalleEstadoPregunta");
cargarEstado("selectDetalleEstadoPreguntaFiltro");
cargarEstado("selectDetalleEstadoPreguntaMod");


validarRegistroPregunta();
validarModificarPregunta();
confirmarModificarPregunta();



//Registrar la pregunta
$("#btnRegistrarPregunta").click(function(){

	if($("#formRegistroPregunta").valid()){

		var idBoton = $("#btnRegistrarPregunta");
		var idLoader = $("#loaderRegistroPregunta");

		efectoProcesamiento(idBoton,idLoader,true);
		

		var nombre = $.trim($("#txtNombrePregunta").val());
		var idDetalleTipoFormacion = $("#selectDetalleTipoFormacionPregunta").val();
		var idDetalleEstado = $("#selectDetalleEstadoPregunta").val();

		
		var detalleTipoFormacion = {			
				"id" : idDetalleTipoFormacion			
		}
		
		var detalleEstado = {			
				"id" : idDetalleEstado			
		}


		var pregunta = {			
				"nombre" : nombre,
				"detalleTipoFormacion" : detalleTipoFormacion,
				"detalleEstado" : detalleEstado						
		}


		guardar("pregunta",pregunta,"POST",false).done(function(){

			listarTodosPregunta();
			limpiarFormulario("formRegistroPregunta");
			efectoProcesamiento(idBoton,idLoader,false);
			tostadaRegistro();
			
		}).fail(function(){

			efectoProcesamiento(idBoton,idLoader,false);	
			tostadaError();
		});

	}

})


//Listar todos los datos de la pregunta
function listarTodosPregunta(){

	listarTodosPreguntaGenerico("listarTodos");

}


//Listar todos los datos de la pregunta generico
function listarTodosPreguntaGenerico(metodo){

	var idCuerpoTabla = $("#cuerpoTablaPregunta");
	var idLoaderTabla = $("#loaderTablaPregunta");	
	var idAlertaTabla = $("#alertaTablaPregunta");

	limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);

	loaderTabla(idLoaderTabla,true);


	consultar("pregunta",metodo,true).done(function(data){
		
		var cantidadDatos = data.length;
		var contador = 1;

		data.forEach(function(item){

			var datoNumero = $("<td></td>").text(contador);
			var txtAreaNombre = $("<textarea class='textarea-table' disabled='disabled'></textarea>").text(item.nombre);
			var datoTipoFormacion = $("<td></td>").text(item.detalleTipoFormacion.nombre);
			var datoEstado = $("<td></td>").text(item.detalleEstado.nombre);


			var datoOpciones = "<td>"+
			'<button id="btnModificarPregunta'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarPregunta"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
			"</td>";


			
			var datoNombre = $("<td></td>").append(txtAreaNombre);
			
			var fila = $("<tr></tr>").append(datoNumero,datoNombre,datoTipoFormacion,datoEstado,datoOpciones);
			
			idCuerpoTabla.append(fila);

			
			asignarEventoClickPregunta(item.id,item.nombre,item.detalleTipoFormacion.id,item.detalleEstado.id,contador);

			contador++;                        	
		})

		loaderTabla(idLoaderTabla,false);
		verificarDatosTabla(idAlertaTabla,cantidadDatos);

	}).fail(function(){	
		loaderTabla(idLoaderTabla,false);
		agregarAlertaTabla(idAlertaTabla,"error");
	})
}


//Asignarle evento click a el modificar/eliminar de la Pregunta
function asignarEventoClickPregunta(idPregunta,nombre,idDetalleTipoFormacion,idDetalleEstado,contador){

	$("#btnModificarPregunta"+contador).click(function(){

		limpiarFormulario("formModificarPregunta");

		$("#txtIdPregunta").val(idPregunta);
		$("#txtNombrePreguntaMod").val(nombre);
		$("#selectDetalleTipoFormacionPreguntaMod").val(idDetalleTipoFormacion);
		$("#selectDetalleEstadoPreguntaMod").val(idDetalleEstado);
	});
}


//Funcion para cuando le unda guardar cambios, modifique el registro
function confirmarModificarPregunta(){

	var idBoton = $("#btnConfirmarModificarPregunta");
	var idLoader = $("#loaderModificarPregunta");

	idBoton.click(function(){

		if($("#formModificarPregunta").valid()){

			efectoProcesamiento(idBoton,idLoader,true);


			var idPregunta = $("#txtIdPregunta").val();

			var nombre = $.trim($("#txtNombrePreguntaMod").val());
			var idDetalleTipoFormacion = $("#selectDetalleTipoFormacionPreguntaMod").val();
			var idDetalleEstado = $("#selectDetalleEstadoPreguntaMod").val();
			

			var detalleTipoFormacion = {			
					"id" : idDetalleTipoFormacion			
			}
			
			var detalleEstado = {			
					"id" : idDetalleEstado			
			}
 

			var pregunta = {		
					"id" : idPregunta,
					"nombre" : nombre,
					"detalleTipoFormacion" : detalleTipoFormacion,
					"detalleEstado" : detalleEstado						
			}


			guardar("pregunta",pregunta,"PUT",false).done(function(){

				efectoProcesamiento(idBoton,idLoader,false);
				$('#modalModificarPregunta').modal('hide');
				tostadaActualizar();	
				listarTodosFiltroPregunta();

			}).fail(function(){
				efectoProcesamiento(idBoton,idLoader,false);
				tostadaError();
			});

		}		
	})
}



//Consultar por filtros de la pregunta
function listarTodosFiltroPregunta(){

	var idDetalleTipoFormacionFiltro = $("#selectDetalleTipoFormacionPreguntaFiltro").val();
	var idDetalleEstadoFiltro = $("#selectDetalleEstadoPreguntaFiltro").val();

	listarTodosPreguntaGenerico("listarTodosFiltro?idDetalleTipoFormacion="+idDetalleTipoFormacionFiltro+"&idDetalleEstado="+idDetalleEstadoFiltro);				
}


//Cuando le unda click consulte los filtros
$("#btnFiltrarPregunta").click(function(){

	listarTodosFiltroPregunta();
});


//Refrescar filtro pregunta y listar todos
$("#btnRefrescarFiltroPregunta").click(function(){
	
	listarTodosPregunta();
});




//Validar el formulario de registro pregunta
function validarRegistroPregunta(){

	$("#formRegistroPregunta").validate({
		rules : {
			txtNombrePregunta : {
				required : true
			},
			selectDetalleTipoFormacionPregunta : {
				required : true,
				min : 1
			},
			selectDetalleEstadoPregunta : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtNombrePregunta : "Debe escribir un nombre",
			selectDetalleTipoFormacionPregunta : "Debe escoger un tipo formacion",
			selectDetalleEstadoPregunta : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})	
}


//Validar el formulario de modificar pregunta
function validarModificarPregunta(){

	$("#formModificarPregunta").validate({
		rules : {
			txtNombrePreguntaMod : {
				required : true
			},
			selectDetalleTipoFormacionPreguntaMod : {
				required : true,
				min : 1
			},
			selectDetalleEstadoPreguntaMod : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtNombrePreguntaMod : "Debe escribir un nombre",
			selectDetalleTipoFormacionPreguntaMod : "Debe escoger un tipo formacion",
			selectDetalleEstadoPreguntaMod : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})

}