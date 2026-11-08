ALTER TABLE doctor ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE doctor ADD COLUMN auth_version INT NOT NULL DEFAULT 0;
ALTER TABLE user_dtls ADD COLUMN auth_version INT NOT NULL DEFAULT 0;
CREATE TABLE doctor_slot (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 doctor_id INT NOT NULL,
 starts_at TIMESTAMP NOT NULL,
 enabled BOOLEAN NOT NULL DEFAULT TRUE,
 CONSTRAINT uq_doctor_start UNIQUE (doctor_id, starts_at),
 FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
ALTER TABLE appointment ADD COLUMN slot_id BIGINT NULL;
ALTER TABLE appointment ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE appointment ADD CONSTRAINT fk_appointment_slot FOREIGN KEY (slot_id) REFERENCES doctor_slot(id);
CREATE TABLE appointment_note (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 appointment_id INT NOT NULL,
 doctor_id INT NOT NULL,
 body VARCHAR(1000) NOT NULL,
 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (appointment_id) REFERENCES appointment(id),
 FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
INSERT INTO appointment_note(appointment_id,doctor_id,body)
 SELECT id,doctor_id,status FROM appointment
 WHERE UPPER(status) NOT IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED');
UPDATE appointment SET status = CASE
 WHEN UPPER(status) IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED') THEN UPPER(status)
 ELSE 'COMPLETED' END;
ALTER TABLE appointment MODIFY COLUMN status VARCHAR(16) NOT NULL;
ALTER TABLE appointment ADD CONSTRAINT ck_appointment_status CHECK (status IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED'));
CREATE TABLE slot_reservation (
 slot_id BIGINT PRIMARY KEY,
 appointment_id INT NOT NULL UNIQUE,
 FOREIGN KEY (slot_id) REFERENCES doctor_slot(id),
 FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);
CREATE TABLE booking_request (
 user_id INT NOT NULL,
 request_key VARCHAR(64) NOT NULL,
 payload_hash VARCHAR(64) NOT NULL,
 appointment_id INT NULL,
 PRIMARY KEY (user_id,request_key),
 FOREIGN KEY (user_id) REFERENCES user_dtls(id),
 FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);
CREATE TABLE appointment_event (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 appointment_id INT NOT NULL,
 actor_role VARCHAR(16) NOT NULL,
 actor_id INT NOT NULL,
 from_status VARCHAR(16) NULL,
 to_status VARCHAR(16) NOT NULL,
 action VARCHAR(40) NOT NULL,
 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);
CREATE INDEX ix_appointment_user ON appointment(user_id,id);
CREATE INDEX ix_appointment_doctor ON appointment(doctor_id,id);
CREATE INDEX ix_note_appointment ON appointment_note(appointment_id,id);
CREATE INDEX ix_event_appointment ON appointment_event(appointment_id,id);
CREATE INDEX ix_slot_available ON doctor_slot(starts_at,doctor_id);
