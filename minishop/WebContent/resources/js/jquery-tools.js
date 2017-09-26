(function ($)
{
    $.fn.isNum = function (data)
    {
        var chrTmp, strTmp;
        var len, str;
        if (data == undefined)
        {
            str = $(this).val();
        }
        else
        {
            str = data;
        }
        len = str.length;
        if (str.length == 0) return false;
        strTmp = '';
        for (var i = 0; i < len; ++i)
        {
            chrTmp = str.charCodeAt(i);
            if ((chrTmp <= 47 && chrTmp > 31) || chrTmp >= 58)
            {
                return false;
            }
        }
        return true;
    }
    $.fn.hasNum = function (data)
    {
        var chrTmp, strTmp;
        var len, str;
        if (data == undefined)
        {
            str = $(this).val();
        }
        else
        {
            str = data;
        }
        len = str.length;
        if (str.length == 0) return false;
        strTmp = '';
        for (var i = 0; i < len; ++i)
        {
            chrTmp = str.charCodeAt(i);
            if ((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58)
            {
                return true;
            }
        }
        return false;
    }

    $.fn.toInt = function (data)
    {
        var chrTmp, strTmp, string, len;
        var len, str;
        if (data == undefined)
        {
            str = $(this).val();
        }
        else
        {
            str = data;
        }
        len = str.length;
        if (str.length == 0) return false;
        strTmp = '';
        for (var i = 0; i < len; ++i)
        {
            chrTmp = str.charCodeAt(i);
            if ((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58)
            {
                strTmp = strTmp + String.fromCharCode(chrTmp);
            }
        }
        if (strTmp == '') return 0;
        return parseInt(strTmp);
    }
    $.toInt = function (string)
    {
        var chrTmp, strTmp;
        var len;
        len = string.length;
        strTmp = '';
        for (var i = 0; i < len; ++i)
        {
            chrTmp = string.charCodeAt(i);
            if ((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58)
            {
                strTmp = strTmp + String.fromCharCode(chrTmp);
            }
        }
        if (strTmp == '') return 0;
        return parseInt(strTmp);
    }
    $.queryStr = function (name)
    { name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]"); var regexS = "[\\?&]" + name + "=([^&#]*)"; var regex = new RegExp(regexS); var results = regex.exec(window.location.href); if (results == null) return ""; else return decodeURIComponent(results[1].replace(/\+/g, " ")); }
    $.queryStrToJson = function ()
    {
        href = window.location.href;
        if (href.indexOf("?") < 0) return {};
        qStr = href.replace(/(.*?\?)/, '');
        qArr = qStr.split('&');
        stack = {};
        for (var i in qArr)
        {
            var a = qArr[i].split('=');
            var name = a[0], value = isNaN(a[1]) ? decodeURIComponent(a[1].replace(/\+/g, " ")) : parseFloat(a[1]);
            if (name.match(/(.*?)\[(.*?)]/))
            {
                name = RegExp.$1; name2 = RegExp.$2;
                if (name2)
                {
                    if (!(name in stack))
                    {
                        stack[name] = {};
                    }
                    stack[name][name2] = value;
                }
                else
                {
                    if (!(name in stack))
                    {
                        stack[name] = [];
                    }
                    stack[name].push(value);
                }
            }
            else
            {
                stack[name] = value;
            }
        }
        return stack;
    }
})(jQuery);

String.prototype.format = function ()
{
    var pattern = /\{\d+\}/g;
    var args = arguments;
    return this.replace(pattern, function (capture) { return args[capture.match(/\d+/)]; });
}

String.prototype.isEmpty = function ()
{
    if (this == null)
        return true;
    if ($.trim(this) == "")
        return true;
    return false;
}

$.fn.center = function ()
{
    this.css("position", "absolute");
    this.css("top", ($(window).height() - this.height()) / 2 + $(window).scrollTop() + "px");
    this.css("left", ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + "px");
    return this;
}