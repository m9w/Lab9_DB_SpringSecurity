(function ($) {
    "use strict";
    var limitShowPagination = 9;
    var currentTable, currentPage, maxPages, columnRegEx;

    function buildPagination(currentPage, maxPage, limitShowPage) {
        var container = $('<div class="pagination"><ul></ul></div>')
        var list = container.find(":first-child");
        if(currentPage === 1)
             list.append("<li class=\"pagination-start\"><span class=\"pagenav\"></span></li>" +
                         "<li class=\"pagination-prev\"><span class=\"pagenav\"></span></li>");
        else list.append("<li class=\"pagination-start\" onclick='document.p_control(1)'><a class=\"pagenav\"></a></li>" +
                         "<li class=\"pagination-prev\" onclick='document.p_control("+(currentPage-1)+")'><a class=\"pagenav\"></a></li>");
        var countPage = currentPage-~~(limitShowPage/2);
        if(currentPage + limitShowPage/2>maxPage)countPage = maxPage - limitShowPage + 1;
        if(countPage<1) countPage = 1;
        for(var i = 1;i<=limitShowPage&&countPage<=maxPage ;countPage++,i++)
            if(countPage === currentPage)list.append("<li><span class=\"pagenav\">"+countPage+"</span></li>");
            else list.append("<li onclick='document.p_control("+countPage+")'><a class=\"pagenav\">"+countPage+"</a></li>");
        if(currentPage === maxPage)
             list.append("<li class=\"pagination-next\"><span class=\"hasTooltip pagenav\"></span></li>" +
                         "<li class=\"pagination-end\"><span class=\"hasTooltip pagenav\"></span></li>");
        else list.append("<li class=\"pagination-next\" onclick='document.p_control("+(currentPage+1)+")'><a class=\"hasTooltip pagenav\"></a></li>" +
                         "<li class=\"pagination-end\" onclick='document.p_control("+maxPage+")'><a class=\"hasTooltip pagenav\"></a></li>");
        $("#pagination").html(container).append("<button class='BlueBut plus' onclick=\"$('body').toggleClass('show')\">+</button>");
    }

    function addHeadMessage(text, status) {
        $("#messageLine").append("<div tag=\""+(status?"G":"R")+"\" onclick=\"$(this).remove()\">"+text+"</div>");
    }

    function getParams() {
        var vars = $(location).attr('search').substr(1).split('&');
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split('=');
            if(pair.length === 2 && pair[0] === "pagesize")
                return "pageSize="+(+pair[1])+"&page="+(currentPage-1);
        }
        return "page="+(currentPage-1)
    }

    function buildModalAddRecords(){
        var list = $("#modal");list.empty();
        for (var [key, value] of Object.entries(columnRegEx)) {
            list.append("<label>"+key+"<input "+((new RegExp(value).test(""))?"placeholder=\"NULL\" ":"required ")+
            (value==="^\\d{4}(-\\d\\d){2}$"?"type=\"date\" ":"")+ "name=\""+key+"\" pattern=\""+value+"\"/></label>");
        }
        list.append("<input type=\"hidden\" name=\"_csrf\" value=\""+csrf+"\"/>");
        list.append("<button class='BlueBut'>Add record to "+currentTable+"</button>");
    }

    function query(page){
        currentPage = page === undefined? 1 : page;
        $.get("/q/"+currentTable, getParams(),function (text){
            var data = JSON.parse(text.split('\\').join("\\\\"));
            var pages = data.shift();
            currentPage = pages.current;
            maxPages = pages.max;
            columnRegEx = data.shift();

            var columns_d = [];
            for (var [key] of Object.entries(columnRegEx)) {
                var type = "number";
                for (const el of data)
                    if(el[key]!=null) {
                        type = typeof el[key];
                        break;
                    }
                columns_d.push({index: key, title: key, type: type});
            }


            console.log(columns_d);
            $("#container").empty();
            new FancyGrid({
                title: currentTable,
                renderTo: 'container',
                height: 'fit',
                data: data,
                defaults: {
                    flex: 1,
                    sortable: true
                },
                columns: columns_d
            });
            buildPagination(currentPage, maxPages, limitShowPagination);
            buildModalAddRecords();
        });
    }

    document.p_control = query;

    document.send_modal = function(){
        $.ajax({
            type: 'POST',
            url: '/i/'+currentTable,
            data: $('#modal').serialize(),
            success: function (data) {
                if (data.status === "S"){
                    $('body').toggleClass('show');
                    addHeadMessage("Success", true);
                }else addHeadMessage(data.message, false);
            },
            dataType: "json"
        });
    };

    $("#emp, #dept").click(function (){
        currentTable = this.id;
        query();
    });

})(jQuery);