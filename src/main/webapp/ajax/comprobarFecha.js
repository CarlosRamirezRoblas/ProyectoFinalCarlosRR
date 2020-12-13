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

    $('#cita').attr("min", actual);
    
    $('#cita').val(actual);
  

    });
    $("#cita").blur(function () {
         $("#mensaje").empty();
        const picker = document.getElementById('cita');
            var day = new Date(this.value).getUTCDay();
            if ([6, 0].includes(day)) {
                $("#mensaje").append("Fines de semana no disponibles.");
                this.value = '';
            }
        
    });
   


