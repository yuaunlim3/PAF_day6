//Movies with rating more than 6 and status is Ended
/*db.series.find({"rating.average":{
            $gte: 6
        },
        "status": "Ended"
})
*/
//Movies with webChannel that is not null
/*
db.series.find({
    "webChannel": { $ne: null }
})

db.series.find({
    "network.country.name":"Canada"
})



//$in is acts like a or
db.series.find({
    genres:{
        $in:["Drama","Crime","Horror"]
    }
})


//$all for all
db.series.find({
    genres:{
        $all:["Drama","Crime","Horror"]
    }
})

//Can if an attribute exists
db.series.find({
    award:{$exists:false}
})

//Show all the distinct timezone in the database
db.series.distinct("network.country.timezone")

//To count the number of shows for Canada
db.series.find({
    "network.country.name":"Canada"
}).count()


//Limit to 2 
db.series.find({
    "network.country.name":"Canada"
}).limit(2)

//Skip 2
db.series.find({
    "network.country.name":"Canada"
}).skip(2)

//Order based on ratings
//-1 ==> desc 
//1 ==> asec
db.series.find({
    "network.country.name":"Canada"
}).sort({
    "rating.average":-1,
    "name":1
})



//Projections for data that we want
db.series.find({
    "network.country.name":"Canada"
}).sort({
    "rating.average":-1,
    "name":1
})
.projection({
    name:1,genres:1,
    summary:1,"image.original":1,
    _id:0
})
*/
db.series.distinct("genres", "rating.average",{
    "network.country.name": {
        $regex:"Canada",
        $options:"i" }
})
