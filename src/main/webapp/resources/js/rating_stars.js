/**
 * Created by Piotr on 2015-05-31.
 */
$(document).ready(function () {
    $(".rating-star").click(function () {
        $(this).toggleClass("glyphicon-star");
        $(this).toggleClass("glyphicon-star-empty");
        var fullStars = $(this).parent().find('.glyphicon-star');
        var rate = fullStars.size();
        $(this).parent().find('input:hidden').val(rate);
    });
});