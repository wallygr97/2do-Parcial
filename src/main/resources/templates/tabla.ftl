<!DOCTYPE html>
<html lang="en">
<head>
	<title>Table V01</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="Table_Responsive_v1/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/css/util.css">
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/css/main.css">
<!--===============================================================================================-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<!--===============================================================================================-->
<style>
    .refresh{
        padding-bottom: 20px;
        margin-left: 36px;
    }
    .container-table100{
        background-image: url("/images/2.jpg");
    }
</style>
</head>
<body>
	<div class="limiter">
		<div class="container-table100">
            <div class="refresh">
                <button onclick="llenarTabla();" class="btn btn-success" id="btn_refresh"> <i class="fas fa-sync-alt"></i> Actualizar Tabla </button>
            </div>
            <div class="refresh">
                <button onclick="sincronizarEncuestas()" class="btn btn-success" id="sincronizar-btn"> <i class="fas fa-upload"></i> Sincronizar Encuestas </button>
            </div>
            <div class="refresh">
                <button class="btn btn-success" onclick="window.location='/listaEncuestasDB';"> <i class="fas fa-table"></i> Ver Datos Online </button>
            </div>
            <div class="wrap-table100">
				<div class="table100">
					<table id="tablaEncuestas">
						<thead>
							<tr class="table100-head">
								<th class="column1">Código</th>
								<th class="column2">Nombre</th>
								<th class="column3">Sector</th>
								<th class="column4">Nivel Escolar</th>
								<th class="column5">Latitud</th>
								<th class="column6">Longitud</th>
								<th class="column7">Modificar</th>
								<th class="column8">Eliminar</th>
							</tr>
						</thead>
						<tbody id="tb">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

    <!-- Modal -->
    <div class="modal fade" id="modificar-encuesta" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button id="cerrar-modal" type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4><span class="glyphicon glyphicon-lock"></span> Modificar Encuesta </h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <form role="form">
                        <div class="wrap-input100 validate-input bg1" data-validate="Se debe entrar el nombre de la persona!">
                            <span class="label-input100">Nombre completo</span>
                            <input id="nombre-modificar" class="input100" type="text" name="nombre" placeholder="Entra el nombre de la persona">
                        </div>
                        <div class="wrap-input100 input100-select bg1" data-validate="Se debe entrar el sector de la persona!">
                            <span class="label-input100">Sector</span>
                            <div>
                                <select id="sector-modificar" class="js-select2" name="sector">
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
                                <select id="nivel-modificar" class="js-select2" name="nivel">
                                    <option>Elige el nivel educativo</option>
								<#list nivelesEducativos as nivel>
                                    <option>${nivel.nivel}</option>
								</#list>
                                </select>
                                <div class="dropDownSelect2"></div>
                            </div>
                        </div>
                        <button id="guardar-modificacion" type="button" class="contact100-form-btn">
							<span>
							Guardar
							<i class="fas fa-angle-right"></i>
							</span>
						</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Include Dexie -->
    <script src="https://unpkg.com/dexie@latest/dist/dexie.js"></script>

	<script>
		function insertarFila(encuesta){
            var table = document.getElementsByTagName("tbody")[0];
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var codigo = row.insertCell(0);
            var nombre = row.insertCell(1);
            var sector = row.insertCell(2);
            var nivelEscolar = row.insertCell(3);
            var lat = row.insertCell(4);
            var lon = row.insertCell(5);
            var modificarBtn = row.insertCell(6);
            var eliminarBtn = row.insertCell(7);


            codigo.className = "column1";
            nombre.className = "column2";
            sector.className = "column3";
            nivelEscolar.className = "column4";
            lat.className = "column5";
            lon.className = "column6";
            modificarBtn.className = "column7";
            eliminarBtn.className = "column8";

			codigo.innerHTML = encuesta.idEncuesta;
            nombre.innerHTML = encuesta.nombre;
            sector.innerHTML = encuesta.sector;
            nivelEscolar.innerHTML = encuesta.nivel;
            lat.innerHTML = encuesta.latitud;
            lon.innerHTML = encuesta.longitud;
            modificarBtn.innerHTML = '<button class="btn btn-success"  data-toggle="modal" data-target="#modificar-encuesta"> Modificar </button>';
			eliminarBtn.innerHTML = '<button class="btn btn-success" > Eliminar </button>';
			eliminarBtn.onclick = function() {eliminarEncuesta(encuesta.idEncuesta)};
			modificarBtn.onclick = function () { modificarEncuesta(encuesta.idEncuesta) };
            document.getElementById("guardar-modificacion").onclick = function () { actualizarEncuesta(encuesta.idEncuesta)};
        }
        function llenarTabla() {
            var db = new Dexie("Gerard_Encuesta_nuevo");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });

            db.encuestas
					.toArray()
                    .then(function (encuestas) {
                        var table = document.getElementsByTagName("tbody")[0];
                        if(encuestas.length >= 0 && table.rows.length != encuestas.length){
                            while(table.rows.length > 0) {
                                table.deleteRow(0);
                            }
                            for(var i=0; i < encuestas.length; i++ ){
                                console.log(encuestas.toString());
                                insertarFila(encuestas[i]);
                            }
						}
                    });
        }

        function eliminarEncuesta(idEncuesta){
            var db = new Dexie("Gerard_Encuesta_nuevo");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });
            db.encuestas.delete(idEncuesta);
            llenarTabla();
        }

        function modificarEncuesta(id){
            var db = new Dexie("Gerard_Encuesta_nuevo");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });

			db.encuestas.get(id,function (encuesta) {
				$("#nombre-modificar").val(encuesta.nombre);
                $("#sector-modificar").val(encuesta.sector);
                $("#nivel-modificar").val(encuesta.nivel);

				console.log("Id: " + id  + "Nombre: " +  encuesta.nombre.toString() + " Sector: " + encuesta.sector.toString() + " Nivel: " + encuesta.nivel.toString());
			});
        }

        function actualizarEncuesta(id){
            var db = new Dexie("Gerard_Encuesta_nuevo");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });

            db.encuestas.update(parseInt(id),{ nombre: $('#nombre-modificar').val(), sector: $("#sector-modificar").val(), nivel: $("#nivel-modificar").val()})
                    .then(function () {
                        var table = document.getElementsByTagName("tbody")[0];
                        while(table.rows.length > 0) {
                            table.deleteRow(0);
                        }
                        llenarTabla();
                        $("#cerrar-modal").click();
                        alert("La encuesta " + id + " ha sido modificada con éxito");
                    });
        }
        function sincronizarEncuestas(){
            var table = document.getElementsByTagName("tbody")[0];

            if(table.rows.length > 0 ){
                var db = new Dexie("Gerard_Encuesta_nuevo");
                db.version(1).stores({
                    encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
                });

                db.encuestas
                        .toArray()
                        .then(function (encuestas) {
                            var encuestasJson = JSON.stringify(encuestas);
                            $.ajax({
                                data: encuestasJson,
                                type: 'POST',
                                url: "/sincronizarEncuestas",
                                success: function () {
                                    db.encuestas.clear();
                                    llenarTabla();
                                    alert("Las encuestas han sido enviadas al servidor satisfactoriamente");
                                }
                            })
                        });
            }
        }
        
	</script>
    <script>document.getElementById("btn_refresh").click();</script>

</body>
</html>