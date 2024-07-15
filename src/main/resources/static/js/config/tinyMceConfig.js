/* default setting */
function tinyEditorInit(elementId, height) {
    //텍스트 에디터
    tinymce.init({
        selector: 'textarea#'+elementId,
        height: height ?? 500,
        plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table paste imagetools wordcount'
        ],
        toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
        // images_upload_handler: editorImageUploadHandler,
        language: 'ko_KR',
        convert_urls: false,
    });
}