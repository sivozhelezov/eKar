

DROP TABLE IF EXISTS trans;

CREATE TABLE trans (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR2(100),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);