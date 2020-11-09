create table Player
(
    id         int         not null auto_increment,
    name       varchar(45) not null unique,
    wins       int         not null,
    loses      int         not null,
    lastPlayed date        not null,
    PRIMARY KEY (ID)
)


