/* default setting */
function tinyEditorInit(elementId, height) {
    //텍스트 에디터
    tinymce.init({
        selector: 'textarea#'+elementId,
        // height: height || 500, // autoresize 사용 안할 시 필요
        width: "100%",
        plugins: [
            'anchor',
            'autoresize',
            'charmap',
            'code',
            'fullscreen',
            'image imagetools', // imagetools: 이미지 편집 기능 (6.0 이상 버전에서는 프리미엄 기능이라 사용불가)
            'lists advlist',
            'link autolink',
            'preview',
            'print',
            'visualblocks',
            'table',
            'paste',
            'wordcount',
            'quickbars',
        ],
        toolbar: ['undo redo | bullist numlist outdent indent | link image table | wordcount fullscreen preview print',
            'styleselect | bold italic underline strikethrough | forecolor backcolor charmap | alignleft aligncenter alignright alignjustify'
        ],
        quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote | removeformat',
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
        min_height: height, // autoresize 사용시에만 필요
        // images_upload_handler: editorImageUploadHandler,
        language: 'ko_KR',
        paste_data_images: true, // 이미지 드래그 앤 드롭 기능 on/off
        menubar: false, // 메뉴바 표시 안함
        convert_urls: false, // 절대 경로 자동 변경 사용 안함
        link_default_protocol: 'http',
        link_default_target: '_blank', // 새 탭에서 링크 실행
        file_picker_types: 'image media',
        file_picker_callback: function(cb, value, meta) {
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            if (meta.filetype === 'image') {
                input.setAttribute('accept', 'image/*');
            } else if (meta.filetype === 'media') {
                input.setAttribute('accept', 'video/*');
            }

            input.onchange = function() {
                var file = input.files[0];
                var reader = new FileReader();
                reader.onload = function(e) {
                    cb(e.target.result, { title: file.name });
                };
                reader.readAsDataURL(file);
            };

            input.click();
        },
    });
}

// 에디터 이미지 업로드 핸들
// let testResponse;
//
// function editorImageUploadHandler(blobInfo, success, failure, progress) {
//     var xhr, formData;
//     xhr = new XMLHttpRequest();
//     xhr.withCredentials = false;
//     xhr.open('POST', `/${siteId}/common/file/editor`);
//
//     xhr.upload.onprogress = function(e) {
//         progress(e.loaded / e.total * 100);
//     };
//
//     xhr.onload = function() {
//         var json;
//
//         if (xhr.status === 403) {
//             failure('HTTP Error: ' + xhr.status, { remove: true });
//             return;
//         }
//
//         if (xhr.status < 200 || xhr.status >= 300) {
//             failure('HTTP Error: ' + xhr.status);
//             return;
//         }
//         testResponse = xhr;
//
//         json = JSON.parse(xhr.responseText);
//
//         if (!json || typeof json.location != 'string') {
//             failure('Invalid JSON: ' + xhr.responseText);
//             return;
//         }
//
//
//         success(json.location);
//     };
//
//     xhr.onerror = function() {
//         failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
//     };
//
//     formData = new FormData();
//
//     formData.append('file', blobInfo.blob(), blobInfo.filename());
//
//     xhr.send(formData);
//
// }