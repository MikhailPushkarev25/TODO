
CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT UNIQUE,
   password TEXT
);

CREATE TABLE items (
   id SERIAL primary key,
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN,
   users_id INT REFERENCES users(id)
);