
$("#btnIniciarEvaluacion").click(function(e){
	
	var identifcacion = $.trim($("#txtIdentificacionAprendiz").val());
	
	consultar("aprendiz","existeIdentificacionAprendiz?identificacion="+identifcacion,false).done(function(data){
		
		if(data){
			
			location.href = "/encuesta?identificacion="+identifcacion;
		}else{
			
			$("#mensaje").empty().append("<div class='alert alert-danger'>No se ha encontrado el aprendiz</div>");
		}
		
	}).fail(function(){
	
		console.log("Ha ocurrido un error");
	});
	
	
	e.preventDefault();
})





