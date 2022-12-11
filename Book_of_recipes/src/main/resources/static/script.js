let firstARadio = $('#firstARadio');
let firstBRadio = $('#firstBRadio');
let recipeRequest = $('#recipeRequest');

$.ajax({
    url: "/",
    type: "GET",
    contentType: "html"
});

$('#selectDish').change(function () {
    let isLink = document.querySelectorAll('input[name = "isLink"]')
    for (let link of isLink)
        if (link.checked)
            recipeRequest.attr("action", link.value);
});

//замена экшена у радио кнопки
firstARadio.change(function () {
    if (!firstARadio.is("checked", true))
        recipeRequest.attr("action", firstARadio.val())
});
firstBRadio.change(function () {
    if(!firstBRadio.is("checked", true))
        recipeRequest.attr("action", firstBRadio.val())
});

//автоматический выбор при старте странице
firstARadio.prop('checked', true);
recipeRequest.attr("action", "/firstA");