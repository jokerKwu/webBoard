var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click',function(){
           _this.update_t();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var formData = new FormData();
        var inputFile = $("input[name='uploadFile']");
        var files = inputFile[0].files;

        for (var i=0;i<files.length;i++){
            formData.append("uploadFile", files[i]);
        }
        formData.append('title', $('#title').val());
        formData.append('author',$('#author').val());
        formData.append('content',$('#content').val());

        $.ajax({
            type: 'POST',
            url: '/api/v1/fileUpload',
            processData: false,
            contentType: false,
            data: formData,
            success: function(){

            }
 //           dataType: 'json',
 //           contentType:'application/json; charset=utf-8',
 //           data: JSON.stringify(data)
 //           data: formData,
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {

        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update_t : function(){
        var formData = new FormData();
        var inputFile = $("input[name='uploadFile']");
        var files = inputFile[0].files;
        for (var i = 0; i < files.length; i++){
            formData.append("uploadFile",files[i]);
        }
        formData.append('title', $('#title').val());
        formData.append('author',$('#author').val());
        formData.append('content',$('#content').val());
        var id = $('#id').val();
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/'+id,
            processData: false,
            contentType: false,
            data: formData,
            success: function(){
            }
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();

function setThumbnail(event){
    for (var image of event.target.files) {
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = document.createElement("img");
            img.setAttribute("src",event.target.result);
            img.setAttribute("class","img-thumbnail");
            img.width = 300;
            img.height = 300;

            document.querySelector("div#image_container").appendChild(img);
        };
        console.log(image);
        reader.readAsDataURL(image);
    }
}

/*
21-01-19 파일업로드
 */
$(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});