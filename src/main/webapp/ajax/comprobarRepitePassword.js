$(document).ready(function () {

    $("#repitePassword").blur(function () {

        $.ajax({
            type: "post",
            url: "Ajax", //Nombre del servlet
            data: {
                accion: "checkPassword", password: $('#password').val(), repite: $('#repitePassword').val()//Mensaje enviado al servlet, como clave: valor
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
    });


});