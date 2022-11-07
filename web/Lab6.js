var Lab6 = (function () {

    return {

        updateAttendeeInfo: function () {

            $("#output").append("Failed to update attendee");
            //retrieve value for attendee id
            var id = $("#attendeemenu").val().trim();

            //add new info to data object
            var firstname = $("#firstname").val().trim();
            var lastname = $("#lastname").val().trim();
            var displayname = $("#displayname").val().trim();
            data = { "firstname": firstname, "lastname": lastname, "id": id, "displayname": displayname };

            //AJAX PUT request
            $.ajax({
                url: 'http://localhost:8180/Lab6/AttendeeServlet',
                method: 'PUT',
                data: data,
                dataType: 'json',
                success: function (response) {
                    if (response.success === true)
                        $("#output").html("Update completed successfully!");
                }
            });

        },
        getSessionInfo: function () {
            console.log("Get Session Information");
            var sessionid = $("#sessionmenu").val().trim();
            var url = 'http://localhost:8180/Lab6/SessionServlet?id=' + sessionid;
            
            // AJAX GET request
            $.ajax({
                url: url,
                method: 'GET',
                success: function (response) {
                    if (response[0].success === true) {

                        //create table from json
                        var sessionTable = "<tr><th>Attendee Id</th><th>Session Id</th><th>Firstname</th><th>Lastname</th><th>Display Name</th></tr>";
                        for (var i = 1; i < response.length; i++) {

                            sessionTable += "<tr>";

                            for (const key in response[i]) {

                                sessionTable += "<td>";
                                sessionTable += response[i][key];
                                sessionTable += "</td>";

                            }

                            sessionTable += "</tr>";
                        }
                        $("#outputtable").html(sessionTable);
                    }
                }
            });

        },

        updateRegistrationInfo: function () {
            console.log("Updating Registration");

            var attendeeid_old = $("#attendeeid").val().trim();
            var attendeeid_update = $("#attendeeid_update").val().trim();
            var sessionid_old = $("#sessionid").val().trim();
            var sessionid_update = $("#sessionid_update").val().trim();

            data = { "oldAttendeeID": attendeeid_old, "oldSessionID": sessionid_old, "updatedAttendeeID": attendeeid_update, "updatedSessionID": sessionid_update };

            $.ajax({
                url: 'http://localhost:8180/Lab6/registrations',
                method: 'PUT',
                data: data,
                dataType: 'json',
                success: function (response) {
                    console.log(JSON.stringify(response));
                }
            });
        },

        deleteRegistrationInfo: function () {
            console.log("Deleting registration");
            var attendeeid = $("#attendeeid_delete").val().trim();
            var sessionid = $("#sessionid_delete").val().trim();
            data = { "attendeeid": attendeeid, "sessionid": sessionid };

            $.ajax({
                url: 'http://localhost:8180/Lab6/registrations',
                method: 'DELETE',
                data: data,
                dataType: 'json',
                success: function (response) {
                    console.log(JSON.stringify(response));
                }
            });


        },

    };
})();