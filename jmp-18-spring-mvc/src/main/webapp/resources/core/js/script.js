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
            firstName: $('#firstname').val(),
            lastName: $('#lastname').val()
        }),
        contentType: "application/json",
        success: function (data) {
            var template = Handlebars.compile($('#user').html());
            $('#user-table tr:last').after(template(data));
        }
    })
}

function updateUser() {
    $.ajax({
        url: 'users/'.concat($('#id').val()),
        method: 'PUT',
        data: JSON.stringify({
            id: $('#id').val(),
            firstName: $('#firstname_edit').val(),
            lastName: $('#lastname_edit').val()
        }),
        contentType: "application/json",
        success: function (data) {
            var template = Handlebars.compile($('#user').html());
            $('.user_'.concat(data.id)).replaceWith(template(data));
            $('.error').html('')
        },
        error: function (err) {
            $('.error').html(err.responseText)
        }
    })
}

function deleteUser(id) {
    $.ajax({
        url: 'users/'.concat(id),
        method: 'DELETE',
        success: function () {
            $('.user_'.concat(id)).html('');
        }
    })
}