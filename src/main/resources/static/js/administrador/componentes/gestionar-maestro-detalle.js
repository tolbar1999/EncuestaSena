/* ====== Tab maestro  ====== */
	
	$("#navMaestro").click(function(){
	
		listarTodosMaestro();
	});
	
	validarRegistroMaestro();
	validarModificarMaestro();
	confirmarModificarMaestro();
	confirmarEliminarMaestro();

	
	
	//Registrar el maestro
	$("#btnRegistrarMaestro").click(function(){
	
		if($("#formRegistroMaestro").valid()){
	
			var idBoton = $("#btnRegistrarMaestro");
			var idLoader = $("#loaderRegistroMaestro");
	
			efectoProcesamiento(idBoton,idLoader,true);
	
	
			var nombre = $.trim($("#txtNombreMaestro").val());
			var descripcion = $.trim($("#txtDescripcionMaestro").val());
	
	
			var maestro = {			
					"nombre" : nombre,
					"descripcion" : descripcion
			}
	
	
			guardar("maestro",maestro,"POST").done(function(){
				
					listarTodosMaestro();
					limpiarFormulario("formRegistroMaestro");
					efectoProcesamiento(idBoton,idLoader,false);
					tostadaRegistro();
	
			}).fail(function(){
					efectoProcesamiento(idBoton,idLoader,false);	
					tostadaError();
			});
	
		}
	
	})
	
	
	//Listar todos los datos del maestro
	function listarTodosMaestro(){
	
		listarTodosMaestroGenerico("listarTodos");
	
	}
	
	
	//Listar todos los datos del maestro generico
	function listarTodosMaestroGenerico(metodo){
	
		var idCuerpoTabla = $("#cuerpoTablaMaestro");
		var idLoaderTabla = $("#loaderTablaMaestro");	
		var idAlertaTabla = $("#alertaTablaMaestro");
	
		limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla);
	
		loaderTabla(idLoaderTabla,true);
	
	
		consultar("maestro",metodo,true).done(function(data){
	
			var cantidadDatos = data.length;
			var contador = 1;
	
			data.forEach(function(item){
	
				var datoNumero = $("<td></td>").text(contador);
				var datoNombre = $("<td></td>").text(item.nombre);
				var txtAreaDescripcion = $("<textarea class='textarea-table' disabled='disabled'></textarea>").text(item.descripcion);
	
	
				var datoOpciones = "<td>"+
				'<button id="btnModificarMaestro'+contador+'" class="btn btn-table espacioModificar" data-toggle="modal" data-target="#modalModificarMaestro"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>'+
				'<button id="btnEliminarMaestro'+contador+'" class="btn btn-danger btn-table" data-toggle="modal" data-target="#modalEliminarMaestro"><i class="fa fa-trash-o" aria-hidden="true"></i></button>'+
				"</td>";
	
	
				var datoDescripcion = $("<td></td>").append(txtAreaDescripcion);
	
				
				var fila = $("<tr></tr>").append(datoNumero,datoNombre,datoDescripcion,datoOpciones);
	
				idCuerpoTabla.append(fila);
	
	
				asignarEventoClickMaestro(item.id,item.nombre,item.descripcion,contador);
	
				contador++;                        	
			})
	
				loaderTabla(idLoaderTabla,false);
				verificarDatosTabla(idAlertaTabla,cantidadDatos);
				idCuerpoTabla.show();
	
		}).fail(function(){	
				loaderTabla(idLoaderTabla,false);
				agregarAlertaTabla(idAlertaTabla,"error");
		})
	}
	
	
	//Asignarle evento click a el modificar/eliminar del Maestro
	function asignarEventoClickMaestro(idMaestro,nombre,descripcion,contador){
	
		$("#btnModificarMaestro"+contador).click(function(){
	
			limpiarFormulario("formModificarMaestro");
	
			$("#txtIdMaestro").val(idMaestro);
			$("#txtNombreMaestroMod").val(nombre);
			$("#txtDescripcionMaestroMod").val(descripcion);
	
		});
		
		$("#btnEliminarMaestro"+contador).click(function(){
	
			$("#txtIdMaestro").val(idMaestro);	
	
		});
	
	}
	
	
	//Funcion para cuando le unda guardar cambios, modifique el registro
	function confirmarModificarMaestro(){
		
		var idBoton = $("#btnConfirmarModificarMaestro");
		var idLoader = $("#loaderModificarMaestro");
	
		idBoton.click(function(){
	
			if($("#formModificarMaestro").valid()){
	
				efectoProcesamiento(idBoton,idLoader,true);
	
	
				var idMaestro = $("#txtIdMaestro").val();
	
	
				var nombre = $.trim($("#txtNombreMaestroMod").val());
				var descripcion = $.trim($("#txtDescripcionMaestroMod").val());
	
	
				var maestro = {		
						"id" : idMaestro,
						"nombre" : nombre,
						"descripcion" : descripcion
				}
	
	
				guardar("maestro",maestro,"PUT",false).done(function(){
	
						efectoProcesamiento(idBoton,idLoader,false);
						$('#modalModificarMaestro').modal('hide');
						listarTodosMaestro();
						tostadaActualizar();	
	
				}).fail(function(){
						efectoProcesamiento(idBoton,idLoader,false);
						tostadaError();
				});
	
			}		
		})
	}
	
	
	//Funcion para cuando le undan confirmar, elimine el registro
	function confirmarEliminarMaestro(){
	
		var idBoton = $("#btnConfirmarEliminarMaestro");
		var idLoader = $("#loaderEliminarMaestro");
	
		idBoton.click(function(){
			
			efectoProcesamiento(idBoton,idLoader,true);
			
			var idMaestro = $("#txtIdMaestro").val();
	
			eliminar("maestro",idMaestro).done(function(){	
	
					efectoProcesamiento(idBoton,idLoader,false);
					$('#modalEliminarMaestro').modal('hide');
					listarTodosMaestro();
					tostadaEliminar();			
	
			}).fail(function(){
	
					efectoProcesamiento(idBoton,idLoader,false);
					tostadaErrorEliminar();
			});	
		})
	}
		
	//Validar el formulario de registro maestro
	function validarRegistroMaestro(){
	
		$("#formRegistroMaestro").validate({
			rules : {
				txtNombreMaestro : {
					required : true
				},
				txtDescripcionMaestro : {
					required : true
				}
			},
			messages : {
				txtNombreMaestro : "Debe escribir un nombre",
				txtDescripcionMaestro : "Debe escribir una descripcion"
			},
			normalizer : function(valor) {
				return $.trim(valor);
			},
			validClass: 'valido-validate',
			errorClass: "error-validate"
		})	
	}
	
	
	//Validar el formulario de modificar maestro
	function validarModificarMaestro(){
	
		$("#formModificarMaestro").validate({
			rules : {
				txtNombreMaestroMod : {
					required : true
				},
				txtDescripcionMaestroMod : {
					required : true
				}
			},
			messages : {
				txtNombreMaestroMod : "Debe escribir un nombre",
				txtDescripcionMaestroMod : "Debe escribir una descripcion"
			},
			normalizer : function(valor) {
				return $.trim(valor);
			},
			validClass: 'valido-validate',
			errorClass: "error-validate"
		})
	
	}
/* ====== Cierre tab maestro  ====== */