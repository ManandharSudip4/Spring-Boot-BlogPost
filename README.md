# Blogpost Project

    REST API....

## Postman Collection

### Blogpost CRUD

#### Create

    URL: http://localhost:8080/api/v1/blog
    Token: Bearer token needs to be added to Authorization header
    Body: {
                "id": 5,
                "catID": 1,
                "title": "Leo Messi Breaks Another Record",
                "content": "Messi started playing football as a boy and in 1995 joined the youth team of Newell’s Old Boys (a Rosario-based top-division football club). Messi’s phenomenal skills garnered the attention of prestigious clubs on both sides of the Atlantic. At age 13 Messi and his family relocated to Barcelona, and he began playing for FC Barcelona’s under-14 team. He scored 21 goals in 14 games for the junior team, and he quickly graduated through the higher-level teams until at age 16 he was given his informal debut with FC Barcelona in a friendly match.",
                "userId": 152,
                "thumbnailUrl": "https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg",
                "createdDate":"2023-08-27"
            }


### View Blogs 
#### All Blogs 
    URL: http://localhost:8080/api/v1/blogs
    Token: no need to add token


#### Blogs by ID
    URL: http://localhost:8080/api/v1/blogs/{id}
    Token: no need to add token

### Update
    URL: http://localhost:8080/api/v1/blog/{id}
    Token: Bearer token needs to be added to Authorization header
    Body :{ 
            id": 6,
            "catID": 10,
            "title": "Neymar: Once a Barcelona Prince",
            "content": "Neymar to AL Hilal",
            "userId": 1,
            "thumbnailUrl": "https://cdn.britannica.com/35/238335-050-2CB2EB8/Neymar-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg",
            "createdDate":"2023-08-28"
        }