ALTER TABLE user_dtls MODIFY COLUMN password VARCHAR(255) NOT NULL;
ALTER TABLE doctor MODIFY COLUMN password VARCHAR(255) NOT NULL;
ALTER TABLE doctor_slot ADD CONSTRAINT uq_slot_doctor UNIQUE(id,doctor_id);
ALTER TABLE appointment ADD CONSTRAINT fk_slot_doctor FOREIGN KEY(slot_id,doctor_id) REFERENCES doctor_slot(id,doctor_id);
ALTER TABLE appointment ADD CONSTRAINT uq_appointment_slot UNIQUE(id,slot_id);
ALTER TABLE slot_reservation ADD CONSTRAINT fk_reserved_appointment_slot FOREIGN KEY(appointment_id,slot_id) REFERENCES appointment(id,slot_id);
