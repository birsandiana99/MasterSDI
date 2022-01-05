function goAdd() {
    $.get("ServletDispatcher",
        {
            action: "add"
        },
        function (data) {
            console.log("sdkjfhsdofdsfsd");
            window.location.href = "add.jsp";
        }
    );
}