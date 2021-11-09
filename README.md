# Movie Project
---
## Part One - Display and Search Movie.
---
Create an application that displays information about movies getting from [The Movie DB](https://developers.themoviedb.org/3/getting-started/introduction).The movie information is `Title`, `poster image`, `vote average`, and `date`.
- Your application will get the popular movies from this JSON: https://api.themoviedb.org/3/movie/popular?api_key=PUT_YOUR_KEY&language=en-US&page=1
- Your application supports search for a specific movie, thus you need this JSON for this purpose: https://api.themoviedb.org/3/search/movie?api_key=PUT_YOUR_KEY&language=en-US&page=1&include_adult=false
- Also, your application will save the last search done by the user.

> **Note:** 
>You need to generate a API Key and replace the `PUT_YOUR_KEY` in the both URLs with your key. You can find how to generate it in this [video link](https://www.youtube.com/watch?v=Gf45f5cW6c4). 
>Also, you will face a problem with the image since the base URL will not available in JSON data, so you can fix it by using the base URL in this [Page](https://developers.themoviedb.org/3/getting-started/images).
> For search API, if you used `@Query("text")` in fun maybe you face problem, you can find what is the keyword used in the API instead of text in this [Page](https://developers.themoviedb.org/3/search/search-movies)


## Part Two - POST/DELETE HTTP Method. (Advanced and Optional)
---
Go to the [Movie BD website](https://developers.themoviedb.org/3/getting-started/introduction), and choose either Delete or Post HTTP method that is available on this website and apply on your application.


