# last-fm-music
A last.fm search mobile app


Last.fm API
The API is provided by Last.fm, the response data is in JSON format and the API documentation is provided below.
API Key:
1) Sign-up for a last.fm account at http://last.fm
2) Go to: https://www.last.fm/api/account/create and create your API Key
3) Documentation can be viewed at: http://www.last.fm/api/show/album.search

# Building the project
Once you have the api key you need to create a gradle property file at root of the project
and ad it there

`./secrets.properties`

`LASTFM_API_KEY="{API_KEY}"`
