var host = "http://172.24.1.33:8080";


// Metodo para consultar cualquier servicio rest
function consultar(tabla,metodo,json){
	
	var tipoDato = "";
	
	if (json){
		tipoDato = "json";
	}
	
	
	return $.ajax({
		
		type : "GET",
		
		dataType : tipoDato,
		
		url : host+"/"+tabla+"/"+metodo

	}).fail(function(jqXHR, textStatus, errorThrown) {

		console.log("La solicitud a fallado: " + textStatus);		
	});
	
}



// Metodo para insertar o actualizar
function guardar(tabla,objeto,httpMetodo,devolver){
	
	var metodo = "";
	
	if (httpMetodo == "POST"){
		
		metodo = "insertar";
	}
	
	if (httpMetodo == "PUT"){
				
		metodo = "actualizar";
	}
	
	if (devolver){
		
		metodo = "insertarDevolver";
	}

	return $.ajax({
		 
		type : httpMetodo,
			 
		data : JSON.stringify(objeto),

		url : host+"/"+tabla+"/"+metodo,
			
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

// Metodo para eliminar un registro
function eliminar(tabla,id){
	
	return $.ajax({
		
		type : "DELETE",
		
		url : host+"/"+tabla+"/eliminar?id="+id

	}).fail(function(jqXHR, textStatus, errorThrown) {

		console.log("La solicitud a fallado: " + textStatus);
	});
		
}

//Metodo para modificar el instructor-ficha
function modificarInstructorFicha(objeto){

	return $.ajax({
		 
		type : "PUT",
			 
		data : JSON.stringify(objeto),

		url : host+"/instructorFicha/actualizarCustom",
			
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





//Cargar en un select el maestro
function cargarMaestro(idSelect){
	
	consultar("maestro","listarTodos",true).done(function(data){
		
		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){
			
			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.nombre+"</option>");
		});
	});
	
}

//Cargar en un select el estado
function cargarEstado(idSelect){

	consultar("detalle","listarTodosPorIdMaestro?idMaestro=1",true).done(function(data){

		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){

			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.nombre+"</option>");				
		});

	});

}

//Cargar en un select la sede
function cargarSede(idSelect){
	
	consultar("detalle","listarTodosPorIdMaestro?idMaestro=4",true).done(function(data){

		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){

			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.nombre+"</option>");				
		});

	});

}

//Cargar en un select el tipo formacion
function cargarTipoFormacion(idSelect){

	consultar("detalle","listarTodosPorIdMaestro?idMaestro=5",true).done(function(data){

		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");

		data.forEach(function(item){

			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.nombre+"</option>");				
		});

	});
}

//Cargar en un select la ficha
function cargarFicha(idSelect){
	
	consultar("ficha","listarTodos",true).done(function(data){
		
		$("#"+idSelect).empty();
		$("#"+idSelect).append("<option value='0'>-- Seleccione --</option>");
		
		data.forEach(function(item){
			
			$("#"+idSelect).append("<option value='"+item.id+"'>"+item.numero+"</option>");				
		});

	});
}





function autocompletar(data, idInput) {
	
	$("#"+idInput).autocomplete({
		source: data
	});
}

//Limpiar todo el formulario
function limpiarFormulario(idFormulario){
	
	document.getElementById(idFormulario).reset();					
	$("#"+idFormulario).validate().resetForm();	
	$("#"+idFormulario+" *").removeClass("valido-validate error-validate");
}


//Activar o desactivar el efecto de procesamiento
function efectoProcesamiento(idBoton,idLoader,controlar){
			
	if (controlar){
		idBoton.attr("disabled","disabled");		
		idLoader.addClass("loader");	
	}else{
		
		idBoton.removeAttr("disabled");
		idLoader.removeClass("loader");
	}

}


//Limpiar totalmente las cosas de la tabla
function limpiarTabla(idCuerpoTabla,idLoaderTabla,idAlertaTabla){
	
	idCuerpoTabla.empty();
	idLoaderTabla.empty();
	idAlertaTabla.empty();
}


//Agregar o quitar el loader de la tabla
function loaderTabla(divLoader,controlar){
	
	if(controlar){
		divLoader.append('<center><div class="loader"></div></center>');
	}else{
		divLoader.empty();
	}
}


//Verificar si no tiene datos la tabla y poner un alert informandolo
function verificarDatosTabla(idAlertaTabla,cantidadDatos){
	
	if (cantidadDatos == 0){
		
		agregarAlertaTabla(idAlertaTabla,"nodatos");		
	}
	
}



//Agregar una alerta a la tabla dependiendo del tipo de alerta
function agregarAlertaTabla(idAlertaTabla,tipoAlerta){
		
	var divAlerta = "";
	

	if (tipoAlerta == "error"){
		
		divAlerta = '<div class="alert alert-danger" role="alert">'+
					'Ha ocurrido un error, intentelo nuevamente'+
					'</div>';			
	}
	
	if (tipoAlerta == "nodatos"){
		
		divAlerta = '<div class="alert alert-info" role="alert">'+
					'No se encuentran datos en esta consulta'+
					'</div>';		
	}
	
	idAlertaTabla.append(divAlerta);	
}

//Tostadas de notificacion para diferentes acciones
function tostadaRegistro(){
	
	toastr["success"]("Se ha registrado correctamente");
}

function tostadaActualizar(){
	
	toastr["info"]("Se ha actualizado correctamente");
}

function tostadaError(){
	
	toastr["error"]("Ha ocurrido un error, intentelo nuevamente");
}

function tostadaEliminar(){
	
	toastr["success"]("Se ha eliminado correctamente");
}

function tostadaErrorEliminar(){
	
	toastr["error"]("No se pudo eliminar, intentelo nuevamente");
}

function tostadaErrorCustom(mensaje){
	
	toastr["error"](mensaje);
}



