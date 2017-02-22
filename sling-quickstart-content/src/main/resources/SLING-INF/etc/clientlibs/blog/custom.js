(function () {


    $(document).ready(function () {


        $(".form-signin").submit(function (e) {
            // Prevent form submission
            e.preventDefault();
            var $form = $(e.target);

            $.ajax({
                url: $form.attr('action'),
                type: 'POST',
                data: $form.serialize(),
                success: function () {
                    window.location.reload();
                }
            });
            return false;
        });

        $(".sling-logout").click(function () {
            var url = $(this).attr("href");
            $.ajax(url);
            setTimeout(function () {
                document.location.reload(true);
            }, 100);
            return false;
        });
    });


}());