
/* ====== Instructor ====== */

listarTodosInstructor();
cargarTipoInstructor();
cargarEstado("selectDetalleEstadoInstructor");
cargarEstado("selectDetalleEstadoInstructorFiltro");
cargarEstado("selectDetalleEstadoInstructorMod");


validarRegistroInstructor();
validarModificarInstructor();
confirmarModificarInstructor();



//Registrar el instructor
$("#btnRegistrarInstructor").click(function(){

	if($("#formRegistroInstructor").valid()){

		var idBoton = $("#btnRegistrarInstructor");
		var idLoader = $("#loaderRegistroInstructor");

		efectoProcesamiento(idBoton,idLoader,true);


		var cedula = $("#txtCedulaInstructor").val();
		var nombreCompleto = $.trim($("#txtNombreCompletoInstructor").val());
		var idDetalleTipoInstructor = $("#selectDetalleTipoInstructor").val();
		var idDetalleEstado = $("#selectDetalleEstadoInstructor").val();


		var detalleTipoInstructor = {			
				"id" : idDetalleTipoInstructor			
		}
		
		var detalleEstado = {			
				"id" : idDetalleEstado			
		}


		var instructor = {			
				"cedula" : cedula,
				"nombreCompleto" : nombreCompleto,
				"detalleTipoInstructor" : detalleTipoInstructor,
				"detalleEstado" : detalleEstado
		}


		guardar("instructor",instructor,"POST",false).done(function(){

			listarTodosInstructor();
			limpiarFormulario("formRegistroInstructor");
			efectoProcesamiento(idBoton,idLoader,false);
			tostadaRegistro();

		}).fail(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);	
			tostadaError();
		});

	}

})


//Listar todos los datos del instructor
function listarTodosInstructor(){

	listarTodosInstructorGenerico("listarTodos");

}


//Listar todos los datos del instructor generico
function listarTodosInstructorGenerico(metodo){

	var idCuerpoTabla = $("#cuerpoTablaInstructor");
	var idLoaderTabla = $("#loaderTablaInstructor");	
	var idAlertaTabla = $("#alertaTablaInstructor");

	limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);

	loaderTabla(idLoaderTabla,true);


	consultar("instructor",metodo,true).done(function(data){

		var cantidadDatos = data.length;
		var contador = 1;

		data.forEach(function(item){

			var datoNumero = $("<td></td>").text(contador);
			var datoCedula = $("<td></td>").text(item.cedula);
			var datoNombreCompleto = $("<td></td>").text(item.nombreCompleto);
			var datoDetalleTipoInstructor = $("<td></td>").text(item.detalleTipoInstructor.nombre);
			var datoDetalleEstado = $("<td></td>").text(item.detalleEstado.nombre);


			var datoOpciones = "<td>"+
			'<button id="btnModificarInstructor'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarInstructor"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
			"</td>";



			var fila = $("<tr></tr>").append(datoNumero,datoCedula,datoNombreCompleto,datoDetalleTipoInstructor,datoDetalleEstado,datoOpciones);

			idCuerpoTabla.append(fila);


			asignarEventoClickInstructor(item.id,item.cedula,item.nombreCompleto,item.detalleTipoInstructor.id,item.detalleEstado.id,contador);

			contador++;                        	
		})
		
		loaderTabla(idLoaderTabla,false);
		verificarDatosTabla(idAlertaTabla,cantidadDatos);

	}).fail(function(){	
		loaderTabla(idLoaderTabla,false);
		agregarAlertaTabla(idAlertaTabla,"error");
	})
}


//Asignarle evento click a el modificar del instructor
function asignarEventoClickInstructor(idInstructor,cedula,nombreCompleto,idDetalleTipoInstructor,idDetalleEstado,contador){

	$("#btnModificarInstructor"+contador).click(function(){

		limpiarFormulario("formModificarInstructor");
		
		$("#txtIdInstructor").val(idInstructor);
		
		$("#txtCedulaInstructorMod").val(cedula);
		$("#txtNombreCompletoInstructorMod").val(nombreCompleto);
		$("#selectDetalleTipoInstructorMod").val(idDetalleTipoInstructor);
		$("#selectDetalleEstadoInstructorMod").val(idDetalleEstado);
		
	});
}


