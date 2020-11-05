create table Customers
(
    id         int          not null auto_increment,
    name       varchar(255) not null,
    wins       int          not null,
    loses      int          not null,
    lastPlayed date         not null,
    PRIMARY KEY (ID)
)


