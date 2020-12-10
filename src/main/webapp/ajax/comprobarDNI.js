$(document).ready(function () {

    $("#dni").blur(function () {
        $("#mensaje").empty();
        $("#enviar").removeAttr("disabled");
        if ($("#dni").val().match("[0-9]{8}[A-Za-z]{1}")) {
            $.ajax({
                type: "post",
                url: "Ajax", //Nombre del servlet
                data: {
                    accion: "checkDNI", dni: $('#dni').val()//Mensaje enviado al servlet, como clave: valor
                },
                success: function (respuesta) { //Sucede al recibir una respuesta válida
                    $("#mensaje").empty();
                    console.log(respuesta);
                    if (respuesta !== null) {
                        $("#mensaje").append(respuesta.mensaje);
                        $("#enviar").attr("disabled", "disabled");
                    } else {
                        $("#enviar").removeAttr("disabled");

                    }

                }

            });
        } else {
            $("#mensaje").append("DNI no valido.");
            $("#enviar").attr("disabled", "disabled");
        }

    });
});
