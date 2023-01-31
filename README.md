# Sakuta-lib

### This project created for learning Java and Spring Boot. In this project you can read mangas, check authors of manga. 
### Admins can add manga, volumes and chapters, with manga pages. You can write comments for chosen manga pages. 
### You can Register and Login in account for adding your favourite mangas.


### Use mysql for database system

# Routes

# Account Routes

## Login Route

`POST /login`

### This route will send you true or false, true if you logged and false if not. Body for this request:

```
{
    "login": "STRING",
    "password": "STRING"
}
```

#### This field means exactly what they mean normally. Login for login, and Password for password

## Register Route

`POST /register`

### This route will register new account with data from body. Body for this request:

```
{
    "login": "STRING",
    "password": "STRING",
    "firstName": "STRING",
    "secondName": "STRING",
    "nickName": "STRING"
}
```

#### Login and password fields means exactly same what they mean in previous request. 
#### Field "firstName" is your first name what will be in your profile.
#### Field "secondName" is your second name what will be in your profile.
#### Field "nickName" is your nick name what will be in your profile. By nickname you can find another users

## Add Favourite Manga To Account Route

`POST /favouriteManga`

### This route will add or delete (if manga what you want to add to favourite is already exist there) manga to your list of favourite mangas.
### Body for this request:

```
{
    "mangaId": NUMBER,
    "userLogin": "STRING"
}
```

#### Field "mangaId" contains id of manga what you want to add or delete to your favourite mangas list.
### Field "userLogin" contains login of user who want to add this manga to them account

## Get Favourite Manga Route

`GET /favouriteManga?userLogin=STRING`

### This route will return list of account favourite manga, example response from this route: 

```
[
    {
        "mangaId": 4,
        "mangaName": "Bleach",
        "mangaPathName": "bleach",
        "mangaDescription": "",
        "author": {
            "authorId": 1,
            "authorFirstName": "yehor",
            "authorSecondName": "kochetov"
        },
        "mangaGenres": [
            {
                "genreId": 2,
                "genre": "School"
            },
            {
                "genreId": 1,
                "genre": "Horror"
            }
        ]
    }
]
```

#### You have query parameter "userLogin", this parameter contains login of account which from you want to get list of favourite mangas

# Mangas Routes

## Add Manga Route

`POST /manga`

### This route will add new manga to database with parameters which you send in body. Body for this request:

```
{
    "mangaName": "STRING",
    "mangaPathName": "STRING",
    "mangaDescription": "STRING",
    "authorId": NUMBER
}
```

#### Field "mangaName" contains manga name which will display on website.
#### Field "mangaPathName" contains manga name which will using for another routes and for getting pages from this manga.
#### Field "mangaDescription" contains manga description which will display on website.
#### Field "authorId" contains id of author who create this manga.

## Get Manga By Path Name Route

`GET /manga?name=STRING`

### This route will return you manga by her path name, example response:

```
{
    "mangaId": 4,
    "mangaName": "Bleach",
    "mangaPathName": "bleach",
    "mangaDescription": "",
    "author": {
        "authorId": 1,
        "authorFirstName": "yehor",
        "authorSecondName": "yehor"
    },
    "mangaGenres": [
        {
            "genreId": 2,
            "genre": "School"
        },
        {
            "genreId": 1,
            "genre": "Horror"
        }
    ]
}
```

#### You have query parameter "name", this parameter contains path name of manga which you want to get

## Get Manga By Genres Route

`GET /mangas/genres?genresId=NUMBER,NUMBER`

### This route will return manga with exactly this genres, example response:

```
[
    {
        "mangaId": 4,
        "mangaName": "Bleach",
        "mangaPathName": "bleach",
        "mangaDescription": "",
        "author": {
            "authorId": 1,
            "authorFirstName": "yehor",
            "authorSecondName": "kochetov"
        },
        "mangaGenres": [
            {
                "genreId": 1,
                "genre": "Horror"
            },
            {
                "genreId": 2,
                "genre": "School"
            }
        ]
    }
]
```

#### You have query parameter "genresId", this parameter contains array of id's genres what must be in manga which you want to get.

## Get All Mangas Route

`GET /mangas`

### This route will return , example of response you can see in previous request example.

# Volumes Routes

## Add New Volume Route

`POST /volume`

### This route add new volume to chosen manga. Body for this request:
```
{
    "mangaId": NUMBER,
    "volumeNumber": NUMBER
}
```

#### Field "mangaId" contains id of manga to which you want to add volume.
#### Field "volumeNumber" contains number of new volume.

## Get All Manga Volumes Route

`GET /volumes?mangaId=NUMBER`

### This route will return all of volumes from chosen manga, example response:

```
[
    {
        "volumeId": 3,
        "volumeNumber": 1,
        "volumeChaptersAmount": 1
    },
    {
        "volumeId": 4,
        "volumeNumber": 2,
        "volumeChaptersAmount": 0
    }
]
```

