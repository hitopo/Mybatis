$(document).ready(function () {
    $("#submit-btn:eq(0)").click(function () {
        if (!confirm("确定要修改吗？")) {
            $(this).preventDefault();
            return;
        } else {
            $("#mainForm").submit();
        }
    });

    var MaxInputs = 5;//maximum input boxes allowed
    var x = $("Textarea").length; //initlal text box count

    $("#AddMoreFileBox").click(function ()  //on add input button click
    {
        if (x < MaxInputs) //max input box allowed
        {
            //add input box
            $("#InputsWrapper").append('<div><textarea name="content"></Textarea><a href="#" class="removeclass">×</a></div>');
            x++; //text box increment
        }
        return false;
    });

    $("body").on("click", ".removeclass", function () { //user click on remove text
        if (x > 1) {
            $(this).parent('div').remove(); //remove text box
            x--; //decrement textbox
        }
        return false;
    })
});