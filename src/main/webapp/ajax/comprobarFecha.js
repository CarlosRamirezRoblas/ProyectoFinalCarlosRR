$(document).ready(function () {
    var d = new Date();
    if ((d.getMonth() + 1) < 10) {
        var month = "0" + (d.getMonth() + 1);
    } else {
        var month = (d.getMonth() + 1);
    }
    var year = d.getFullYear();
    var day = d.getDate();
    var actual = year + "-" + month + "-" + day;

    $('#fecha').attr("max", actual);

    $("#enviar").click(function () {
        $("#mensaje").empty();
        if ($('#fecha').val() !== "") {
            $("#enviar").removeAttr("disabled");
        } else {
            $("#mensaje").append("Introduzca una fecha.");
            $("#enviar").attr("disabled", "disabled");

        }
    });
    $("#fecha").blur(function () {
        if ($('#fecha').val() !== "") {
            $("#mensaje").empty();
            $("#enviar").removeAttr("disabled");
        }
    });
});