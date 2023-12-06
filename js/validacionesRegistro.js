
document.addEventListener("DOMContentLoaded", function() {
document.getElementById("formulario").addEventListener('submit', validacion_registro); 
});

function validacion_registro(evento){
	evento.preventDefault();
	
	 var nombre = document.getElementById('nombre').value;
	  var apellidoPat = document.getElementById('apellidoPat').value;
	  var apellidoMat = document.getElementById('apellidoMat').value;
	  var celular = document.getElementById('celular').value;
	  var codigo = document.getElementById('apellidoMat').value;
	  var password = document.getElementById('celular').value;


	 if(nombre.length <2){
		   Swal.fire({
			  title: 'INGRESE UN NOMBRE VALIDO',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else if(apellidoPat.length < 2){
		   Swal.fire({
			  title: 'INGRESE UN APELLIDO VALIDO',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else if(apellidoMat.length< 2){
		   Swal.fire({
			  title: 'INGRESE UN APELLIDO VALIDO',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else if(celular.length != 9 ){
		   Swal.fire({
			  title: 'INGRESE UN NUMERO DE TELEFONO VALIDO',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }	else if(codigo.length < 3 ){
		   Swal.fire({
			  title: 'INGRESE UN NUMERO DE TELEFONO VALIDO',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else if(password.length <5 ){
		   Swal.fire({
			  title: 'INGRESE UNA CONTRASEÑA ',
			  width: 600,
			  padding: '2em',
			  color: '#07afd9',
			  background: '#24303c ',
	 
		})
			val=1;
	 }else{ 
		 var val=0;
	 }
	 
	 
		
		
//envio de fatos y redireccionado a reserva2
		  if(val !=1 ){
			 Swal.fire({
			  icon: 'success',
			  title: 'REGISTRO EXITOSO',
			
			})
			  setTimeout(() => {
  				this.submit();
			},2000);
		  		
		  }
}
    