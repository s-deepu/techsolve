use(function() {
    var count=properties.get("slideCount");

    var arr = [];
    if(count && count>0)
    {
        for(var i=0;i<count;i++)
        {
            arr.push("slide"+i);
        }
    }

           return {
               childs:arr
                   };
                });