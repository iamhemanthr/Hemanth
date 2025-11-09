CREATE TABLE user_account (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  roles VARCHAR(255),
  enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE talent (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  phone VARCHAR(50),
  primary_skill VARCHAR(255) NOT NULL,
  years_exp INT,
  location VARCHAR(255),
  current_status VARCHAR(50),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE talent_secondary_skills (
  talent_id INT REFERENCES talent(id) ON DELETE CASCADE,
  secondary_skills VARCHAR(255)
);

CREATE TABLE interview (
  id SERIAL PRIMARY KEY,
  talent_id INT REFERENCES talent(id) ON DELETE CASCADE,
  round_name VARCHAR(255),
  interviewer VARCHAR(255),
  mode VARCHAR(255),
  scheduled_at TIMESTAMP,
  result VARCHAR(50),
  feedback TEXT,
  rating INT,
  recorded_at TIMESTAMP
);

CREATE TABLE deployment (
  id SERIAL PRIMARY KEY,
  talent_id INT UNIQUE REFERENCES talent(id) ON DELETE CASCADE,
  client VARCHAR(255), project VARCHAR(255), role_title VARCHAR(255),
  start_date DATE, end_date DATE,
  deployment_status VARCHAR(50),
  remarks TEXT
);

CREATE TABLE status_history (
  id SERIAL PRIMARY KEY,
  talent_id INT REFERENCES talent(id) ON DELETE CASCADE,
  old_status VARCHAR(50),
  new_status VARCHAR(50),
  changed_by VARCHAR(255),
  reason TEXT,
  changed_at TIMESTAMP
);
