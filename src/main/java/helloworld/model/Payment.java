
package helloworld.model;

import java.time.LocalDate;

        public class Payment {
            private int paymentId;
            private int billId;
            private double amount;
            private LocalDate paymentDate;
            private String method;

            public Payment(int paymentId, int billId, double amount, LocalDate paymentDate, String method) {
                this.paymentId = paymentId;
                this.billId = billId;
                this.amount = amount;
                this.paymentDate = paymentDate;
                this.method = method;
            }

            public int getPaymentId() {
                return paymentId;
            }

            public int getBillId() {
                return billId;
            }

            public double getAmount() {
                return amount;
            }

            public LocalDate getPaymentDate() {
                return paymentDate;
            }

            public String getMethod() {
                return method;
            }

            @Override
            public String toString() {
                return "Payment ID: " + paymentId +
                        ", Bill ID: " + billId +
                        ", Amount: " + amount +
                        ", Date: " + paymentDate +
                        ", Method: " + method;
            }
        }
