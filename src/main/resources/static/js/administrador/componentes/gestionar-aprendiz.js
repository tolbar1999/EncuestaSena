
/* ====== Aprendiz ====== */

listarTodosAprendiz();
cargarFicha("selectFichaAprendiz");
cargarFicha("selectFichaAprendizFiltro");
cargarFicha("selectFichaAprendizMod");
cargarEstado("selectDetalleEstadoAprendiz");
cargarEstado("selectDetalleEstadoAprendizFiltro");
cargarEstado("selectDetalleEstadoAprendizMod");

validarRegistroAprendiz();
validarModificarAprendiz();
confirmarModificarAprendiz();



//Registrar el aprendiz
$("#btnRegistrarAprendiz").click(function(){

	if($("#formRegistroAprendiz").valid()){
		
		var idBoton = $("#btnRegistrarAprendiz");
		var idLoader = $("#loaderRegistroAprendiz");

		efectoProcesamiento(idBoton,idLoader,true);
		
		
		var identificacion = $("#txtIdentificacionAprendiz").val();
		var nombreCompleto = $.trim($("#txtNombreCompletoAprendiz").val());
		var idFicha = $("#selectFichaAprendiz").val(); 
		var idDetalleEstado = $("#selectDetalleEstadoAprendiz").val();


		var ficha = {			
				"id" : idFicha			
		}
		
		var detalleEstado = {			
				"id" : idDetalleEstado			
		}


		var aprendiz = {			
				"identificacion" : identificacion,
				"nombreCompleto" : nombreCompleto,
				"ficha" : ficha,
				"detalleEstado" : detalleEstado
		}


		guardar("aprendiz",aprendiz,"POST",false).done(function(){

			listarTodosAprendiz();
			limpiarFormulario("formRegistroAprendiz");
			efectoProcesamiento(idBoton,idLoader,false);
			tostadaRegistro();

		}).fail(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);	
			tostadaError();
		});

	}

})


//Listar todos los datos del aprendiz
function listarTodosAprendiz(){
	
	listarTodosAprendizGenerico("listarTodos");

}


//Listar todos los datos del aprendiz generico
function listarTodosAprendizGenerico(metodo){

	var idCuerpoTabla = $("#cuerpoTablaAprendiz");
	var idLoaderTabla = $("#loaderTablaAprendiz");	
	var idAlertaTabla = $("#alertaTablaAprendiz");

	limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);

	loaderTabla(idLoaderTabla,true);


	consultar("aprendiz",metodo,true).done(function(data){
		
		var cantidadDatos = data.length;
		var contador = 1;

		data.forEach(function(item){

			var datoNumero = $("<td></td>").text(contador);
			var datoIdentificacion = $("<td></td>").text(item.identificacion);
			var datoNombreCompleto = $("<td></td>").text(item.nombreCompleto);
			var datoFicha = $("<td></td>").text(item.ficha.numero);
			var datoDetalleEstado = $("<td></td>").text(item.detalleEstado.nombre);


			var datoOpciones = "<td>"+
			'<button id="btnModificarAprendiz'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarAprendiz"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
			"</td>";



			var fila = $("<tr></tr>").append(datoNumero,datoIdentificacion,datoNombreCompleto,datoFicha,datoDetalleEstado,datoOpciones);

			idCuerpoTabla.append(fila);

			
			asignarEventoClickAprendiz(item.id,item.identificacion,item.nombreCompleto,item.ficha.id,item.detalleEstado.id,contador);

			contador++;                        	
		})
		
		loaderTabla(idLoaderTabla,false);
		verificarDatosTabla(idAlertaTabla,cantidadDatos);

	}).fail(function(){	
		loaderTabla(idLoaderTabla,false);
		agregarAlertaTabla(idAlertaTabla,"error");
	})
}


//Asignarle evento click a el modificar del aprendiz
function asignarEventoClickAprendiz(idAprendiz,identificacion,nombreCompleto,idFicha,idDetalleEstado,contador){

	$("#btnModificarAprendiz"+contador).click(function(){

		limpiarFormulario("formModificarAprendiz");
		
		$("#txtIdAprendiz").val(idAprendiz);
		
		$("#txtIdentificacionAprendizMod").val(identificacion);
		$("#txtNombreCompletoAprendizMod").val(nombreCompleto);
		$("#selectFichaAprendizMod").val(idFicha);
		$("#selectDetalleEstadoAprendizMod").val(idDetalleEstado);
		
	});
}


