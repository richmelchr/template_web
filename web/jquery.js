$(document).ready(function () {
    $("#writerBtn").click(function () {
        alert("hello");
        $.ajax({
            url: 'Servlet',
            data: {
                button: "writerBtn",
                writerName: $('#writerName').val()
            },
            success: function (responseText) {
                $('#result' + ' p').text(responseText);
            }
        });
    });

    $('#songBtn').click(function () {
        $.ajax({
            url: 'Servlet',
            data: {
                button: "songBtn",
                // songID: $('#songID').val(),
                songName: $('#songName').val(),
                yearWritten: $('#yearWritten').val(),
                length: $('#length').val(),
                genre: $('#genre').val(),
                writerID: $('#writerID').val(),
                artistID: $('#artistID').val(),
                albumID: $('#albumID').val()
            },
            success: function (responseText) {
                $('#result' + ' p').text(responseText);
            }
        });
    });

    $('#albumBtn').click(function () {
        $.ajax({
            url: 'Servlet',
            data: {
                button: "albumBtn",
                // albumID: $('#albumID').val(),
                albumName: $('#albumName').val(),
                yearRel: $('#yearRel').val()
            },
            success: function (responseText) {
                $('#result' + ' p').text(responseText);
            }
        });
    });

    $('#artistBtn').click(function () {
        $.ajax({
            url: 'Servlet',
            data: {
                button: "artistBtn",
                // artistID: $('#artistID').val(),
                artistName: $('#artistName').val()
            },
            success: function (responseText) {
                $('#result' + ' p').text(responseText);
            }
        });
    });

    $('#lookupBtn').click(function () {
        $.ajax({
            url: 'Servlet',
            data: {
                button: "lookupBtn",
                selectTable: $('#selectTable').val(),
                fromTable: $('#fromTable').val(),
                whereTable: $('#whereTable').val()
            },
            success: function (responseText) {
                $('#result' + ' p').text(responseText);
            }
        });
    });

    $('#radioBtn').click(function () {
       $.ajax({
          url: 'Servlet',
           data: {
              button: "radioBtn",
              radioSelect: $('#radioSelect' + ' ul' + ' input:checked').val()
           },
           success: function (responseText) {
               $('#result' + ' table' + ' tbody').html(responseText);
           }
       });
    });

/*

    $("#decryptSelect").click(function () {
        var radioSelect = $('#decryptSelect' + ' input:checked').val();
        $(".selections").css("display", "none");    //hide all decrypt option DOM elements
        if (radioSelect == "shift") {
            $(".shift").css("display", "block");    //show shift decrypt options
        } else if (radioSelect == "affine") {
            $(".affine").css("display", "block");   //show affine decrypt options
        } else if (radioSelect == "rsa") {
            $(".rsa").css("display", "block");      //show RSA decrypt options
        }
    });
*/

});

