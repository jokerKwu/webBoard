$(document).ready(function() {
    //여기 아래 부분
    $('#summernote').summernote({
        height: 450,                 // 에디터 높이
        minHeight: 300,             // 최소 높이
        maxHeight: 800,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '자유롭게 작성해주세요.'	//placeholder 설정
    });
});