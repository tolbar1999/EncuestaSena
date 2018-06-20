
/* ====== Ficha-instructor  ====== */

	listarTodosFichaInstructor();
	cargarEstado("selectDetalleEstadoFi");
	cargarEstado("selectDetalleEstadoFiFiltro");
	cargarEstado("selectDetalleEstadoFiMod");
	
	
	validarRegistroFichaInstructor();
	validarModificarFichaInstructor();
	confirmarModificarFichaInstructor();
	
	
	$(".auto-completar-ficha").keyup(function(){
		
		var inputFicha = this;
		
		var numeroFicha = inputFicha.value;
	
		$.ajax({
	
			type : "GET",
	
			dataType : "json",
			
			url : host+"/ficha/autoCompletarNumeroFicha?numero="+numeroFicha
	
		}).done(function(data, textStatus, jqXHR) {
			
			autocompletar(data.content,inputFicha.id);
			
		}).fail(function(jqXHR, textStatus, errorThrown) {
	
			console.log("La solicitud a fallado: " + textStatus);		
		});
	})
	
	
	$(".auto-completar-instructor").keyup(function(){
		
		var inputNombreCompleto = this;
		
		var nombreCompleto = inputNombreCompleto.value;
		
		$.ajax({
	
			type : "GET",
	
			dataType : "json",
	
			url : host+"/instructor/autoCompletarNombreCompleto?nombreCompleto="+nombreCompleto
	
		}).done(function(data, textStatus, jqXHR) {
	
			autocompletar(data.content,inputNombreCompleto.id);
	
		}).fail(function(jqXHR, textStatus, errorThrown) {
	
			console.log("La solicitud a fallado: " + textStatus);		
		});
	})
	
	
	
	
	//Registrar la ficha-instructor
	$("#btnRegistrarFi").click(function(){
		
		if($("#formRegistroFi").valid()){
	
			var idBoton = $("#btnRegistrarFi");
			var idLoader = $("#loaderRegistroFi");
	
			efectoProcesamiento(idBoton,idLoader,true);
	
			
			var numeroFicha = $("#txtNumeroFichaFi").val();
			var nombreCompletoInstructor = $.trim($("#txtNombreCompletoInstructorFi").val());
			var idDetalleEstado = $("#selectDetalleEstadoFi").val();
	
			consultar("ficha","buscarPorNumeroFicha?numero="+numeroFicha,false).done(function(idFicha){
				
				consultar("instructor","buscarPorNombreCompleto?nombreCompleto="+nombreCompletoInstructor,false).done(function(idInstructor){
						
					var id = {
							"idFicha" : idFicha,
							"idInstructor" : idInstructor
					}
					
					var detalleEstado = {			
							"id" : idDetalleEstado			
					}
			
					
					var instructorFicha = {			
							"id" : id,
							"detalleEstado" : detalleEstado						
					}
			
			
					guardar("instructorFicha",instructorFicha,"POST",true).done(function(respuesta){
						
						if(respuesta){
							
							listarTodosFichaInstructor();
							limpiarFormulario("formRegistroFi");
							efectoProcesamiento(idBoton,idLoader,false);
							tostadaRegistro();
						}else{
							efectoProcesamiento(idBoton,idLoader,false);
							tostadaErrorCustom("Ya se ha asignado este instructor a la ficha");
						}
			 
					}).fail(function(){
			
						efectoProcesamiento(idBoton,idLoader,false);	
						tostadaError();
					});
					
				});
				
			});
		}
	})
	
	
	//Listar todos los datos de ficha-instructor
	function listarTodosFichaInstructor(){
	
		listarTodosFichaInstructorGenerico("listarTodos");
	}
	
	
	//Listar todos los datos de la ficha-instructor generico
	function listarTodosFichaInstructorGenerico(metodo){
	
		var idCuerpoTabla = $("#cuerpoTablaFi");
		var idLoaderTabla = $("#loaderTablaFi");	
		var idAlertaTabla = $("#alertaTablaFi");
	
		limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);
	
		loaderTabla(idLoaderTabla,true);
	
	
		consultar("instructorFicha",metodo,true).done(function(data){
	
			var cantidadDatos = data.length;
			var contador = 1;
			
			data.forEach(function(item){
				
				var datoNumero = $("<td></td>").text(contador);
				var datoFicha = $("<td></td>").text(item.ficha.numero);
				var datoInstructor = $("<td></td>").text(item.instructor.nombreCompleto);
				var datoEstado = $("<td></td>").text(item.detalleEstado.nombre);
	
	
				var datoOpciones = "<td>"+
				'<button id="btnModificarFi'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarFi"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
				"</td>";
	
				
				var fila = $("<tr></tr>").append(datoNumero,datoFicha,datoInstructor,datoEstado,datoOpciones);
	
				idCuerpoTabla.append(fila);
	
				asignarEventoClickFichaInstructor(item.id.idFicha,item.id.idInstructor,item.ficha.numero,item.instructor.nombreCompleto,item.detalleEstado.id,contador);
	
				contador++;                        	
			})
	
			loaderTabla(idLoaderTabla,false);
			verificarDatosTabla(idAlertaTabla,cantidadDatos);
	
		}).fail(function(){	
			loaderTabla(idLoaderTabla,false);
			agregarAlertaTabla(idAlertaTabla,"error");
		})
	}
	
	
	//Asignarle evento click a el modificar de la ficha-instructor
	function asignarEventoClickFichaInstructor(idFichaFi,idInstructorFi,numeroFicha,nombreCompletoInstructor,idDetalleEstado,contador){
		
		$("#btnModificarFi"+contador).click(function(){
			
			limpiarFormulario("formModificarFi");
			
			$("#txtIdFichaFiViejo").val(idFichaFi);
			$("#txtIdInstructorFiViejo").val(idInstructorFi);
			$("#txtNumeroFichaFiMod").val(numeroFicha);
			$("#txtNombreCompletoInstructorFiMod").val(nombreCompletoInstructor);
			$("#selectDetalleEstadoFiMod").val(idDetalleEstado);
			
		});
	}
	
	
	//Funcion para cuando le unda guardar cambios, modifique el registro
	function confirmarModificarFichaInstructor(){

		var idBoton = $("#btnConfirmarModificarFi");
		var idLoader = $("#loaderModificarFi");

		idBoton.click(function(){

			if($("#formModificarFi").valid()){

				efectoProcesamiento(idBoton,idLoader,true);

				var idFichaFiViejo = $("#txtIdFichaFiViejo").val();
				var idInstructorFiViejo = $("#txtIdInstructorFiViejo").val();

				var numeroFicha = $("#txtNumeroFichaFiMod").val();
				var nombreCompletoInstructor = $.trim($("#txtNombreCompletoInstructorFiMod").val());
				var idDetalleEstado = $("#selectDetalleEstadoFiMod").val();


				consultar("ficha","buscarPorNumeroFicha?numero="+numeroFicha,false).done(function(idFicha){

					consultar("instructor","buscarPorNombreCompleto?nombreCompleto="+nombreCompletoInstructor,false).done(function(idInstructor){

						var idFichaInstructorNuevo = {
								
								"idFicha" : idFicha,
								"idInstructor" : idInstructor
						}

						var detalleEstado = {			
								"id" : idDetalleEstado			
						}

						var instructorFichaNuevo = {		
								"id" : idFichaInstructorNuevo,
								"detalleEstado" : detalleEstado						
						}
						
						var instructorFichaIdViejo = {
								
								"idFicha" : idFichaFiViejo,
								"idInstructor" : idInstructorFiViejo
						}
						
						var instructorFichaMod = {
								
								"instructorFichaNuevo" : instructorFichaNuevo,
								"instructorFichaIdViejo" : instructorFichaIdViejo
						}
						
						modificarInstructorFicha(instructorFichaMod).done(function(){
							
							efectoProcesamiento(idBoton,idLoader,false);
							$('#modalModificarFi').modal('hide');
							tostadaActualizar();
							listarTodosFiltroFichaInstructor();
							
						}).fail(function(){
							efectoProcesamiento(idBoton,idLoader,false);
							tostadaError();
						});

					});

				});
			}		
		})
	}

	
	
	//Consultar por filtros de la ficha-instructor
	function listarTodosFiltroFichaInstructor(){
	
		var numeroFichaFiltro = $.trim($("#txtNumeroFichaFiFiltro").val());
		var nombreInstructorFiltro = $.trim($("#txtNombreCompletoInstructorFiFiltro").val());
	
		listarTodosFichaInstructorGenerico("listarTodosFiltro?numeroFicha="+numeroFichaFiltro+"&nombreInstructor="+nombreInstructorFiltro);				
	}
	
	
	//Cuando le unda click consulte los filtros
	$("#btnFiltrarFi").click(function(){
	
		listarTodosFiltroFichaInstructor();
	});
	
	
	//Refrescar filtro ficha-instructor y listar todos
	$("#btnRefrescarFiltroFi").click(function(){
	
		listarTodosFichaInstructor();
	});
	
	
	
	
	//Validar el formulario de registro ficha-instructor
	function validarRegistroFichaInstructor(){
		
		$("#formRegistroFi").validate({
			rules : {
				txtNumeroFichaFi : {
					required : true,
					number : true,
					remote : function () { 
						var devolver = {
								url: "/ficha/existeNumeroInstructorFicha?numero="+$("#txtNumeroFichaFi").val(),
								type: "get",
								dataType: "json"
						};
						
						return devolver;
					}
				},
				txtNombreCompletoInstructorFi : {
					required : true,
					remote : function () { 
						var devolver = {
								url: "/instructor/existeNombreCompleto?nombreCompleto="+$.trim($("#txtNombreCompletoInstructorFi").val()),
								type: "get",
								dataType: "json"
						};
						
						return devolver;
					}
				},
				selectDetalleEstadoFi : {
					required : true,
					min : 1
				}
			},
			messages : {
				txtNumeroFichaFi : {
					required : "Debe escribir una ficha",
					number : "Debe escribir solo numeros",
					remote : "Debe escribir una ficha existente"
				},
				txtNombreCompletoInstructorFi : {
					required : "Debe escribir un instructor",
					remote : "Debe escribir un instructor existente"
				},
				selectDetalleEstadoFi : "Debe escoger un estado"
			},
			normalizer : function(valor) {
				return $.trim(valor);
			},
			validClass: 'valido-validate',
			errorClass: "error-validate"
		})	
	}
	
	
	//Validar el formulario de modificar ficha-instructor
	function validarModificarFichaInstructor(){
	
		$("#formModificarFi").validate({
			rules : {
				txtNumeroFichaFiMod : {
					required : true,
					number : true,
					remote : function () { 
						var devolver = {
								url: "/ficha/existeNumeroInstructorFicha?numero="+$("#txtNumeroFichaFiMod").val(),
								type: "get",
								dataType: "json"
						};
						
						return devolver;
					}
				},
				txtNombreCompletoInstructorFiMod : {
					required : true,
					remote : function () { 
						var devolver = {
								url: "/instructor/existeNombreCompleto?nombreCompleto="+$.trim($("#txtNombreCompletoInstructorFiMod").val()),
								type: "get",
								dataType: "json"
						};
						
						return devolver;
					}
				},
				selectDetalleEstadoFiMod : {
					required : true,
					min : 1
				}
			},
			messages : {
				txtNumeroFichaFiMod : {
					required : "Debe escribir una ficha",
					number : "Debe escribir solo numeros",
					remote : "Debe escribir una ficha existente"
				},
				txtNombreCompletoInstructorFiMod : {
					required : "Debe escribir un instructor",
					remote : "Debe escribir un instructor existente"
				},
				selectDetalleEstadoFiMod : "Debe escoger un estado"
			},
			normalizer : function(valor) {
				return $.trim(valor);
			},
			validClass: 'valido-validate',
			errorClass: "error-validate"
		})	
	
	}
/* ====== Cierre ficha-instructor  ====== */















