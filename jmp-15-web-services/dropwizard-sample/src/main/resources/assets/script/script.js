function getUsers() {
    $.get({
        url: '/users',
        success: function (data) {
            var template = Handlebars.compile($('#users').html());
            $('.users').html(template(data));
        }
    })
}

function addUser() {
    $.post({
        url: '/users',
        data: JSON.stringify({
            firstName: $('.firstname').val(),
            lastName: $('.lastname').val()
        }),
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            //var template = Handlebars.compile($('#user').html());
            //$('#user-table tr:last').after(template(data));
        }
    })
}
