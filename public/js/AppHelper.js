
$(function() {
    $("#publicationDate").datepicker({dateFormat: "dd.mm.yy"});
    $('#publicationDate').click(function () {
        $('#publicationDate').datepicker("show");
    });
});