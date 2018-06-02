
/* ====== Ficha ====== */

listarTodosFicha();
cargarEstado("selectDetalleEstadoFicha");
cargarEstado("selectDetalleEstadoFichaFiltro");
cargarEstado("selectDetalleEstadoFichaMod");


validarRegistroFicha();
validarModificarFicha();
confirmarModificarFicha();



//Registrar la ficha
$("#btnRegistrarFicha").click(function(){

	if($("#formRegistroFicha").valid()){

		var idBoton = $("#btnRegistrarFicha");
		var idLoader = $("#loaderRegistroFicha");

		efectoProcesamiento(idBoton,idLoader,true);


		var numero = $("#txtNumeroFicha").val();
		var jornada = $("#selectQuemadoJornadaFicha").val();
		var idDetalleEstado = $("#selectDetalleEstadoFicha").val();


		var detalleEstado = {			
				"id" : idDetalleEstado			
		}


		var ficha = {			
				"numero" : numero,
				"jornada" : jornada,
				"detalleEstado" : detalleEstado						
		}


		guardar("ficha",ficha,"POST",false).done(function(){

			listarTodosFicha();
			limpiarFormulario("formRegistroFicha");
			efectoProcesamiento(idBoton,idLoader,false);
			tostadaRegistro();

		}).fail(function(){
			
			efectoProcesamiento(idBoton,idLoader,false);	
			tostadaError();
		});

	}

})


//Listar todos los datos de la ficha
function listarTodosFicha(){

	listarTodosFichaGenerico("listarTodos");

}


//Listar todos los datos de la ficha generico
function listarTodosFichaGenerico(metodo){

	var idCuerpoTabla = $("#cuerpoTablaFicha");
	var idLoaderTabla = $("#loaderTablaFicha");	
	var idAlertaTabla = $("#alertaTablaFicha");

	limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);

	loaderTabla(idLoaderTabla,true);


	consultar("ficha",metodo,true).done(function(data){

		var cantidadDatos = data.length;
		var contador = 1;

		data.forEach(function(item){

			var datoNumero = $("<td></td>").text(contador);
			var datoNumeroFicha = $("<td></td>").text(item.numero);
			var datoJornada = $("<td></td>").text(item.jornada);
			var datoDetalleEstado = $("<td></td>").text(item.detalleEstado.nombre);


			var datoOpciones = "<td>"+
			'<button id="btnModificarFicha'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarFicha"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
			"</td>";



			var fila = $("<tr></tr>").append(datoNumero,datoNumeroFicha,datoJornada,datoDetalleEstado,datoOpciones);

			idCuerpoTabla.append(fila);


			asignarEventoClickFicha(item.id,item.numero,item.jornada,item.detalleEstado.id,contador);

			contador++;                        	
		})
		
		loaderTabla(idLoaderTabla,false);
		verificarDatosTabla(idAlertaTabla,cantidadDatos);

	}).fail(function(){	
		loaderTabla(idLoaderTabla,false);
		agregarAlertaTabla(idAlertaTabla,"error");
	})
}


//Asignarle evento click a el modificar de la ficha
function asignarEventoClickFicha(idFicha,numero,jornada,idDetalleEstado,contador){

	$("#btnModificarFicha"+contador).click(function(){

		limpiarFormulario("formModificarFicha");
		
		$("#txtIdFicha").val(idFicha);
		$("#txtNumeroFichaMod").val(numero);
		$("#selectQuemadoJornadaFichaMod").val(jornada);
		$("#selectDetalleEstadoFichaMod").val(idDetalleEstado);

	});
}


//Funcion para cuando le unda guardar cambios, modifique el registro
function confirmarModificarFicha(){
	
	var idBoton = $("#btnConfirmarModificarFicha");
	var idLoader = $("#loaderModificarFicha");

	idBoton.click(function(){

		if($("#formModificarFicha").valid()){

			efectoProcesamiento(idBoton,idLoader,true);


			var idFicha = $("#txtIdFicha").val();

			var numero = $("#txtNumeroFichaMod").val();
			var jornada = $("#selectQuemadoJornadaFichaMod").val();
			var idDetalleEstado = $("#selectDetalleEstadoFichaMod").val();
			

			var detalleEstado = {			
					"id" : idDetalleEstado			
			}


			var ficha = {		
					"id" : idFicha,
					"numero" : numero,
					"jornada" : jornada,
					"detalleEstado" : detalleEstado
			}


			guardar("ficha",ficha,"PUT",false).done(function(){

				efectoProcesamiento(idBoton,idLoader,false);
				$('#modalModificarFicha').modal('hide');
				tostadaActualizar();	
				listarTodosFiltroFicha();

			}).fail(function(){
				efectoProcesamiento(idBoton,idLoader,false);
				tostadaError();
			});

		}		
	})
}


// Consultar por filtros de la ficha
function listarTodosFiltroFicha(){
	
	var numeroFiltro = $("#txtNumeroFichaFiltro").val();
	var jornadaFiltro = $("#selectQuemadoJornadaFichaFiltro").val();
	var idDetalleEstadoFiltro = $("#selectDetalleEstadoFichaFiltro").val();
	
	listarTodosFichaGenerico("listarTodosFiltro?numero="+numeroFiltro+"&jornada="+jornadaFiltro+"&idDetalleEstado="+idDetalleEstadoFiltro);				
}


//Cuando le unda click consulte los filtros
$("#btnFiltrarFicha").click(function(){

	listarTodosFiltroFicha();

});


//Refrescar filtro ficha y listar todos
$("#btnRefrescarFiltroFicha").click(function(){

	listarTodosFicha();
});




//Validar el formulario de registro ficha
function validarRegistroFicha(){

	$("#formRegistroFicha").validate({
		rules : {
			txtNumeroFicha : {
				required : true,
				number: true,
				remote : function () { 
					var devolver = {
							url: "/ficha/existeNumero?numero="+$("#txtNumeroFicha").val(),
							type: "get",
							dataType: "json"
					};

					return devolver;
				}
			},
			selectQuemadoJornadaFicha : {
				required : true
			},
			selectDetalleEstadoFicha : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtNumeroFicha : {
				required : "Debe escribir una ficha",
				number : "Debe escribir solo numeros",
				remote : "Esta ficha ya existe"
			},
			selectQuemadoJornadaFicha : "Debe escoger una jornada",
			selectDetalleEstadoFicha : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})	
}


//Validar el formulario de modificar ficha
function validarModificarFicha(){
 
	$("#formModificarFicha").validate({
		rules : {
			txtNumeroFichaMod : {
				required : true,
				number : true
			},
			selectQuemadoJornadaFichaMod : {
				required : true
			},
			selectDetalleEstadoFichaMod : {
				required : true,
				min : 1
			}
		},
		messages : {
			txtNumeroFichaMod : {
				required : "Debe escribir una ficha",
				number : "Debe escribir solo numeros"
			},
			selectQuemadoJornadaFichaMod : "Debe escoger una jornada",
			selectDetalleEstadoFichaMod : "Debe escoger un estado"
		},
		normalizer : function(valor) {
			return $.trim(valor);
		},
		validClass: 'valido-validate',
		errorClass: "error-validate"
	})

}
/* ====== Cierre Ficha  ====== */


