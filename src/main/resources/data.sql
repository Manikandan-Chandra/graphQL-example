DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS director;

create table director (id INT AUTO_INCREMENT  PRIMARY KEY,
                       first_name VARCHAR(250) NOT NULL,
                       last_name VARCHAR(250) NOT NULL);

create table movie (id INT AUTO_INCREMENT  PRIMARY KEY,
                     title VARCHAR(250) NOT NULL,
                     language VARCHAR(50) default NULL,
                     duration decimal default NULL,
                     budget decimal default NULL,
                     director_id INT NOT NULL,
                     foreign key (director_id) references director(id));