//Funcion para cuando le unda guardar cambios, modifique el registro
function confirmarModificarInstructor(){
	
	var idBoton = $("#btnConfirmarModificarInstructor");
	var idLoader = $("#loaderModificarInstructor");

	idBoton.click(function(){

		if($("#formModificarInstructor").valid()){

			efectoProcesamiento(idBoton,idLoader,true);


			var idInstructor = $("#txtIdInstructor").val();

			var cedula = $("#txtCedulaInstructorMod").val();
			var nombreCompleto = $.trim($("#txtNombreCompletoInstructorMod").val());
			var idDetalleTipoInstructor = $("#selectDetalleTipoInstructorMod").val();
			var idDetalleEstado = $("#selectDetalleEstadoInstructorMod").val();

			
			var detalleTipoInstructor = {			
					"id" : idDetalleTipoInstructor			
			}

			var detalleEstado = {			
					"id" : idDetalleEstado			
			}


			var instructor = {
					"id" : idInstructor,
					"cedula" : cedula,
					"nombreCompleto" : nombreCompleto,
					"detalleTipoInstructor" : detalleTipoInstructor,
					"detalleEstado" : detalleEstado
			}


			guardar("instructor",instructor,"PUT",false).done(function(){

				efectoProcesamiento(idBoton,idLoader,false);
				$('#modalModificarInstructor').modal('hide');
				tostadaActualizar();	
				listarTodosFiltroInstructor();

			}).fail(function(){
				efectoProcesamiento(idBoton,idLoader,false);
				tostadaError();
			});

		}		
	})
}


// Consultar por filtros del instructor
function listarTodosFiltroInstructor(){
	
	var cedulaFiltro = $("#txtCedulaInstructorFiltro").val();
	var nombreCompletoFiltro = $.trim($("#txtNombreCompletoInstructorFiltro").val());
	var idDetalleTipoInstructorFiltro = $("#selectDetalleTipoInstructorFiltro").val();
	var idDetalleEstadoFiltro = $("#selectDetalleEstadoInstructorFiltro").val();

	
	listarTodosInstructorGenerico("listarTodosFiltro?cedula="+cedulaFiltro+"&nombreCompleto="+nombreCompletoFiltro+"&idDetalleTipoInstructor="+idDetalleTipoInstructorFiltro+"&idDetalleEstado="+idDetalleEstadoFiltro);				
}


//Cuando le unda click consulte los filtros
$("#btnFiltrarInstructor").click(function(){

	listarTodosFiltroInstructor();

});


//Refrescar filtro instructor y listar todos
$("#btnRefrescarFiltroInstructor").click(function(){

	listarTodosInstructor();
});




//Validar el formulario de registro instructor
function validarRegistroInstructor(){

	$("#formRegistroInstructor").validate({
		rules : {
			txtCedulaInstructor : {
				required : true,
				number: true,
				remote : function () { 
					var devolver = {
							url: "/instructor/existeCedula?cedula="+$("#txtCedulaInstructor").val(),
							type: "get",
							dataType: "json"
					};

					return devolver;
				}
			},
			txtNombreCompletoInstructor : {
				required : true
			},
			selectDetalleTipoInstructor : {
				required : true,
				min : 1
			},
			selectDetalleEstadoInstructor : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtCedulaInstructor : {
				required : "Debe escribir una cedula",
				number : "Debe escribir solo numeros",
				remote : "Esta cedula ya existe"
			},
			txtNombreCompletoInstructor : "Debe escribir el nombre completo",
			selectDetalleTipoInstructor : "Debe escoger un tipo instructor",
			selectDetalleEstadoInstructor : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})	
}


//Validar el formulario de modificar instructor
function validarModificarInstructor(){
 
	$("#formModificarInstructor").validate({
		rules : {
			txtCedulaInstructorMod : {
				required : true,
				number : true
			},
			txtNombreCompletoInstructorMod : {
				required : true
			},
			selectDetalleTipoInstructorMod : {
				required : true,
				min : 1
			},
			selectDetalleEstadoInstructorMod : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtCedulaInstructorMod : {
				required : "Debe escribir una cedula",
				number : "Debe escribir solo numeros"
			},
			txtNombreCompletoInstructorMod : "Debe escribir el nombre completo",
			selectDetalleTipoInstructorMod : "Debe escoger un tipo instructor",
			selectDetalleEstadoInstructorMod : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})

}

//Cargar en un select el tipo de instructor
function cargarTipoInstructor(){

	consultar("detalle","listarTodosPorIdMaestro?idMaestro=2",true).done(function(data){

		$("#selectDetalleTipoInstructor").empty();
		$("#selectDetalleTipoInstructor").append("<option value='0'>-- Seleccione --</option>");
		
		$("#selectDetalleTipoInstructorFiltro").empty();
		$("#selectDetalleTipoInstructorFiltro").append("<option value='0'>-- Seleccione --</option>");
		
		$("#selectDetalleTipoInstructorMod").empty();
		$("#selectDetalleTipoInstructorMod").append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){

			$("#selectDetalleTipoInstructor").append("<option value='"+item.id+"'>"+item.nombre+"</option>");	
			$("#selectDetalleTipoInstructorFiltro").append("<option value='"+item.id+"'>"+item.nombre+"</option>");	
			$("#selectDetalleTipoInstructorMod").append("<option value='"+item.id+"'>"+item.nombre+"</option>");	
		});

	});

}


/* ====== Cierre instructor  ====== */


