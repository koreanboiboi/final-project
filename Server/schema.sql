CREATE TABLE artist (
    spotify_artist_id   VARCHAR(128)                NOT NULL,
    artist_name         VARCHAR(128)                NOT NULL,
    popularity          TINYINT(100)       					        , 
    link                VARCHAR(128)                		    ,
    image				        VARCHAR(128)						            ,
    user_id             VARCHAR(128)                NOT NULL,

    PRIMARY KEY(spotify_artist_id),
    FOREIGN KEY(user_id) REFERENCES user(unique_user_id)
);

CREATE TABLE album (
    spotify_album_id    VARCHAR(128)                NOT NULL,
    album_name          VARCHAR(128)                NOT NULL,
    release_date        DATE                        		    ,
    link                VARCHAR(128)                		    ,
    image				        VARCHAR(128)						            ,
    user_id             VARCHAR(128)                NOT NULL,

    PRIMARY KEY(spotify_album_id),
    FOREIGN KEY(user_id) REFERENCES user(unique_user_id)
);

CREATE TABLE user (
  unique_user_id        VARCHAR(255)                NOT NULL,
  name                  VARCHAR(255)                NOT NULL,
  PRIMARY KEY (unique_user_id)
);


-- MANY TO MANY

CREATE TABLE user_artist (
  id INT NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(128) NOT NULL,
  artist_id VARCHAR(128) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (unique_user_id),
  FOREIGN KEY (artist_id) REFERENCES artist (spotify_artist_id)
);

CREATE TABLE user_album (
  id INT NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(128) NOT NULL,
  album_id VARCHAR(128) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (unique_user_id),
  FOREIGN KEY (album_id) REFERENCES album (spotify_album_id)
);

SELECT *
FROM user_artist
JOIN user ON user_artist.user_id = user.unique_user_id
JOIN artist ON user_artist.artist_id = artist.spotify_artist_id

SELECT *
FROM user_album
JOIN user ON user_album.user_id = user.unique_user_id
JOIN album ON user_album.album_id = album.spotify_album_id