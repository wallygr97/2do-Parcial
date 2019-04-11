<!DOCTYPE html>
<html lang="en">
<head>
	<title>Formulario de encuesta</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">

	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/offline-language-english.css">
	<link rel="stylesheet" type="text/css" href="css/offline-language-english-indicator.css">
	<!--===============================================================================================-->
	<style>
		.hiddenValue{
			display: none;
		}
	</style>
</head>
<body>
<div class="container-contact100">
	<div class="wrap-contact100">
		<form class="contact100-form validate-form" action="/registrarEncuesta" method="post">
				<span class="contact100-form-title">
					Formulario
				</span>

			<div class="wrap-input100 validate-input bg1" data-validate="Se debe entrar el nombre de la persona!" required>
				<span class="label-input100">Nombre completo</span>
				<input  class="input100" type="text" name="nombre" placeholder="Entra el nombre de la persona">
			</div>

			<!--div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Enter Your Email (e@a.x)">
                <span class="label-input100">Email *</span>
                <input class="input100" type="text" name="email" placeholder="Enter Your Email ">
            </div>
            <div class="wrap-input100 bg1 rs1-wrap-input100">
                <span class="label-input100">Phone</span>
                <input class="input100" type="text" name="phone" placeholder="Enter Number Phone">
            </div-->

			<div class="wrap-input100 input100-select bg1" data-validate="Se debe entrar el sector de la persona!">
				<span class="label-input100">Sector</span>
				<div>
					<select id="sector" class="js-select2" name="sector">
						<option>Elige el sector</option>
						<#list sectores as sector>
							<option>${sector.sector}</option>
						</#list>
					</select>
					<div class="dropDownSelect2"></div>
				</div>
			</div>

			<div class="wrap-input100 input100-select bg1" data-validate="Se debe entrar el nivel escolar de la persona!">
				<span class="label-input100">Nivel escolar</span>
				<div>
					<select id="nivel" class="js-select2" name="nivel">
						<option>Elige el nivel educativo</option>
						<#list nivelesEducativos as nivel>
							<option>${nivel.nivel}</option>
						</#list>
					</select>
					<div class="dropDownSelect2"></div>
				</div>
			</div>



			<div class="wrap-input100 validate-input bg0 rs1-alert-validate">
				<span class="label-input100">Localización</span>
				<textarea id="geolocalizacion" class="input100" name="geolocalizacion" readonly="readonly">No se pudo obtener su localización...</textarea>
				<div class="hiddenValue">
					<p id="LblLat" name="LblLat1">0</p>
					<p id="LblLon" name="LblLon1">0</p>
				</div>
			</div>

			<div class="container-contact100-form-btn">
				<button type="button" class="contact100-form-btn" onclick="crearEncuesta();">
						<span>
							Enviar
							<i class="fas fa-angle-right"></i>
						</span>
				</button>
			</div>
		</form>
	</div>
</div>



<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<script>
	$(".js-select2").each(function(){
		$(this).select2({
			minimumResultsForSearch: 20,
			dropdownParent: $(this).next('.dropDownSelect2')
		});
		$(".js-select2").each(function(){
			$(this).on('select2:close', function (e){
				if($(this).val() == "Please chooses") {
					$('.js-show-service').slideUp();
				}
				else {
					$('.js-show-service').slideUp();
					$('.js-show-service').slideDown();
				}
			});
		});
	})
</script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/geolocation.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>



<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<!-- Include Dexie -->
<script src="https://unpkg.com/dexie@latest/dist/dexie.js"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag(){dataLayer.push(arguments);}
	gtag('js', new Date());
	gtag('config', 'UA-23581568-13');
</script>
<script>
	var db = new Dexie("Gerard_Encuesta_nuevo");
	db.version(1).stores({
		encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
	});
	function crearEncuesta(){
		var lat=document.getElementById("LblLat").textContent;;
		var lon=document.getElementById("LblLon").textContent;;
		var nombre = document.getElementsByName("nombre")[0].value;
		var sector = document.getElementsByName("sector")[0].value;
		var nivel = document.getElementsByName("nivel")[0].value;
		console.log("Nombre: " + nombre +" Sector: " + sector + " Nivel: "+ nivel + " Latitud: " + lat + " Longitud" + lon);
		db.encuestas.add({nombre: nombre, sector: sector, nivel: nivel, longitud: lon, latitud: lat});
		alert(" Se ha guardado la encuesta en el registro local.");
		borrarCampos();
	}
	function borrarCampos(){
		document.getElementsByName("nombre")[0].value = '';
		$('#sector').val($("#sector option:first").val()).trigger('change.select2');
		$('#nivel').val($("#nivel option:first").val()).trigger('change.select2');
	}
</script>
</body>
</html>