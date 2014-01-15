
$(function() {
    $("#publicationDate").datepicker({dateFormat: "dd.mm.yy"});
    $('#publicationDate').click(function () {
        $('#publicationDate').datepicker("show");
    });
});

// Bootbox Buttonsprache auf deutsch setzten
bootbox.setDefaults({locale: "de"});

function fiveheadsNotify(notificationType, notificationStrongText, notificationText){
    $('#notificationBar').children().hide().remove();
    $('#notificationBar').append('<div class="container"></div> <div class="alert alert-dismissable alert-'+ notificationType +'">' +
                                            '<strong>' + notificationStrongText + ' </strong>' + notificationText +
                                            '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' +
                                            '</div></div>');
}