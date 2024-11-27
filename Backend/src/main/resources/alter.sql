-- Credit_balance foreign key constraint
ALTER TABLE credit_balance
ADD CONSTRAINT fk_credit_balance_student
FOREIGN KEY (student_id)
REFERENCES student(student_id);

-- Student bills foreign key constraint
ALTER TABLE student_bills
ADD CONSTRAINT fk_student_bills_student
FOREIGN KEY (student_id)
REFERENCES student(student_id),
ADD CONSTRAINT fk_student_bills_bill
FOREIGN KEY (bill_id)
REFERENCES bills(id);

-- Student payment foreign key constraint
ALTER TABLE student_payment
ADD CONSTRAINT fk_student_payment_student
FOREIGN KEY (student_id)
REFERENCES student(student_id),
ADD CONSTRAINT fk_student_payment_bill
FOREIGN KEY (bill_id)
REFERENCES bills(id);
