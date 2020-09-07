$('#myTab a').on('click', function (e) {
  e.preventDefault()
  $(this).tab('show')
})

function crearServicio(action) {
    ajaxCreate('POST', '/servicio/create', '#formServicio', 'Servicio', action);
}

function crearReservacion(action) {
    ajaxCreate('POST', '/reservacion/save', '#formReservacion', 'Reservación', action);
}

function eliminar(modelo, id) {
	Swal.fire({
		  title: 'Se va a eliminar el registro',
		  text: "¿Seguro?, no podrás revertir esta operación",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Si, eliminar',
		  cancelButtonText: 'No, salir',
		}).then((result) => {
		  if (result.value) {
			  ajax('POST', '/' + modelo + '/delete/' + id, {}, function() {
					Swal.fire({
						  title: 'Registro eliminado correctamente',
						  text: "Se ha eliminado correctamente el registro.",
						  icon: 'success',
						  confirmButtonText: 'Aceptar'
						}).then((result) => {
						  window.location.reload();
						})
				}, function() {
					Swal.fire({
						  title: '¡Ha ocurrido un error!',
						  text: "No se ha podido eliminar el registro",
						  icon: 'error',
						  confirmButtonText: 'Ok'
						})
				});
		  }
		})
	
}


function ajaxCreate(method, url, form, name, action) {
	var dataForm = objectifyForm($(form).serializeArray());	
	console.log('Enviando formulario', $(form), dataForm)
	var requestBody = JSON.stringify(dataForm);
	$.ajax({
		url : url,
		method : method,
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			Swal.fire({
				  title: name + ' ' + action,
				  text: name.toLowerCase() +" ha sido "+ action  +" correctamente.",
				  icon: 'success',
				  confirmButtonText: 'Aceptar'
				}).then((result) => {
				  window.location.reload();
				})
		},
		error : function(err){
			Swal.fire({
				  title: name + ' NO ' + action,
				  text: "Verifique que los datos del formulario sean correctos",
				  icon: 'error',
				  confirmButtonText: 'Ok'
				})
		}		
	});
}



function ajax(method, url, form, correcto, incorrecto) {
    var dataForm = objectifyForm($("#formServicio").serializeArray());
    var requestBody = JSON.stringify(dataForm);
    $.ajax({
        url: url,
        method: method,
        contentType: "application/json",
        headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
        data: requestBody,
        success: function (response) {
            if (typeof correcto === 'function') {
                correcto()
            }
        },
        error: function (err) {
            if (typeof incorrecto === 'function') {
                incorrecto()
            }
        }
    });
}

function ajaxReport(url, correcto, incorrecto) {
    $.ajax({
        url: url,
        method: 'GET',
        contentType: "application/json",
        success: function (response) {
            if (typeof correcto === 'function') {
                correcto(response)
            }
        },
        error: function (err) {
            if (typeof incorrecto === 'function') {
                incorrecto(err)
            }
        }
    });
}




function objectifyForm(formArray) {
	var returnArray = {};
	for (var i = 0; i < formArray.length; i++) {
		returnArray[formArray[i]['name']] = formArray[i]['value'];
	}
	return returnArray;
}


function chart1() {

	ajaxReport('/reservacion/reporte-reservas-por-servicio', (response) => {
		console.log('res', response);
		
		const labels = response.map((data) => data.servicio);
		const data = response.map((data) => data.total);
		const colors = response.map((data) => random_rgba(1));
		const bgColors = response.map((data) => random_rgba(0.2));
		
		console.log('res2', labels, data);
		
		var ctx = document.getElementById('chart1');
		ctx.height= 150;
		var myChart = new Chart(ctx, {
		    type: 'line',
		    responsive: true,
		    data: {
		        labels,
		        datasets: [{
		            label: 'Reservaciones por servicio',
		            data,
		            backgroundColor: bgColors,
		            borderColor: colors,
		            borderWidth: 1
		        }]
		    },
		    options: {
		    	maintainAspectRatio: true,
		        scales: {
		            yAxes: [{
		            	stacked: true,
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        },
		        
		    }
		});
		
	});
}

function chart2() {

	ajaxReport('/reservacion/reporte-reservas-pendientes', (response) => {
		console.log('res', response);
		
		const labels = response.map((data) => data.servicio);
		const data = response.map((data) => data.total);
		const colors = response.map((data) => random_rgba(1));
		const bgColors = response.map((data) => random_rgba(0.2));
		
		console.log('res2', labels, data, colors);
		
		var ctx = document.getElementById('chart2');
		ctx.height= 150;
		var myChart = new Chart(ctx, {
		    type: 'line',
		    responsive: true,
		    data: {
		        labels,
		        datasets: [{
		            label: 'Reservaciones Pendientes',
		            data,
		            backgroundColor: bgColors,
		            borderColor: colors,
		            borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		            	stacked: true,
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        },
		        
		    }
		});
		
	});
}

function random_rgba(opacity) {
    var o = Math.round, r = Math.random, s = 255;
    console.log('color', 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + opacity + ')')
    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + opacity + ')';
}


$(document).ready(() => {
	chart1();
		chart2();
				
})