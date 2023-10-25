
$(".btn-clear").on("click", function () {
    if (confirm('Are you sure you want to clear data table?')) {
        window.location.replace("/cleartable");
    }
})