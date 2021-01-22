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

        $('#btn-comments-save').on('click',function(){
           _this.comments_save();
        });

        _this.commentList();
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
        formData.append('content',$('#commentsContent').val());
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
    },
    comments_save : function(){
        var formData = new FormData();
        formData.append('author', $('#author').val());
        formData.append('postId', $('#id').val());
        formData.append('commentsContent', $('#commentsContent').val());
        alert($('#id').val());
        alert(formData.get('postId'));

        $.ajax({
            type: 'POST',
            url: '/api/v1/comments',
            processData: false,
            contentType: false,
            data: formData,
        }).done(function(){
            alert('댓글이 등록되었다.');
            commentList(formData.get('postId'));
        }).fail(function (error){
            alert(JSON.stringify(error))
        });
    },
    commentsDelete : function(commentsId){
        var id = commentsId;

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/comments/{id}',
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/'; //리다이렉트로 수정해야됨
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    commentsUpdateProc : function(commentsId){
        var data = {
            'commentsId': commentsId,
            'content': $('#content').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/v1/comments/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('댓글이 수정되었습니다.');
            if(data == 1) commentList(postId);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentsUpdate : function(commentsId, commentsContent){
        var a ='';

        a += '<div class="input-group">';
        a += '<input type="text" class="form-control" name="content_'+commentsId+'" value="'+commentsContent+'"/>';
        a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentsUpdateProc('+commentsId+');">수정</button> </span>';
        a += '</div>';

        $('.commentsContent'+commentsId).html(a);
    },

    commentList : function(){
        var postId = $('#id').val();
        $.ajax({
            url : '/api/v1/comments/list',
            type : 'get',
            data : {'postId':postId},
            success : function(data){
                var a ='';
                $.each(data, function(key, value){
                    alert(value);
                    a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                    a += '<div class="commentInfo'+value.id+'">'+'댓글번호 : '+value.id+' / 작성자 : '+value.author;
                    a += '<a onclick="commentsUpdate('+value.id+',\''+value.content+'\');"> 수정 </a>';
                    a += '<a onclick="commentsDelete('+value.id+');"> 삭제 </a> </div>';
                    a += '<div class="commentsContent'+value.id+'"> <p> 내용 : '+value.content +'</p>';
                    a += '</div></div>';
                });

                $(".commentsList").html(a);
            }
        });
    },



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

/*
21-01-22 댓글 구현 ~
 */


