document.addEventListener("DOMContentLoaded", function() {
document.getElementById("formulario").addEventListener('submit', val_redirect); 
});

function val_redirect(evento){
	evento.preventDefault();
	
	 var direccion = document.getElementById('direccion').value;
	  var referencias = document.getElementById('referencias').value;

	 if(direccion.length == 0){
		   Swal.fire({
			  title: 'INGRESE UNA DIRECCION',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else if(referencias.length == 0){
		   Swal.fire({
			  title: 'INGRESE UNA REFERENCIA',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }	else{
		 var val=0;
	 }
	 
	 
		
		
//envio de fatos y redireccionado a reserva2
		  if(val !=1 ){
		  		this.submit();
		  }
}