//Funcion para cuando le unda guardar cambios, modifique el registro
function confirmarModificarAprendiz(){
	
	var idBoton = $("#btnConfirmarModificarAprendiz");
	var idLoader = $("#loaderModificarAprendiz");

	idBoton.click(function(){

		if($("#formModificarAprendiz").valid()){
			
			efectoProcesamiento(idBoton,idLoader,true);


			var idAprendiz = $("#txtIdAprendiz").val();
			
			var identificacion = $("#txtIdentificacionAprendizMod").val();
			var nombreCompleto = $.trim($("#txtNombreCompletoAprendizMod").val());
			var idFicha = $("#selectFichaAprendizMod").val();
			var idDetalleEstado = $("#selectDetalleEstadoAprendizMod").val();
			
			
			var ficha = {			
					"id" : idFicha			
			}

			var detalleEstado = {			
					"id" : idDetalleEstado			
			}


			var aprendiz = {
					"id" : idAprendiz,
					"identificacion" : identificacion,
					"nombreCompleto" : nombreCompleto,
					"ficha" : ficha,
					"detalleEstado" : detalleEstado
			}


			guardar("aprendiz",aprendiz,"PUT",false).done(function(){
				
				efectoProcesamiento(idBoton,idLoader,false);
				$('#modalModificarAprendiz').modal('hide');
				tostadaActualizar();	
				listarTodosFiltroAprendiz();

			}).fail(function(){
				efectoProcesamiento(idBoton,idLoader,false);
				tostadaError();
			});

		}		
	})
}


// Consultar por filtros del aprendiz
function listarTodosFiltroAprendiz(){
	
	var identificacionFiltro = $("#txtIdentificacionAprendizFiltro").val();
	var nombreCompletoFiltro = $.trim($("#txtNombreCompletoAprendizFiltro").val());
	var idFichaFiltro = $("#selectFichaAprendizFiltro").val();
	var idDetalleEstadoFiltro = $("#selectDetalleEstadoAprendizFiltro").val();
	
	
	listarTodosAprendizGenerico("listarTodosFiltro?identificacion="+identificacionFiltro+"&nombreCompleto="+nombreCompletoFiltro+"&idFicha="+idFichaFiltro+"&idDetalleEstado="+idDetalleEstadoFiltro);				
}


//Cuando le unda click consulte los filtros
$("#btnFiltrarAprendiz").click(function(){

	listarTodosFiltroAprendiz();
});


//Refrescar filtro aprendiz y listar todos
$("#btnRefrescarFiltroAprendiz").click(function(){
	
	listarTodosAprendiz();
});




//Validar el formulario de registro aprendiz
function validarRegistroAprendiz(){

	$("#formRegistroAprendiz").validate({
		rules : {
			txtIdentificacionAprendiz : {
				required : true,
				number: true,
				remote : function () { 
					var devolver = {
							url: "/aprendiz/existeIdentificacion?identificacion="+$("#txtIdentificacionAprendiz").val(),
							type: "get",
							dataType: "json"
					};

					return devolver;
				}
			},
			txtNombreCompletoAprendiz : {
				required : true
			},
			selectFichaAprendiz : {
				required : true,
				min : 1
			},
			selectDetalleEstadoAprendiz : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtIdentificacionAprendiz : {
				required : "Debe escribir una identificacion",
				number : "Debe escribir solo numeros",
				remote : "Esta identificacion ya existe"
			},
			txtNombreCompletoAprendiz : "Debe escribir el nombre completo",
			selectFichaAprendiz : "Debe escoger una ficha",
			selectDetalleEstadoAprendiz : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})	
}


//Validar el formulario de modificar aprendiz
function validarModificarAprendiz(){
	
	$("#formModificarAprendiz").validate({
		rules : {
			txtIdentificacionAprendizMod : {
				required : true,
				number : true
			},
			txtNombreCompletoAprendizMod : {
				required : true
			},
			selectFichaAprendizMod : {
				required : true,
				min : 1
			},
			selectDetalleEstadoAprendizMod : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtIdentificacionAprendizMod : {
				required : "Debe escribir una identificacion",
				number : "Debe escribir solo numeros"
			},
			txtNombreCompletoAprendizMod : "Debe escribir el nombre completo",
			selectFichaAprendizMod : "Debe escoger una ficha",
			selectDetalleEstadoAprendizMod : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})

}


/* ====== Cierre aprendiz  ====== */


