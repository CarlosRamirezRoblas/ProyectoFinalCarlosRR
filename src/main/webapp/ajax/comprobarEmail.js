$(document).ready(function () {

    $("#email").blur(function () {
        $("#mensaje").empty();
        $("#enviar").removeAttr("disabled");
            $.ajax({
                type: "post",
                url: "Ajax", //Nombre del servlet
                data: {
                    accion: "checkEmail", email: $('#email').val()//Mensaje enviado al servlet, como clave: valor
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
