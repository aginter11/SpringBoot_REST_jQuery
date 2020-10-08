function reloadTable() {
    fetch('http://localhost:8080/api/user/').then(
        response => {
            response.json().then(
                data => {
                    let temp = "";
                    data.forEach((u) => {
                        temp += "<tr >";
                        temp += "<td >" + u.id + "</td>";
                        temp += "<td >" + u.lastname + "</td>";
                        temp += "<td >" + u.name + "</td>";
                        temp += "<td >" + u.email + "</td>";
                        temp += "<td >" + JSON.stringify(u.roles[0].role.toString()) + "</td>"
                        temp += "<td >" +
                            "    <a class='btn btn-info' role='button' onmouseover='fillEditModal(" + u.id + ")' data-toggle='modal' data-target='#editUser'>Edit</a>" +
                            "    <a class='btn btn-danger' role='button' onmouseover='fillDeleteModal(" + u.id + ")' data-toggle='modal' data-target='#deleteUser'>Delete</a>" +
                            "    </td>"
                        temp += "</tr>";
                    })
                    // document.getElementById("list").innerHTML = temp;
                    $("#usersTableHere").empty();
                    $("#usersTableHere").append(temp);
                }
            )
        }
    )
}

function fillEditModal(userId) {
    $.get("http://localhost:8080/api/user/" + userId, function (userJSON) {
        $('#idEdit').val(userJSON.id);
        $('#lastnameEdit').val(userJSON.lastname);
        $('#nameEdit').val(userJSON.name);
        $('#emailEdit').val(userJSON.email);
        //   $('#passwordEdit').val(userJSON.password);
        if (userJSON.roles.length == 2) {
            $('#rolesEdit option').prop('selected', true)
        } else if (userJSON.roles.length == 1) {
            $('#rolesEdit option:last').prop('selected', true)
        } else {
            $('#rolesEdit option').prop('selected', false)
        }
    });
}

function fillDeleteModal(userId) {
    $.get("http://localhost:8080/api/user/" + userId, function (userJSON) {
        $('#idDel').val(userJSON.id);
        $('#lastnameDel').val(userJSON.lastname);
        $('#nameDel').val(userJSON.name);
        $('#emailDel').val(userJSON.email);
        //   $('#passwordEdit').val(userJSON.password);
        if (userJSON.roles.length == 2) {
            $('#rolesDel option').prop('selected', true)
        } else if (userJSON.roles.length == 1) {
            $('#rolesEdit option:last').prop('selected', true)
        } else {
            $('#rolesEdit option').prop('selected', false)
        }
    });
}

function reloadNewUserTable() {
    $('#newLastname').val('');
    $('#newName').val('');
    $('#newEmail').val('');
    $('#newPassword').val('');
    $('#newRoles').val('');
}


$(function () {
    // $("#logout").append("<a class='custom-a' href='/logout'>Logout</a>");
    $('#addSubmit').on("click", function () {
        let checked = [];
        $('#newRoles option:selected').each(function () {
            checked.push($(this).val());
        });
        // reloadNewUserTable();

        let user = {
            lastname: $("#newLastname").val(),
            name: $("#newName").val(),
            email: $("#newEmail").val(),
            password: $("#newPassword").val(),
            roles: checked

        };
        fetch('http://localhost:8080/api/user/', {
            method: "POST",
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

        reloadNewUserTable();
        setTimeout(reloadTable, 1000)

    });


    $('#modalDeleteBtn').on("click", function () {
        fetch('http://localhost:8080/api/user/' + $('#idDel').val(), {
            method: "DELETE",
            credentials: 'same-origin',
        });

        setTimeout(reloadTable, 500)
        $('#deleteUser').modal('hide');
    });


    $('#modalEditBtn').on("click", function () {
        let checked = [];
        $('#rolesEdit option:selected').each(function () {
            checked.push($(this).val());
        });


        let user = {
            id: $('#idEdit').val(),
            lastname: $("#lastnameEdit").val(),
            name: $("#nameEdit").val(),
            email: $("#emailEdit").val(),
            roles: checked
        };
        fetch('http://localhost:8080/api/user/', {
            method: "PUT",
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

        reloadNewUserTable();
        setTimeout(reloadTable, 500)
        $('#editUser').modal('hide');
    });
});
reloadTable();