#### You have query parameter "mangaId", this parameter contains id of manga from which you want to get all volumes.

# Chapters Routes

## Add New Chapter Route

`POST /chapter`

### This route will add new chapter to chosen volume which id you write in body. Body for this request:

```
{
    "chapterName": "STRING",
    "chapterNumber": NUMBER,
    "chapterAuthorId": NUMBER,
    "chapterVolumeId": NUMBER
}
```

#### Field "chapterName", contains a chapter name which will display on website.
#### Field "chapterNumber", contains a chapter number in the manga.
#### Field "chapterAuthorId", contains a id of main author who create with chapter.
#### Field "chapterVolumeId", contains a id of which volume is chapter in.

## Get All Manga Chapters Route

`GET /chapters?mangaId=NUMBER`

### This route will return all chapters of chosen manga, example response:

```
[
    {
        "chapterId": 3,
        "chapterName": "Death & Strawberry",
        "chapterNumber": 1,
        "chapterAddedDate": "25-01-2023",
        "chapterVolume": 1,
        "chapterPagesAmount": 6,
        "chapterAuthor": {
            "authorId": 1,
            "authorFirstName": "yehor",
            "authorSecondName": "kochetov"
        }
    }
]
```
#### You have query parameter "mangaId", this parameter contains id of manga from which you want to get all chapters.

# Manga Author Routes

## Add Manga Author Route

`POST /author`

### This route will add new manga author with parameters from body. Body for this request:

```
{
    "authorFirstName": "STRING",
    "authorSecondName": "STRING",
    "authorBirthday": "STRING"
}
```

#### Field "authorFirstName", contains author first name which will display on website.
#### Field "authorSecondName", contains author second name which will display on website. 
#### Field "authorBirthday", contains author birth day which will display on website. FORMAT ("dd-MM-yyyy").

## Get Manga Author By Id Route

`GET /author/NUMBER`

### This route return you manga author by id which you send in query, example response:

```
{
    "authorId": 4,
    "authorFirstName": "yehor",
    "authorSecondName": "krupski"
}
```

#### You have anonymous query parameter "authorId", this parameter contains id of author which you want to get. 

## Get Manga Author By His Name Route

`GET /author?name=STRING`

### This route return you manga authors with name from query,  example response:

```
[
    {
        "authorId": 1,
        "authorFirstName": "yehor",
        "authorSecondName": "kochetov"
    },
    {
        "authorId": 4,
        "authorFirstName": "yehor",
        "authorSecondName": "krupski"
    }
]
```

#### You have query parameter "name", this parameter contains name which must to be in author in response.

# Manga Pages Routes

## Upload Manga Page Route

`POST /uploadPage`

### This route upload new page of manga to server, must to say that in database saving only path to that image.
### Image saving in server folder in special chapter volume and manga folder.

<br>

#### In body, you send file (this is manga page actually). 
#### Volume id (it using for build correct path to folder image must be in).
#### Chapter id (it using for update info in chapter and path too).
#### Manga id (it using for build correct path to folder image must be in).
#### Page number (it using for path and just for website information).

<br>

### Actual field names:

### file, volumeId, chapterId, mangaId, pageNumber

## Get Manga Page Route

`GET /read/STRING/NUMBER/NUMBER/STRING`

### This route will return you image of manga.

#### You have anonymous query parameter "mangaName", this parameter contains manga path name.
#### You have anonymous query parameter "volumeNumber", this parameter contains volume number from which you will get page.
#### You have anonymous query parameter "chapterNumber", this parameter contains chapter number from which you will get page.
#### You have anonymous query parameter "page", this parameter contains page number what you will get.

<br>

#### Example request: "http://localhost:3434/read/horimiya/1/1/4.jpeg"
#### This request will send image from manga horimiya, from first volume and chapter and 4-th page.

## Get Manga Page Comments Route

`GET /comments?path=horimiya/1/1/1.jpeg`

### This route return list of comment for chosen manga page which path is send in query, example response:

```
[
    {
        "commentId": 1,
        "commentContent": "this main page is amazing!",
        "commentAuthorLogin": "yehor"
    }
]
```

#### You have query parameter "path", this parameter contains path to manga page.

# Manga Genres Routes

## Add Genre Route

`POST /genre`

### This route will add new genre with data from body. Body for this request:

```
{
    "genre": "STRING"
}
```

#### Field "genre", contains genre name which will display on website.

## Get All Genres Routes

`GET /genres`

### This route will return list of all genres, example response: 

```
[
    {
        "genreId": 3,
        "genre": "Bloody"
    },
    {
        "genreId": 1,
        "genre": "Horror"
    },
    {
        "genreId": 2,
        "genre": "School"
    }
]
```

# Best wishes from Yehor Kochetov :)