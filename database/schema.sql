create table user (
	id varchar (128) not null,
	display_name varchar(32) not null,
	primary key(id)
);

CREATE TABLE search_results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  query VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  _limit INT NOT NULL,
  result_data TEXT NOT NULL
);